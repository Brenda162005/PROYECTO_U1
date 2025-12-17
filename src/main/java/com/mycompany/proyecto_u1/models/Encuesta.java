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

<<<<<<< HEAD

=======
    // --- CONSTRUCTOR 1: Vacío (Necesario) ---
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    public Encuesta() {
        this.preguntas = new ArrayList<>();
    }

<<<<<<< HEAD
    
=======
    // --- CONSTRUCTOR 2: Completo (Usado al cargar desde BD) ---
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    public Encuesta(int id, String titulo, String descripcion, boolean estaPublicada, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estaPublicada = estaPublicada;
        this.imagen = imagen;
        this.preguntas = new ArrayList<>();
    }

<<<<<<< HEAD
    
=======
    // --- CONSTRUCTOR 3: RECUPERADO (Usado en CrearEncuestaFrame) ---
    // Este era el que faltaba y causaba el primer error
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    public Encuesta(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estaPublicada = false; // Por defecto no publicada
        this.preguntas = new ArrayList<>();
    }

<<<<<<< HEAD
    
=======
    // --- MÉTODO RECUPERADO (Usado en PreguntasFrame) ---
    // Este arregla los errores de "cannot find symbol method agregarPregunta"
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    public void agregarPregunta(Pregunta p) {
        if (this.preguntas == null) {
            this.preguntas = new ArrayList<>();
        }
        this.preguntas.add(p);
    }

<<<<<<< HEAD
    
=======
    // --- Getters y Setters ---
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
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