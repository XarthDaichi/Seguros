<<<<<<< HEAD
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.data;

/**
 *
 * @author diego
 */
public class PolicyDao {
    
}
=======
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

    public PolicyDao(RelDatabase db) {
        this.db = db;
        this.userDao = new UserDao(db);
    }
    
    public Policy read(String id) throws Exception {
        String sql = "select " +
                "* " +
                "from  PolicyClass e inner join User u on e.userId = u.userId " +
                "where e.policyId=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = db.executeQuery(stm);
        Policy p;
        if (rs.next()) {
            p = from(rs, "e");
            p.setPolicyOwner(userDao.from(rs, "u"));
            return p;
        } else {
            throw new Exception("Policy does not exist");
        }
    }
    
    public List<Policy> findByClient(User u){
        List<Policy> result = new ArrayList<>();
        try{
            String sql = "select * " +
                    "from " +
                    "PolicyClass e " +
                    "where e.userId=?";
            PreparedStatement stm = db.prepareStatement(sql);
            stm.setString(1, u.getId());
            ResultSet rs = db.executeQuery(stm);
            while (rs.next()) {
                result.add(from(rs, "e"));
            }
        }catch (SQLException ex){}
        
        return result;
    }
    
    public Policy from(ResultSet rs, String alias) {
        try {
            Policy e = new Policy();
            e.setId(rs.getString(alias + ".policyId"));
            e.setPolicyOwner(userDao.from(rs, ".userId"));
            //e.setVehicle(rs.getString(alias + ".vehicleLicensePlate"));
            e.setTermChosen(Term.valueOf(rs.getString(alias + ".term")));
            e.setInitialDate(rs.getString(alias + ".initialDate"));
            e.setInsuredValue(Float.parseFloat(rs.getString(alias + ".insuredValue")));           
            return e;
        } catch (SQLException ex) {
            return null;
        }
    }
}
>>>>>>> 0e2f78d4115a7b16a74c1bc1ab99b5f2d57d309b
