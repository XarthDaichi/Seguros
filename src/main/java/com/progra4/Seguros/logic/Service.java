/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.logic;
import java.util.List;
import com.progra4.Seguros.data.*;
import java.util.ArrayList;
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
    
    public void userUpdate(User u) {
        try {
            userDao.update(u);
        } catch(Exception ex) {
            
        }
    }
    
    public void userRegister(User u) throws Exception {
        userDao.insert(u);
    }
    
    public ArrayList<User> selectAllUsers() throws Exception {
        return userDao.selectAll();
    }
    
    public ArrayList<User> selectOnlyClients() throws Exception{
        return userDao.selectClients();
    }
    
    public List<Policy> policiesFind(User u) throws Exception {
        return policyDao.findByUser(u);
    }
    
    public ArrayList<Policy> selectAllPolicies() throws Exception{
        return policyDao.selectAll();
    }
    
    public void PolicyCreate(Policy p) throws Exception {
        policyDao.insert(p);
    }
    
    public Policy policyFind(String policyId) throws Exception {
        return policyDao.read(policyId);
    }
    
    public void CategoryCreate(Category c) throws Exception {
        categoryDao.insert(c);
    }
    
    public void CoverageCreate(Coverage c, Category cat) throws Exception {
        coverageDao.insert(c, cat);
    }
    
    public ArrayList<ArrayList<Vehicle>> selectBrandsAndModels() throws Exception {
        ArrayList<Vehicle> resultQuery = vehicleDao.selectAll();
        ArrayList<Vehicle> resultNoRedundancy = new ArrayList<>();
        resultNoRedundancy.add(resultQuery.get(0));
        for (int i = 1; i < resultQuery.size(); i++) {
            for (int j = 0; j < resultNoRedundancy.size(); j++) {
                if (resultQuery.get(i).getBrand().equals(resultNoRedundancy.get(j).getBrand()) && resultQuery.get(i).getModel().equals(resultNoRedundancy.get(j).getModel())) {
                    break;
                }
                if (j == resultNoRedundancy.size() - 1) {
                    resultNoRedundancy.add(resultQuery.get(i));
                    break;
                }
            }
            
        }
        ArrayList<ArrayList<Vehicle>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        result.get(0).add(resultNoRedundancy.get(0));
        for (int i = 1; i < resultNoRedundancy.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                if (resultNoRedundancy.get(i).getBrand().equals(result.get(j).get(0).getBrand())) {
                    result.get(j).add(resultNoRedundancy.get(i));
                    break;
                }
                if (j == result.size() - 1) {
                    result.add(new ArrayList<>());
                    result.get(j+1).add(resultNoRedundancy.get(i));
                    break;
                }
            }
        }
        return result;
    }
    
    public Vehicle vehicleVerify(Vehicle v) throws Exception {
        Vehicle vehicle = vehicleDao.read(v.getId());
        if (vehicle.getBrand().equals(v.getBrand()) && vehicle.getModel().equals(v.getModel()) && vehicle.getYear() == v.getYear()) {
            return vehicle;
        }
        return null;
    }
}
