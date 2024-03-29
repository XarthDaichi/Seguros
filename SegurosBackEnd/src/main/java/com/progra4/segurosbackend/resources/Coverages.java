/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.Category;
import com.progra4.segurosbackend.logic.Coverage;
import com.progra4.segurosbackend.logic.Service;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/coverages")
@PermitAll
public class Coverages {
    
    /**
     * 
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Coverage> find(@DefaultValue("") @QueryParam("id") String id) {
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Coverage read(@PathParam("id") String id) throws Exception {
        return Service.instance().coverageFind(id);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
//    @RolesAllowed({"true"})
    public void insert(Coverage coverage) throws Exception {
        Service.instance().CoverageCreate(coverage, coverage.getCategoryId());
    }
}
