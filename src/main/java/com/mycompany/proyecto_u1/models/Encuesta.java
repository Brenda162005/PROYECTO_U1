
package com.mycompany.proyecto_u1.models;
import java.io.Serializable;
import java.util.ArrayList;


public class Encuesta implements Serializable{
    private String titulo;
    private String descripcion;
    private ArrayList<Pregunta> preguntas;
    private boolean estaPublicada;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    // Constructor
    public Encuesta(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.preguntas = new ArrayList<>();
        this.estaPublicada = false; // Nueva encuesta no está publicada por defecto
    }
    
    
    // --- Getters y Setters ---
    
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
    
   
    public void agregarPregunta(Pregunta p) {
        if (this.preguntas == null) {
            this.preguntas = new ArrayList<>();
        }
        this.preguntas.add(p);
    }
    
    @Override
    public String toString() {
        return titulo; // Para mostrar en listas
    }
}
