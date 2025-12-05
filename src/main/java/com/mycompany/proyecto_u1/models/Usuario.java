
package com.mycompany.proyecto_u1.models;
import java.io.Serializable;


public class Usuario implements Serializable{
    private String nombreUsuario;
    private String password;
    private boolean esAdmin; 
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public Usuario() {
    }
    
    
    public Usuario(String nombreUsuario, String password, boolean esAdmin) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.esAdmin = esAdmin;
    }
    
   
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombreUsuario=" + nombreUsuario + ", esAdmin=" + esAdmin + '}';
    }
}
