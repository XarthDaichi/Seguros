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
public class User implements Serializable {
    private String id;
    private String password;
    private String name;
    private String cellphone;
    private String email;
    private String cardNumber;
    private Boolean administrator;

    public User() {
        this.id = "";
        this.password = "";
        this.name = "";
        this.cellphone = "";
        this.email = "";
        this.cardNumber = "";
        this.administrator = false;
    }

    public User(String id, String password, String name, String cellphone, String email, String cardNumber, Boolean administrator) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.cellphone = cellphone;
        this.email = email;
        this.cardNumber = cardNumber;
        this.administrator = administrator;
    }
    
    public User(String id, String password, String name, String cellphone, String email, String cardNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.cellphone = cellphone;
        this.email = email;
        this.cardNumber = cardNumber;
        this.administrator = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", name=" + name + ", cellphone=" + cellphone + ", email=" + email + ", cardNumber=" + cardNumber + ", administrator=" + administrator + '}';
    }
}
