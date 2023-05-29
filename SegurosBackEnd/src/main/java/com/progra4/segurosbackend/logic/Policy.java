/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.logic;

import com.progra4.segurosbackend.logic.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author diego
 */
public class Policy extends Rule implements Serializable {
    private Vehicle vehicle;
    private Term termChosen;
    private LocalDate initialDate;
    private List<Rule> rules;
    private double insuredValue;
    private User policyOwner;

    public Policy() {
        super();
        this.vehicle = new Vehicle();
        this.termChosen = Term.ANNUAL;
        this.initialDate = LocalDate.now();
        this.insuredValue = 0;
        this.policyOwner = new User();
    }

    public Policy(String id, Vehicle vehicle, Term termChosen, LocalDate initialDate, List<Rule> rules, double insuredValue, User policyOwner) {
        super(id, "");
        this.vehicle = vehicle;
        this.termChosen = termChosen;
        this.initialDate = initialDate;
        this.rules = rules;
        this.insuredValue = insuredValue;
        this.policyOwner = policyOwner;
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
    
    public String getTerm() {
        switch (termChosen) {
            case QUARTERLY:
                return "Trimestral";
            case BIANNUAL:
                return "Semestral";
            case ANNUAL:
                return "Anual";
        }
        return "";
    }

    public void setTermChosen(Term termChosen) {
        this.termChosen = termChosen;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public double getInsuredValue() {
        return insuredValue;
    }

    public void setInsuredValue(double insuredValue) {
        this.insuredValue = insuredValue;
    }

    public User getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(User policyOwner) {
        this.policyOwner = policyOwner;
    }

    @Override
    public String toString() {
        return "Policy{" + "vehicle=" + vehicle + ", termChosen=" + termChosen + ", initialDate=" + initialDate + ", rules=" + rules + ", insuredValue=" + insuredValue + '}';
    }

    @Override
    public void remove(Rule rule) {
        super.remove(rule);
    }

    @Override
    public void add(Rule rule) {
        super.add(rule);
    }
    
    @Override
    public double calculateCost(double insuredValue) {
        double total = 0;
        for (Rule r: rules) {
            total += r.calculateCost(insuredValue);
        }
        return total;
    }
}
