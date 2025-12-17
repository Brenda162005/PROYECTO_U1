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

    // --- IMPORTANTE: Esto arregla el botón de "Publicar" ---
    @SerializedName("esta_publicada") 
    private boolean estaPublicada; 

    @SerializedName("imagen")
    private String imagen;
    
    @SerializedName("preguntas") 
    private ArrayList<Pregunta> preguntas;


    public Encuesta() {
        this.preguntas = new ArrayList<>();
    }

    
    public Encuesta(int id, String titulo, String descripcion, boolean estaPublicada, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estaPublicada = estaPublicada;
        this.imagen = imagen;
        this.preguntas = new ArrayList<>();
    }

    
    public Encuesta(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estaPublicada = false; // Por defecto no publicada
        this.preguntas = new ArrayList<>();
    }

    
    public void agregarPregunta(Pregunta p) {
        if (this.preguntas == null) {
            this.preguntas = new ArrayList<>();
        }
        this.preguntas.add(p);
    }

    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isEstaPublicada() { return estaPublicada; }
    public void setEstaPublicada(boolean estaPublicada) { this.estaPublicada = estaPublicada; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public ArrayList<Pregunta> getPreguntas() { return preguntas; }
    public void setPreguntas(ArrayList<Pregunta> preguntas) { this.preguntas = preguntas; }

    @Override
    public String toString() {
        return titulo; 
    }
}