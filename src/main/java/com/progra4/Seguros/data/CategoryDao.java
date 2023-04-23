/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.Category;
import com.progra4.Seguros.logic.Coverage;
import com.progra4.Seguros.logic.Rule;
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
        String sql = "select " +
                "* " +
                "from Coverage e inner join Category c on e.categoryId=c.categoryId " +
                "where e.categoryId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        CoverageDao coverageDao = new CoverageDao(db);
        Category c = from(rs, "c");
        ArrayList<Rule> coverages = new ArrayList<>();
        if (rs.next()) {
            coverages.add(coverageDao.from(rs, "e"));
        } else {
            throw new Exception("Categoria no Existe");
        }
        c.setCoverages(coverages);
        return c;
    }
    
    private Category from(ResultSet rs, String alias) {
        try {
            Category e = new Category();
            e.setId(rs.getString(alias + ".categoryId"));
            e.setDescription(rs.getString(alias + ".descrip"));
            ArrayList<Coverage> coverages = new ArrayList<>();
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
