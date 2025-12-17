package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Pregunta implements Serializable {
    
   
    
    @SerializedName("id")
    private int id;
    
    @SerializedName("id_encuesta")
    private int idEncuesta;
    
    @SerializedName("texto_pregunta") 
    private String texto;
    
    @SerializedName("id_pregunta_padre")
    private Integer idPreguntaPadre; 
    
    
    @SerializedName("sub_preguntas") 
    private ArrayList<Pregunta> subPreguntas;

    

    public Pregunta() {
        this.subPreguntas = new ArrayList<>();
    }

    public Pregunta(String texto) {
        this.texto = texto;
        this.subPreguntas = new ArrayList<>();
    }

    // Constructor completo
    public Pregunta(int id, String texto, ArrayList<Pregunta> subPreguntas) {
        this.id = id;
        this.texto = texto;
        this.subPreguntas = subPreguntas;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getIdPreguntaPadre() {
        return idPreguntaPadre;
    }

    public void setIdPreguntaPadre(Integer idPreguntaPadre) {
        this.idPreguntaPadre = idPreguntaPadre;
    }

    public ArrayList<Pregunta> getSubPreguntas() {
        return subPreguntas;
    }

    public void setSubPreguntas(ArrayList<Pregunta> subPreguntas) {
        this.subPreguntas = subPreguntas;
    }
    
    
    
    public boolean esCompleja() {
        return subPreguntas != null && !subPreguntas.isEmpty();
    }
    
    public void agregarSubPregunta(Pregunta sub) {
        if (this.subPreguntas == null) {
            this.subPreguntas = new ArrayList<>();
        }
        this.subPreguntas.add(sub);
    }

    @Override
    public String toString() {
        return texto; 
    }
}
