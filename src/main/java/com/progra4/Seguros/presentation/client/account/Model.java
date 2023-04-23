package com.progra4.Seguros.presentation.client.account;
import com.progra4.Seguros.logic.*;

public class Model {
    User current;

    public Model() {
        this.current = new User();
    }

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }
}
