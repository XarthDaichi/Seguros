/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class VehicleDao {
    RelDatabase db;
    
    public VehicleDao(RelDatabase db) {
        this.db = db;
    }
    
    public Vehicle read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from  Vehicle v " +
                "where v.licensePlate=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
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
            e.setLicensePlate(rs.getString(alias + ".licensePlate"));
            e.setBrand(rs.getString(alias + ".brand"));
            e.setModel(rs.getString(alias + ".model"));
            e.setYear(rs.getDate(alias + ".yearV").getYear());            
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
