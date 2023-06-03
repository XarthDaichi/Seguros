/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.data;

import com.progra4.segurosbackend.logic.Category;
import com.progra4.segurosbackend.logic.Coverage;
import com.progra4.segurosbackend.logic.Rule;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author diego
 */
public class CategoryDao {
    RelDatabase db;
    
    public CategoryDao(RelDatabase db) {
        this.db = db;
    }
    
    public Category read(String id) throws Exception {
        ArrayList<Coverage> coverages = new ArrayList<>();
        try {
            String sql = "select " +
                    "* " +
                    "from Coverage e inner join Category c on e.categoryId=c.categoryId " +
                    "where e.categoryId=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(id.substring(3,6)));
            ResultSet rs = db.executeQuery(stm);
            CoverageDao coverageDao = new CoverageDao(db);
            Category c = new Category();
            while (rs.next()) {
                c = from(rs, "c");
                coverages.add(coverageDao.from(rs, "e"));
            }
            c.setCoverages(coverages);
            return c;
        }catch (SQLException ex) {}
        return null;
    }
    
    private Category from(ResultSet rs, String alias) {
        try {
            Category e = new Category();
            e.setId(String.format("CAT%03d", rs.getInt(alias + ".categoryId")));
            e.setName(rs.getString(alias + ".categoryName"));
            e.setDescription(rs.getString(alias + ".descrip"));
            ArrayList<Coverage> coverages = new ArrayList<>();
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void insert(Category c) throws Exception {
        String sql = "insert into " +
                "Category " +
                "(categoryName, descrip) " +
                "values (?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getName());
        stm.setString(2, c.getDescription());
        CoverageDao coverageDao = new CoverageDao(db);
        for (Rule ru : c.getCoverages()) {
            Coverage cov = (Coverage) ru;
            coverageDao.insert(cov, c.getId());
        }
        
        db.executeUpdate(stm);
    }
    
    public ArrayList<Category> selectAll() throws Exception {
        ArrayList<Category> result = new ArrayList<>();
        String sql = "select * from Category e";
        PreparedStatement stm = db.prepareStatement(sql);
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            Category c = from(rs, "e");
            result.add(read(c.getId()));
        }
        return result;
    }
}
