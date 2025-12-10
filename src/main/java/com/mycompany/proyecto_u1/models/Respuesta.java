package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Respuesta implements Serializable {
    
    @SerializedName("id_pregunta")
    private int idPregunta;
    
    
    @SerializedName(value = "textoPregunta", alternate = {"texto_pregunta"})
    private String textoPregunta;
    
    
    @SerializedName(value = "puntuacion", alternate = {"calificacion", "valor"})
    private int puntuacion;

    public Respuesta() {}

    public Respuesta(int idPregunta, String textoPregunta, int puntuacion) {
        this.idPregunta = idPregunta;
        this.textoPregunta = textoPregunta;
        this.puntuacion = puntuacion;
    }

    // Getters y Setters
    public int getIdPregunta() { return idPregunta; }
    public void setIdPregunta(int idPregunta) { this.idPregunta = idPregunta; }

    public String getTextoPregunta() { return textoPregunta; }
    public void setTextoPregunta(String textoPregunta) { this.textoPregunta = textoPregunta; }

    public int getPuntuacion() { return puntuacion; }
    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
    
    @Override
    public String toString() {
        return textoPregunta + ": " + puntuacion;
    }
}
