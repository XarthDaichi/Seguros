/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.data;

import com.progra4.segurosbackend.logic.Coverage;
import com.progra4.segurosbackend.logic.Policy;
import com.progra4.segurosbackend.logic.Rule;
import com.progra4.segurosbackend.logic.Term;
import com.progra4.segurosbackend.logic.User;
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
        ArrayList<Coverage> coverages = new ArrayList<>();
        try {
            String sql = "select " +
                    "* " +
                    "from Applies e " +
                    "inner join PolicyClass p on e.policyId=p.policyId inner join Users u on p.userId=u.userId inner join Vehicle v on p.vehicleId=v.id " +
                    "inner join Coverage c on e.coverageId=c.coverageId " +
                    "where p.policyId=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = db.executeQuery(stm);
            UserDao userDao = new UserDao(db);
            VehicleDao vehicleDao = new VehicleDao(db);
            CoverageDao coverageDao = new CoverageDao(db);
            Policy p = new Policy();
            while (rs.next()) {
                p = from(rs, "p");
                p.setPolicyOwner(userDao.from(rs,"u"));
                p.setVehicle(vehicleDao.from(rs, "v"));
                coverages.add(coverageDao.from(rs,"c"));
            }
            p.setRules(coverages);
            return p;
        } catch (SQLException ex) {}
        return null;
    }
    
    public List<Policy> findByUser(User u){
        List<Policy> result = new ArrayList<>();
        ArrayList<Coverage> coverages = new ArrayList<>();
        try{
            String sql = "select " +
                    "* " +
                    "from Applies e " +
                    "inner join PolicyClass p on e.policyId=p.policyId inner join Vehicle v on p.vehicleId=v.id " +
                    "inner join Coverage c on e.coverageId=c.coverageId " +
                    "where p.userId=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, u.getId());
            ResultSet rs = db.executeQuery(stm);
            VehicleDao vehicleDao = new VehicleDao(db);
            CoverageDao coverageDao = new CoverageDao(db);
            Policy p = null;
            String id = "", idlast;
            while (rs.next()) {
                idlast = id;
                id = fromApplies(rs, "e");
                Coverage c;
                c = coverageDao.from(rs, "c");
                if(!id.equals(idlast) && !idlast.equals("")) {
                    p.setRules(coverages);
                    result.add(p);
                    coverages = new ArrayList<>();
                } 
                if (coverages.isEmpty()) {
                    p = new Policy();
                    p = from(rs, "p");
                    p.setVehicle(vehicleDao.from(rs, "v"));
                }
                coverages.add(c);
                if (rs.isLast()) {
                    p.setRules(coverages);
                    result.add(p);
                }
            }  
        }catch (SQLException ex){}
        return result;
    }
    
    public ArrayList<Policy> selectAll() throws Exception{
        ArrayList<Policy> result = new ArrayList<>();
        ArrayList<Coverage> coverages = new ArrayList<>();
        String sql = "select " +
            "* " +
            "from Applies e " +
            "inner join PolicyClass p on e.policyId=p.policyId inner join Users u on p.userId=u.userId inner join Vehicle v on p.vehicleId=v.id " +
            "inner join Coverage c on e.coverageId=c.coverageId"; 
        PreparedStatement stm = db.prepareStatement(sql);
        ResultSet rs = db.executeQuery(stm);
        UserDao userDao = new UserDao(db);
        VehicleDao vehicleDao = new VehicleDao(db);
        CoverageDao coverageDao = new CoverageDao(db);
        Policy p = null;
        String id = "", idlast;
        while (rs.next()) {
            idlast = id;
            id = fromApplies(rs, "e");
            Coverage c;
            c = coverageDao.from(rs, "c");
            if(!id.equals(idlast) && !idlast.equals("")) {
                p.setRules(coverages);
                result.add(p);
                coverages = new ArrayList<>();
            } 
            if (coverages.isEmpty()) {
                p = new Policy();
                p = from(rs, "p");
                p.setVehicle(vehicleDao.from(rs, "v"));
                p.setPolicyOwner(userDao.from(rs,"u"));
            }
            coverages.add(c);
            if (rs.isLast()) {
                p.setRules(coverages);
                result.add(p);
            }
        }
        return result;
    }
    
    public Policy from(ResultSet rs, String alias) {
        try {
            Policy e = new Policy();
            e.setId(String.format("POL%03d", rs.getInt(alias + ".policyId")));
            e.setLicense(rs.getString(alias + ".license"));
            e.setTermChosen(Term.valueOf(rs.getString(alias + ".term")));
            e.setInitialDate(rs.getDate(alias + ".initialDate").toLocalDate());
            e.setInsuredValue(rs.getDouble(alias + ".insuredValue"));           
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public String fromApplies(ResultSet rs, String alias) {
        try {
            return String.format("POL%03d", rs.getInt(alias + ".policyId"));
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public void insert(Policy p) throws Exception {
        String sql = "insert into " +
                "PolicyClass " +
                "(license, userId, vehicleId, term, initialDate, insuredValue) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, p.getLicense());
        stm.setString(2, p.getPolicyOwner().getId());
        stm.setInt(3, p.getVehicle().getId());
        stm.setString(4, p.getTermChosen().name());
        stm.setString(5, p.getInitialDate().toString());
        stm.setDouble(6, p.getInsuredValue());
        
        db.executeUpdate(stm);
        
        String sql2 = "select policyId from PolicyClass e order by policyId desc";
        PreparedStatement stm2 = db.prepareStatement(sql2);
        ResultSet rs = db.executeQuery(stm2);
        
        int id = 0;
        if(rs.next()) {
            id = rs.getInt("e.policyId");
        }
        
        for (Coverage r : p.getRules()) {
            String sql3 = "insert into Applies (policyId, coverageId) " +
                    "values (?, ?)";
            PreparedStatement stm3 = db.prepareStatement(sql3);
            stm3.setInt(1, id);
            stm3.setInt(2, Integer.parseInt(r.getId().substring(3,6)));
            
            db.executeUpdate(stm3);
        }
    }
    
    public void delete(String policyId) throws Exception {
        String sql = "delete from " +
                "Applies " +
                "where policyId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(policyId.substring(3,6)));
        db.executeUpdate(stm);
        
        String sql2 = "delete from " +
                "PolicyClass " +
                "where policyId=?";
        PreparedStatement stm2 = db.prepareStatement(sql2);
        stm2.setInt(1, Integer.parseInt(policyId.substring(3,6)));
        db.executeUpdate(stm2);
    }
}
