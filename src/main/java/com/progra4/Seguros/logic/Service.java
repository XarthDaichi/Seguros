/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.logic;

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
    PolicyDao policyDao;
    
    private Service() {
        relDatabase = new RelDatabase();
        userDao = new UserDao(relDatabase);
        policyDao = new PolicyDao(relDatabase);
    }
    
    public User userFind(String id, String pass) throws Exception {
        return userDao.read(id);
    }
    
    public List<Policy> policiesFind(User u) throws Exception {
        return policyDao.findByClient(u);
    }
}
