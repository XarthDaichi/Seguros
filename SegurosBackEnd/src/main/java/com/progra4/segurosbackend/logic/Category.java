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
public class Category extends Rule implements Serializable {
    private String name;
    private ArrayList<Coverage> coverages;

    public Category() {
        super();
        this.name = "";
    }

    public Category(String id, String description, ArrayList<Coverage> coverages) {
        super(id, description);
        this.coverages = coverages;
    }

    public ArrayList<Coverage> getCoverages() {
        return coverages;
    }

    public void setCoverages(ArrayList<Coverage> coverages) {
        this.coverages = coverages;
    }

    @Override
    public String toString() {
        return "Category{" + "coverages=" + coverages + '}';
    }

    @Override
    public void remove(Rule rule) {
        super.remove(rule);
    }

    @Override
    public void add(Rule rule) {
        super.add(rule);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public double calculateCost(double insuredValue) {
        double total = 0;
        for (Coverage c : coverages) {
            total += c.calculateCost(insuredValue);
        }
        return total;
    }
    
    
}
