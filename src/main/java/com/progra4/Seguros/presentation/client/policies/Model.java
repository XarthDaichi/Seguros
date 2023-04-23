package com.progra4.Seguros.presentation.client.policies;

import com.progra4.Seguros.logic.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    List<Policy> policies;
    Policy selected;

    public Model() {
        this.reset();
    }

    public void reset(){ 
        List<Policy> rows = new ArrayList<>();        
        selected=null;  
        this.setPolicies(rows);
    }
    
    public void setPolicies(List<Policy> cuentas){
        this.policies =cuentas;    
    }

     public List<Policy> getPolicies() {
        return policies;
    }

    public Policy getSeleccionado() {
        return selected;
    }

    public void setSeleccionado(Policy seleccionado) {
        this.selected = seleccionado;
    }
}
