package com.ucaldas.mascotasfelices.modelos;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Atenciones")
public class Atenciones {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Mascotas_id")
    private Mascotas mascota;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date fecha;
    
    private String temperatura;
    private String peso;
    private String frecuenciaR;
    private String frecuenciaC;
    private String animo;
    private String recomendaciones;
    
    @ManyToOne
    @JoinColumn(name = "Veterinarios_id")
    private Veterinarios veterinario;

    
    public Atenciones() {
    }

    public Atenciones(Mascotas mascota, Date fecha, String temperatura, String peso, String frecuenciaR,
            String frecuenciaC, String animo, String recomendaciones, Veterinarios veterinario) {
        this.mascota = mascota;
        this.fecha = fecha;
        this.temperatura = temperatura;
        this.peso = peso;
        this.frecuenciaR = frecuenciaR;
        this.frecuenciaC = frecuenciaC;
        this.animo = animo;
        this.recomendaciones = recomendaciones;
        this.veterinario = veterinario;
    }

    public int getId() {
        return id;
    }

    public Veterinarios getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinarios veterinario) {
        this.veterinario = veterinario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mascotas getMascota() {
        return mascota;
    }

    public void setMascota(Mascotas mascota) {
        this.mascota = mascota;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getFrecuenciaR() {
        return frecuenciaR;
    }

    public void setFrecuenciaR(String frecuenciaR) {
        this.frecuenciaR = frecuenciaR;
    }

    public String getFrecuenciaC() {
        return frecuenciaC;
    }

    public void setFrecuenciaC(String frecuenciaC) {
        this.frecuenciaC = frecuenciaC;
    }

    public String getAnimo() {
        return animo;
    }

    public void setAnimo(String animo) {
        this.animo = animo;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public Veterinarios getVeterinarios() {
        return veterinario;
    }

    public void setVeterinarios(Veterinarios veterinarios) {
        this.veterinario = veterinarios;
    }
    
}
