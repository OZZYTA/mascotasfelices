package com.ucaldas.mascotasfelices.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Mascotas")
public class Mascotas {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String color;
    private String especie;
    private String raza;
    private String genero;

    @ManyToOne
    @JoinColumn(name = "Veterinarios_id")
    private Veterinarios veterinario;

    @ManyToOne
    @JoinColumn(name = "duenos_id")
    private duenos dueno;

    public Mascotas() {
    }

    public Mascotas(String nombre, String color, String especie, String raza, String genero, Veterinarios veterinario,
            duenos dueno) {
        this.nombre = nombre;
        this.color = color;
        this.especie = especie;
        this.raza = raza;
        this.genero = genero;
        this.veterinario = veterinario;
        this.dueno = dueno;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Veterinarios getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinarios veterinario) {
        this.veterinario = veterinario;
    }

    public duenos getDueno() {
        return dueno;
    }

    public void setDueno(duenos dueno) {
        this.dueno = dueno;
    }  
    
    
    
}
