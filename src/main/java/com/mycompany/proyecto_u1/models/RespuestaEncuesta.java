package com.mycompany.proyecto_u1.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Representa una encuesta completada por un usuario.
 */
public class RespuestaEncuesta implements Serializable {
    
    // Para saber qué encuesta se contestó (ej. "Encuesta de Prueba 1")
    private String tituloEncuesta;
    
    // Para saber qué usuario la contestó (ej. "usuario")
    private String nombreUsuario;
    
    // La lista de todas sus respuestas
    private ArrayList<Respuesta> respuestas;

    // Constructor
    public RespuestaEncuesta(String tituloEncuesta, String nombreUsuario) {
        this.tituloEncuesta = tituloEncuesta;
        this.nombreUsuario = nombreUsuario;
        this.respuestas = new ArrayList<>();
    }
    
    // Método útil para añadir respuestas
    public void agregarRespuesta(Respuesta r) {
        this.respuestas.add(r);
    }
    
    // Getters y Setters (necesarios para Gson)
    public String getTituloEncuesta() {
        return tituloEncuesta;
    }

    public void setTituloEncuesta(String tituloEncuesta) {
        this.tituloEncuesta = tituloEncuesta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
        @Override
public String toString() {
    return this.tituloEncuesta; // La JList usará esto para mostrar el texto
}
}
