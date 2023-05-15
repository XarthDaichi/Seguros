package com.progra4.Seguros.presentation.admin.policies;

import com.progra4.Seguros.logic.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Model {
    List<Policy> policies;
    List<User> users;
    Map<String, List<Policy>> policiesByUser;
    Policy selected;

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Policy> rows = new ArrayList<>();
        List<User> rowsU = new ArrayList<>();
        Map<String, List<Policy>> rowsPolicies = new HashMap<>();
        selected=null;  
        this.setPolicies(rows);
        this.setUsers(rowsU);
        this.setPoliciesByUser(rowsPolicies);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Policy getSelected() {
        return selected;
    }

    public void setSelected(Policy selected) {
        this.selected = selected;
    }
    
    public void setPolicies(List<Policy> cuentas){
        this.policies =cuentas;    
    }

     public List<Policy> getPolicies() {
        return policies;
    }

    public Map<String, List<Policy>> getPoliciesByUser() {
        return policiesByUser;
    }

    public void setPoliciesByUser(Map<String, List<Policy>> policiesByUser) {
        this.policiesByUser = policiesByUser;
    }
     
     
}