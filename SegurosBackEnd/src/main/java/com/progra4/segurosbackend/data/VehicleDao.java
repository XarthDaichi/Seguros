/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.data;

import com.progra4.segurosbackend.logic.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        stm.setString(1, v.getBrand());
        stm.setString(2, v.getModel());
        stm.setInt(3, v.getYear());
        
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
    
    public Vehicle findVehicle(String brand, String model, int year) throws Exception {
        String sql = "select * from Vehicle e " +
                "where e.brand=? and e.model=? and e.yearV=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, brand);
        stm.setString(2, model);
        stm.setInt(3, year);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "e");
        }
        return null;
    }
    
    public List<Vehicle> findVehicles(String brand, String model) throws Exception {
        ArrayList<Vehicle> result = new ArrayList<>();
        try {
            String sql = "select * from Vehicle e " +
                "where e.brand=? and e.model=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, brand);
            stm.setString(2, model);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                result.add(from(rs, "e"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }
    
    public List<Vehicle> findVehicles(String brand) throws Exception {
        ArrayList<Vehicle> result = new ArrayList<>();
        try {
            String sql = "select * from Vehicle e " +
                "where e.brand=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, brand);
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                result.add(from(rs, "e"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return result;
    }
}
