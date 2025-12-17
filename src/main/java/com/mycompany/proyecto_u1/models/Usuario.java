package com.mycompany.proyecto_u1.models;

import com.google.gson.annotations.SerializedName; 
import java.io.Serializable;

public class Usuario implements Serializable {
    
    // --- TRADUCTOR DE BASE DE DATOS A JAVA ---
    
    @SerializedName("id") 
    private String id;

    @SerializedName("nombre_usuario") 
    private String nombreUsuario;

    @SerializedName("password")
    private String password;

    @SerializedName("es_admin") 
    private boolean esAdmin; 

    @SerializedName("imagen")
    private String imagen;

   
    public Usuario() {
    }
    
    public Usuario(String nombreUsuario, String password, boolean esAdmin) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.esAdmin = esAdmin;
    }
    
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isEsAdmin() { return esAdmin; }
    public void setEsAdmin(boolean esAdmin) { this.esAdmin = esAdmin; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    @Override
    public String toString() {
        return "Usuario{" + "nombreUsuario=" + nombreUsuario + ", esAdmin=" + esAdmin + '}';
    }
}