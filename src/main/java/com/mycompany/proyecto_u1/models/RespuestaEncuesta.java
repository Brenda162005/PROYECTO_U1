package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class RespuestaEncuesta implements Serializable {
    
    
    @SerializedName("id_encuesta")
    private int idEncuesta; 
    
    @SerializedName(value = "titulo", alternate = {"tituloEncuesta", "titulo_encuesta"})
    private String tituloEncuesta;
    
    
    @SerializedName("nombre_usuario")
    private String nombreUsuario;
    
    @SerializedName("respuestas")
    private ArrayList<Respuesta> respuestas;

   
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