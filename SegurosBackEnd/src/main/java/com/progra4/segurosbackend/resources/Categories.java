/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.segurosbackend.resources;

import com.progra4.segurosbackend.logic.Category;
import com.progra4.segurosbackend.logic.Service;
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
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

/**
 *
 * @author diego
 */

@Path("/categories")
@PermitAll
public class Categories {
    /**
     * 
     * @param name
     * @return
     * @throws java.lang.Exception
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Category> find(@DefaultValue("") @QueryParam("name") String name) throws Exception {
        if (name.equals("")) {
            return Service.instance().selectAllCategories();
        }
        return Service.instance().categoriesFind(name);
    }
    
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Category read(@PathParam("id") String id) throws Exception {
        return Service.instance().categoryFind(id);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void insert(Category cat) throws Exception {
        Service.instance().CategoryCreate(cat);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public void modify(Category cat) throws Exception {
        Service.instance().categoryUpdate(cat);
    }
    
    @DELETE
    @Path("/{categoryId}")
    public void delete(@PathParam("categoryId") String categoryId) throws Exception {
        Service.instance().categoryDelete(categoryId);
    }
}
