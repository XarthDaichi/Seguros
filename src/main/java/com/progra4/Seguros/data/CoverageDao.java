/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.Coverage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        stm.setString(1, id);
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
            e.setId(rs.getString(alias + ".coverageId"));
            e.setName(rs.getString(alias + ".coverageName"));
            e.setDescription(rs.getString(alias + ".descrip"));
            e.setMinimumCost(rs.getDouble(alias + ".cost"));
            e.setPercentage(rs.getDouble(alias + ".percent"));
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
