package com.progra4.Seguros.presentation.admin.policies;

import com.progra4.Seguros.logic.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Policy> policies;
    List<User> users;
    Policy selected;

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Policy> rows = new ArrayList<>();
        List<User> rowsU = new ArrayList<>();
        selected=null;  
        this.setPolicies(rows);
        this.setUsers(rowsU);
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
}