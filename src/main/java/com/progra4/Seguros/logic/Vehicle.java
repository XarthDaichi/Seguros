/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.logic;

/**
 *
 * @author diego
 */
public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int year;

    public Vehicle() {
        this.brand = "";
        this.model = "";
        this.year = 0;
    }

    public Vehicle(int id, String brand, String model, int year) {
        this.id=id;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
