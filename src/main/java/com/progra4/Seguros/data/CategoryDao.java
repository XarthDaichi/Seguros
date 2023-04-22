/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.Category;
import com.progra4.Seguros.logic.Coverage;
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
    
    public ArrayList<Coverage> readCoverages(String idCategory) throws Exception {
        ArrayList<Coverage> results = new ArrayList<>();
        String sql = "select " +
                "* " +
                "from Coverage e " +
                "where e.coverageId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "e");
    }
    
    public Coverage fromCoverage(ResultSet rs, String alias) {
        try {
            Coverage e = new Coverage();
            e.setId(rs.getString(alias + ".coverageId"));
        } catch (SQLException ex) {
            return null;
        }
    }
}
