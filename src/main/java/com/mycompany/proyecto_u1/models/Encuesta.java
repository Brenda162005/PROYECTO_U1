package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Encuesta implements Serializable {
    
   
    
    @SerializedName("id")
    private int id;

    @SerializedName("titulo")
    private String titulo;

    @SerializedName("descripcion")
    private String descripcion;
    
   
    @SerializedName("preguntas")
    private ArrayList<Pregunta> preguntas;

    @SerializedName("esta_publicada") 
    private boolean estaPublicada;

    @SerializedName("imagen")
    private String imagen;

    

    public Encuesta() {
        this.preguntas = new ArrayList<>();
    }

    public Encuesta(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.preguntas = new ArrayList<>();
        this.estaPublicada = false; 
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public boolean isEstaPublicada() {
        return estaPublicada;
    }

    public void setEstaPublicada(boolean estaPublicada) {
        this.estaPublicada = estaPublicada;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
   
    public void agregarPregunta(Pregunta p) {
        if (this.preguntas == null) {
            this.preguntas = new ArrayList<>();
        }
        this.preguntas.add(p);
    }
    
    @Override
    public String toString() {
        return titulo; 
    }
}
