/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.Service;
import com.progra4.segurosbackend.logic.Vehicle;
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
import java.util.ArrayList;

/**
 *
 * @author diego
 */

@Path("/vehicles")
@PermitAll
public class Vehicles {
    /**
     * 
     * @param brand
     * @param model
     * @param year
     * @return
     
    
    @GET
    @Path("/{brand}/{model}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Vehicle read(@PathParam("brand") String brand, @PathParam("model") String model, @PathParam("year") int year) {
        try {
            return Service.instance().findVehicle(brand, model, year);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<ArrayList<Vehicle>> find(@DefaultValue("") @QueryParam("brand") String brand, @DefaultValue("") @QueryParam("model") String model, @DefaultValue("") @QueryParam("year") int year) throws Exception {
        return Service.instance().selectBrandsAndModels();
    }*/
}
