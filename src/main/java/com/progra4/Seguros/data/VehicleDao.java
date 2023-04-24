/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class VehicleDao {
    RelDatabase db;
    
    public VehicleDao(RelDatabase db) {
        this.db = db;
    }
    
    public Vehicle read(int id) throws Exception {
        String sql = "select " +
                "* " +
                "from  Vehicle v " +
                "where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "v");
        } else {
            throw new Exception("Vehiculo no Existe");
        }
    }
    
    public Vehicle from(ResultSet rs, String alias) {
        try {
            Vehicle e = new Vehicle();
            e.setId(rs.getInt(alias + ".id"));
            e.setBrand(rs.getString(alias + ".brand"));
            e.setModel(rs.getString(alias + ".model"));
            e.setYear(rs.getInt(alias + ".yearV"));            
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void insert(Vehicle v) throws Exception {
        String sql = "insert into " +
                "Vehicle " +
                "(brand, model, yearV) "+
                "values (?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(2, v.getBrand());
        stm.setString(3, v.getModel());
        stm.setInt(4, v.getYear());
        
        db.executeUpdate(stm);
    }
    
    public ArrayList<Vehicle> selectAll() throws Exception {
        ArrayList<Vehicle> result = new ArrayList<>();
        String sql = "select * from Vehicle e";
        PreparedStatement stm = db.prepareStatement(sql);
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            result.add(from(rs, "e"));
        }
        return result;
    }
}
