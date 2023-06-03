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
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author diego
 */

@Path("/vehicles")
@PermitAll
public class Vehicles {
    String location = "";
    
    /**
     * 
     * @param id
     * @param brand
     * @param model
     * @param year
     * @return
    */
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<ArrayList<Vehicle>> getAllSorted() throws Exception {
        return Service.instance().selectBrandsAndModels();
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Vehicle read(@PathParam("id") String id) {
        try {
            return Service.instance().getVehicle(Integer.parseInt(id));
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @GET
    @Path("/{id}/flag")
    @Produces("image/png")
    public Response readImage(@PathParam("id") String id) throws IOException {
        File file = new File(location + id);
        Response.ResponseBuilder response = Response.ok((Object) file);
        return response.build();
    }
    
    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vehicle> find(@DefaultValue("") @QueryParam("brand") String brand, @DefaultValue("") @QueryParam("model") String model) throws Exception {
        return Service.instance().findVehicles(brand, model);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(Vehicle v) {
        try {
            Service.instance().vehicleCreate(v);
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("{id}/image")
    public void createImage(@PathParam("id") String id, @FormParam("image") InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(location + id));
            in.transferTo(out);
            out.close();
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
}
