/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.Service;
import com.progra4.segurosbackend.logic.Policy;
import com.progra4.segurosbackend.logic.User;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

/**
 *
 * @author diego
 */

@Path("/policies")
@PermitAll
public class Policies {
    /**
     * 
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Policy> find (@DefaultValue("") @QueryParam("id") String id) throws Exception {
        if (id.equals("")) {
            return Service.instance().selectAllPolicies();
        }
        User u = new User();
        u.setId(id);
        return Service.instance().policiesFind(u);
    }
    
    @GET
    @Path("/{policyId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Policy read (@PathParam("policyId") String policyId) {
        try {
            return Service.instance().policyFind(policyId);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @DELETE
    @Path("/{policId}")
    public void delete(@PathParam("policyId") String policyId) {
        Service.instance().policyDelete(policyId);
    }
    
    @POST
//    @Path("/register")
    @Consumes({MediaType.APPLICATION_JSON})
    public void addPolicy(Policy policy) throws Exception {
        Service.instance().PolicyCreate(policy);
    }
}
