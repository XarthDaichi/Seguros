/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.logic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author diego
 */

public abstract class Rule {
    protected String id;
    protected String description;

    public Rule() {
        id = "";
        description = "";
    }
    

    public Rule(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Rule{" + "id=" + id + ", description=" + description + '}';
    }
    
    public void add(Rule rule) {
        
    }
    
    public void remove(Rule rule) {
        
    }
    
    public ArrayList<Rule> hasCoverages() {
        return null;
    }
    
    public abstract double calculateCost(double insuredValue);
}
