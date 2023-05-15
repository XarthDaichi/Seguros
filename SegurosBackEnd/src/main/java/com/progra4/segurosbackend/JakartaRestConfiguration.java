package com.progra4.segurosbackend;

import com.progra4.segurosbackend.logic.*;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(User.class); 
        classes.add(Rule.class);
        classes.add(Coverage.class);
        classes.add(Category.class);
        classes.add(Policy.class);
        classes.add(Vehicle.class);
        classes.add(Term.class);
        return classes;
    } 
}
