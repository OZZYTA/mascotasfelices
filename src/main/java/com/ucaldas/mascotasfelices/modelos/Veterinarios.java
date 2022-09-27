package com.ucaldas.mascotasfelices.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Veterinarios")
public class Veterinarios {
    @Id
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String tarjetaprof;
    private String password;
    private Boolean estado;

    
    public Veterinarios() {
    }


    public Veterinarios(int id, String nombre, String apellido, String direccion, String telefono, String tarjetaprof, String password, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tarjetaprof = tarjetaprof;
        this.password=password;
        this.estado=true;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getTarjetaprof() {
        return tarjetaprof;
    }


    public void setTarjetaprof(String tarjetaprof) {
        this.tarjetaprof = tarjetaprof;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Boolean getEstado() {
        return estado;
    }


    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
}
