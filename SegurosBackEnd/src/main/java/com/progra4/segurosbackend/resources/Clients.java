/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.Service;
import com.progra4.segurosbackend.logic.User;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author diego
 */

@Path("/clients")
@PermitAll
public class Clients {
    /**
     * 
     * @param id
     * @param name
     * @return
     * @throws java.lang.Exception
     */
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<User> find(@DefaultValue("") @QueryParam("id") String id, @DefaultValue("") @QueryParam("name") String name) throws Exception {
        if (id.equals("") && name.equals("")) {
            return Service.instance().selectOnlyClients();
        } else if (name.equals("")) {
            return Service.instance().userFindId(id);
        } else {
            return Service.instance().userFindName(name);
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public User read(@PathParam("id") String id) throws Exception {
        return Service.instance().userRead(id);
    }
    
    @POST
    @Consumes (MediaType.APPLICATION_JSON)
    public void register(User user) {
        try {
            Service.instance().userRegister(user);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void modify(User user) throws Exception {
        Service.instance().userUpdate(user);
    }
}
