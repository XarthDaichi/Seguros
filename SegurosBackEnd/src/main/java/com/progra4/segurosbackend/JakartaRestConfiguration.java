package com.progra4.segurosbackend;

import com.progra4.segurosbackend.resources.*;
import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api")
@DeclareRoles({"true", "false"})
public class JakartaRestConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(Policies.class);
        classes.add(Categories.class);
        classes.add(Coverages.class);
        classes.add(Login.class);
        classes.add(Vehicles.class);
        classes.add(Clients.class);
        return classes;
    } 
}
