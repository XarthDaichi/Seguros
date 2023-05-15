package com.progra4.Seguros.presentation.login;
import com.progra4.Seguros.logic.*;

public class Model {
    User current;
    
    public Model() {
        this.reset();
    }
    
    public void reset(){
        setCurrent(new User());        
    }
    
    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }
}
