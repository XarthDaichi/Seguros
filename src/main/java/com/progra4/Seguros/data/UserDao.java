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
            throw new Exception("Usuario no Existe");
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
    
    public void insert(User e) throws Exception {
        String sql = "insert into " +
                "Users " +
                "(userId, pass, nameU, cellphone, email, cardNumber, typeU) " +
                "values(?,?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getId());
        stm.setString(2, e.getPassword());
        stm.setString(3, e.getName());
        stm.setString(4, e.getCellphone());
        stm.setString(5, e.getEmail());
        stm.setString(6, e.getCardNumber());
        stm.setBoolean(7, e.getAdministrator());

        db.executeUpdate(stm);
    }
    
    public void updatePassword(User e) throws Exception {
        String sql = "update " +
                "Cliente " +
                "set pass=? " +
                "where userId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getPassword());
        stm.setString(2, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }
    
    public void updateName(User e) throws Exception {
        String sql = "update " +
                "Cliente " +
                "set nameU=? " +
                "where userId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getName());
        stm.setString(2, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }
    
    public void updateCellphone(User e) throws Exception {
        String sql = "update " +
                "Cliente " +
                "set chellphone=? " +
                "where userId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCellphone());
        stm.setString(2, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }
    
    public void updateEmail(User e) throws Exception {
        String sql = "update " +
                "Cliente " +
                "set email=? " +
                "where userId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getEmail());
        stm.setString(2, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }
    
    public void updateCard(User e) throws Exception {
        String sql = "update " +
                "Cliente " +
                "set cardNumber=? " +
                "where userId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCardNumber());
        stm.setString(2, e.getId());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Usuario no existe");
        }
    }
}
