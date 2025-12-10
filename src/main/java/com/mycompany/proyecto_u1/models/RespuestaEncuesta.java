package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class RespuestaEncuesta implements Serializable {
    
    // --- CORRECCIÓN 1: Coincide con la columna 'id_encuesta' de tu tabla ---
    @SerializedName("id_encuesta")
    private int idEncuesta; 
    
    // --- CORRECCIÓN 2: Coincide con la columna 'titulo' de la tabla 'encuestas' ---
    // Usamos 'alternate' por si en algún lado lo llamas diferente, Gson buscará ambos.
    @SerializedName(value = "titulo", alternate = {"tituloEncuesta", "titulo_encuesta"})
    private String tituloEncuesta;
    
    // --- CORRECCIÓN 3: Coincide con la columna 'nombre_usuario' de tu tabla ---
    @SerializedName("nombre_usuario")
    private String nombreUsuario;
    
    @SerializedName("respuestas")
    private ArrayList<Respuesta> respuestas;

    // Constructor vacío
    public RespuestaEncuesta() {
        this.respuestas = new ArrayList<>();
    }

    public RespuestaEncuesta(String tituloEncuesta, String nombreUsuario) {
        this.tituloEncuesta = tituloEncuesta;
        this.nombreUsuario = nombreUsuario;
        this.respuestas = new ArrayList<>();
    }
    
    public void agregarRespuesta(Respuesta r) {
        this.respuestas.add(r);
    }
    
    // Getters y Setters
    public int getIdEncuesta() { return idEncuesta; }
    public void setIdEncuesta(int idEncuesta) { this.idEncuesta = idEncuesta; }

    public String getTituloEncuesta() { return tituloEncuesta; }
    public void setTituloEncuesta(String tituloEncuesta) { this.tituloEncuesta = tituloEncuesta; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public ArrayList<Respuesta> getRespuestas() { return respuestas; }
    public void setRespuestas(ArrayList<Respuesta> respuestas) { this.respuestas = respuestas; }
    
    @Override
    public String toString() {
        return this.tituloEncuesta;
    }
}