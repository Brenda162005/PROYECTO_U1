
package com.mycompany.proyecto_u1.models;
import java.io.Serializable;
import java.util.ArrayList;

public class Pregunta implements Serializable {
   
    private String texto;
    
    // Lista de sub-preguntas. Si está vacía, es una pregunta simple.
    private ArrayList<Pregunta> subPreguntas;

    // Constructor para una pregunta simple (sin sub-preguntas)
    public Pregunta(String texto) {
        this.texto = texto;
        this.subPreguntas = new ArrayList<>(); // Se inicializa vacía
    }

    // Constructor completo (por si se necesita)
    public Pregunta(String texto, ArrayList<Pregunta> subPreguntas) {
        this.texto = texto;
        this.subPreguntas = subPreguntas;
    }

    // --- Getters y Setters ---

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ArrayList<Pregunta> getSubPreguntas() {
        return subPreguntas;
    }

    public void setSubPreguntas(ArrayList<Pregunta> subPreguntas) {
        this.subPreguntas = subPreguntas;
    }
    
    // --- Métodos útiles ---
    
    /**
     * Método rápido para saber si esta pregunta es compleja (tiene sub-preguntas).
     * @return true si tiene sub-preguntas, false si no.
     */
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
