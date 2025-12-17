package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class RespuestaEncuesta implements Serializable {
    
<<<<<<< HEAD
    
    @SerializedName("id_encuesta")
    private int idEncuesta; 
    
    @SerializedName(value = "titulo", alternate = {"tituloEncuesta", "titulo_encuesta"})
    private String tituloEncuesta;
    
    
=======
    // --- CORRECCIÓN 1: Coincide con la columna 'id_encuesta' de tu tabla ---
    @SerializedName("id_encuesta")
    private int idEncuesta; 
    
    // --- CORRECCIÓN 2: Coincide con la columna 'titulo' de la tabla 'encuestas' ---
    // Usamos 'alternate' por si en algún lado lo llamas diferente, Gson buscará ambos.
    @SerializedName(value = "titulo", alternate = {"tituloEncuesta", "titulo_encuesta"})
    private String tituloEncuesta;
    
    // --- CORRECCIÓN 3: Coincide con la columna 'nombre_usuario' de tu tabla ---
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    @SerializedName("nombre_usuario")
    private String nombreUsuario;
    
    @SerializedName("respuestas")
    private ArrayList<Respuesta> respuestas;

<<<<<<< HEAD
   
=======
    // Constructor vacío
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
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
    
<<<<<<< HEAD
    
=======
    // Getters y Setters
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
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