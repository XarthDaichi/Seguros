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
    
    public Category read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from Category e " +
                "where e.categoryId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        Category c;
        if (rs.next()) {
            c = from(rs,"e");
            return c;
        } else {
            throw new Exception("Categoria no Existe");
        }
    }
    
    private Category from(ResultSet rs, String alias) {
        try {
            Category e = new Category();
            e.setId(rs.getString(alias + ".categoryId"));
            e.setDescription(rs.getString(alias + ".descrip"));
        } catch (SQLException ex) {
            return null;
        }
    }
}
