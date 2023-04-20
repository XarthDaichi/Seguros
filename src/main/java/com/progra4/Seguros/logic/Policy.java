/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class Policy {
    private Vehicle vehicle;
    private Term termChosen;
    private String initialDate;
    private List<Cobertura> coberturas;
    private float insuredValue;

    public Policy() {
    }

    public Policy(Vehicle vehicle, Term termChosen, String initialDate, List<Cobertura> coberturas, float insuredValue) {
        this.vehicle = vehicle;
        this.termChosen = termChosen;
        this.initialDate = initialDate;
        this.coberturas = coberturas;
        this.insuredValue = insuredValue;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Term getTermChosen() {
        return termChosen;
    }

    public void setTermChosen(Term termChosen) {
        this.termChosen = termChosen;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public List<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(List<Cobertura> coberturas) {
        this.coberturas = coberturas;
    }

    public float getInsuredValue() {
        return insuredValue;
    }

    public void setInsuredValue(float insuredValue) {
        this.insuredValue = insuredValue;
    }
    
    
}
