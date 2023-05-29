/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.Service;
import com.progra4.segurosbackend.logic.User;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author diego
 */

@Path("/register")
@PermitAll
public class Register {
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public void register(User user) {
        try {
            Service.instance().userRegister(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
