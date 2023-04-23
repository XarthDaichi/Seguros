/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

import com.progra4.Seguros.logic.Policy;
import com.progra4.Seguros.logic.Term;
import com.progra4.Seguros.logic.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lmont
 */
public class PolicyDao {
    RelDatabase db;
    UserDao userDao;
    VehicleDao vehicleDao;

    public PolicyDao(RelDatabase db) {
        this.db = db;
        this.userDao = new UserDao(db);
        this.vehicleDao = new VehicleDao(db);
    }
    
    public Policy read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from  PolicyClass e inner join Users u on e.userId = u.userId inner join Vehicle v on e.vehicleLicensePlate=v.licensePlate " +
                "where e.policyId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        UserDao userDao = new UserDao(db);
        VehicleDao vehicleDao = new VehicleDao(db);
        Policy p;
        if (rs.next()) {
            p = from(rs, "e");
            p.setPolicyOwner(userDao.from(rs, "u"));
            p.setVehicle(vehicleDao.from(rs,"v"));
            return p;
        } else {
            throw new Exception("Policy does not exist");
        }
    }
    
    public List<Policy> findByUser(User u){
        List<Policy> result = new ArrayList<>();
        try{
            String sql = "select * " +
                    "from " +
                    "PolicyClass e inner join Vehicle v on e.vehicleLicensePlate=v.licensePlate " +
                    "where e.userId=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, u.getId());
            ResultSet rs = db.executeQuery(stm);
            VehicleDao vehicleDao = new VehicleDao(db);
            while (rs.next()) {
                Policy p;
                p = from(rs, "e");
                p.setVehicle(vehicleDao.from(rs, "v"));
                result.add(p);
            }
        }catch (SQLException ex){}
        return result;
    }
    
    public Policy from(ResultSet rs, String alias) {
        try {
            Policy e = new Policy();
            e.setId(String.format("POL%03d", rs.getInt(alias + ".policyId")));
            e.setTermChosen(Term.valueOf(rs.getString(alias + ".term")));
            e.setInitialDate(rs.getDate(alias + ".initialDate").toLocalDate());
            e.setInsuredValue(rs.getDouble(alias + ".insuredValue"));           
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void insert(Policy p) throws Exception {
        String sql = "insert into " +
                "PolicyClass " +
                "(userId, vehicleLicensePlate, term, initialDate, insuredValue) " +
                "values(?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(2, p.getDescription());
        stm.setString(3, p.getVehicle().getLicensePlate());
        stm.setString(4, p.getTermChosen().name());
        stm.setString(5, p.getInitialDate().toString());
        stm.setDouble(6, p.getInsuredValue());
        
        db.executeUpdate(stm);
    }
}
