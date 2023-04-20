/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.logic;

/**
 *
 * @author diego
 */
public class User {
    private String ID;
    private String password;
    private String name;
    private String telefono;
    private String email;
    private String cardNumber;

    public User() {
        this.ID = "";
        this.password = "";
        this.name = "";
        this.telefono = "";
        this.email = "";
        this.cardNumber = "";
    }

    public User(String ID, String password, String name, String telefono, String email, String cardNumber) {
        this.ID = ID;
        this.password = password;
        this.name = name;
        this.telefono = telefono;
        this.email = email;
        this.cardNumber = cardNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
