/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.progra4.Seguros.presentation.client.policy;

import com.progra4.Seguros.logic.*;

/**
 *
 * @author lmont
 */
public class Model {
    Policy current;
    
    public Model() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new Policy());        
    }
    
    public Policy getCurrent() {
        return current;
    }

    public void setCurrent(Policy current) {
        this.current = current;
    }
}
