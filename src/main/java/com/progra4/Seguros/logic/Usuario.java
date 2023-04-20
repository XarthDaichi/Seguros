package com.progra4.Seguros.logic;

import java.util.Objects;

public class Usuario {
    
    private String cedula;
    private String clave;
    private String nombre;
    private String telefono;
    private String correo;
    private String tarjeta;
    private int tipo;

    public Usuario(String cedula, String clave, String nombre, String telefono, String correo, String tarjeta, int tipo) {
        this.cedula = cedula;
        this.clave = clave;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.tarjeta = tarjeta;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.cedula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.cedula, other.cedula);
    }
    
    
}
