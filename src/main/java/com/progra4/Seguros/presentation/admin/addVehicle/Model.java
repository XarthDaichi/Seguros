package com.progra4.Seguros.presentation.admin.addVehicle;

import com.progra4.Seguros.logic.*;

public class Model {
    Vehicle current;
    
    public Model() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new Vehicle());        
    }

    public Vehicle getCurrent() {
        return current;
    }

    public void setCurrent(Vehicle current) {
        this.current = current;
    }
    
    
}
