package com.progra4.Seguros.presentation.client.policyInfo;

import com.progra4.Seguros.logic.*;

public class Model {
    Policy current;

    public Model() {
        this.reset();
    }
    
    public void reset() {
        current = new Policy();
    }

    public Policy getCurrent() {
        return current;
    }

    public void setCurrent(Policy current) {
        this.current = current;
    }
}
