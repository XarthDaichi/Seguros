/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.data;

import com.progra4.segurosbackend.logic.Category;
import com.progra4.segurosbackend.logic.Coverage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class CoverageDao {
    RelDatabase db;
    
    public CoverageDao(RelDatabase db) {
        this.db = db;
    }
    
    public Coverage read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from Coverage e " +
                "where e.coverageId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(id.substring(3,6)));
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "e");
        } else {
            throw new Exception("Cobertura no Existe");
        }
    }
    
    public Coverage from(ResultSet rs, String alias) {
        try {
            Coverage e = new Coverage();
            e.setId(String.format("COV%03d", rs.getInt(alias + ".coverageId")));
            e.setName(rs.getString(alias + ".coverageName"));
            e.setDescription(rs.getString(alias + ".descrip"));
            e.setMinimumCost(rs.getDouble(alias + ".cost"));
            e.setPercentage(rs.getDouble(alias + ".percent"));
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void insert(Coverage c, Category cat) throws Exception {
        String sql = "insert into " +
                "Coverage " +
                "(coverageName, descrip, cost, percent, categoryId) " +
                "values (?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, c.getName());
        stm.setString(2, c.getDescription());
        stm.setDouble(3, c.getMinimumCost());
        stm.setDouble(4, c.getPercentage());
        stm.setInt(5, Integer.parseInt(cat.getId().substring(3, 6)));
        
        db.executeUpdate(stm);
    }
    
    public ArrayList<Coverage> selectAll() throws Exception {
        ArrayList<Coverage> result = new ArrayList<>();
        String sql = "select " +
                "* " +
                "from Coverage e";
        PreparedStatement stm = db.prepareStatement(sql);
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            result.add(from(rs, "e"));
        }
        return result;
    }
}
