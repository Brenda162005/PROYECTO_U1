package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Respuesta implements Serializable {
    
    @SerializedName("textoPregunta")
    private String textoPregunta;
    
    @SerializedName("puntuacion")
    private int puntuacion; 

    public Respuesta(String textoPregunta, int puntuacion) {
        this.textoPregunta = textoPregunta;
        this.puntuacion = puntuacion;
    }
    
    
    public String getTextoPregunta() { return textoPregunta; }
    public void setTextoPregunta(String textoPregunta) { this.textoPregunta = textoPregunta; }

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
}
