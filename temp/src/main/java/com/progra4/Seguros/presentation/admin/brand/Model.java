package com.progra4.Seguros.presentation.admin.brand;

import com.progra4.Seguros.logic.*;
import java.util.ArrayList;

public class Model {
    ArrayList<ArrayList<Vehicle>> vehicles;

    public Model() {
        this.reset();
    }
    
    public void reset(){ 
        ArrayList<ArrayList<Vehicle>> rows = new ArrayList<>();
        this.setVehicles(rows);
    }

    public ArrayList<ArrayList<Vehicle>> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<ArrayList<Vehicle>> vehicles) {
        this.vehicles = vehicles;
    }
}
