/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.logic;

import java.io.Serializable;

/**
 *
 * @author diego
 */
public class Coverage extends Rule implements Serializable {
    private String name;
    private double minimumCost;
    private double percentage;
    private String categoryId;

    public Coverage() {
        super();
        this.name = "";
        this.minimumCost = 0;
        this.percentage = 0;
        this.categoryId = "";
    }

    public Coverage(String id, String description, double minimumCost, double percentage) {
        super(id, description);
        this.minimumCost = minimumCost;
        this.percentage = percentage;
        this.categoryId = "";
    }

    public double getMinimumCost() {
        return minimumCost;
    }

    public void setMinimumCost(double minimumCost) {
        this.minimumCost = minimumCost;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Coverage{" + "minimumCost=" + minimumCost + ", percentage=" + percentage + '}';
    }

    @Override
    public double calculateCost(double insuredValue) {
        if (insuredValue * percentage > minimumCost) return insuredValue * percentage;
        else return minimumCost;
    }
}
