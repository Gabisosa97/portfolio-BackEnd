package com.portfolio.Gabisosa97.Security.Controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Mensaje {

    private String mensaje;

    //Constructor
    public Mensaje() {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
