/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class UserDao {
    RelDatabase db;
    
    public UserDao(RelDatabase db) {
        this.db = db;
    }
    
    public User read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from  Users e " +
                "where e.userId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "e");
        } else {
            throw new Exception("Empleado no Existe");
        }
    }
    
    public User from(ResultSet rs, String alias) {
        try {
            User e = new User();
            e.setId(rs.getString(alias + ".userId"));
            e.setPassword(rs.getString(alias + ".pass"));
            e.setName(rs.getString(alias + ".nameU"));
            e.setCellphone(rs.getString(alias + ".cellphone"));
            e.setEmail(rs.getString(alias + ".email"));
            e.setCardNumber(rs.getString(alias + ".cardNumber"));
            e.setAdministrator(rs.getBoolean(alias + ".typeU"));            
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }  
}
