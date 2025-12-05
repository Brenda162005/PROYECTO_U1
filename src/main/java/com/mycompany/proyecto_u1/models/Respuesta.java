package com.mycompany.proyecto_u1.models;

import java.io.Serializable;

/**
 * Almacena la respuesta a una única pregunta (simple o sub-pregunta).
 */
public class Respuesta implements Serializable {
    
    // Guardamos el texto de la pregunta para saber a qué respondemos
    private String textoPregunta;
    
    // Guardamos el valor Likert (1-5)
    private int puntuacion; 

    // Constructor
    public Respuesta(String textoPregunta, int puntuacion) {
        this.textoPregunta = textoPregunta;
        this.puntuacion = puntuacion;
    }
    
    // Getters y Setters (necesarios para Gson)
    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
    
    

}
