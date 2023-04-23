/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.logic;
import java.util.List;
import com.progra4.Seguros.data.*;
import java.util.List;

/**
 *
 * @author diego
 */
public class Service {
    private static Service uniqueInstance;
    
    public static Service instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Service();
        }
        return uniqueInstance;
    }
    
    RelDatabase relDatabase;
    UserDao userDao;
    CoverageDao coverageDao;
    CategoryDao categoryDao;
    PolicyDao policyDao;
    VehicleDao vehicleDao;
    
    private Service() {
        relDatabase = new RelDatabase();
        userDao = new UserDao(relDatabase);
        coverageDao = new CoverageDao(relDatabase);
        categoryDao = new CategoryDao(relDatabase);
        policyDao = new PolicyDao(relDatabase);
        vehicleDao = new VehicleDao(relDatabase);
    }
    
    public User userFind(String id, String pass) throws Exception {
        User user = userDao.read(id);
        if (user.getPassword().equals(pass)) return user;
        else return null;
    }
    
    public void userRegister(User u) throws Exception {
        userDao.insert(u);
    }
    
    public List<Policy> policiesFind(User u) throws Exception {
        return policyDao.findByUser(u);
    }
    
    public void PolicyCreate(Policy p) throws Exception {
        
    }
    
    
}
