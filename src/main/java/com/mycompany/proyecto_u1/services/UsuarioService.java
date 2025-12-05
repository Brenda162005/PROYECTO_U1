package com.mycompany.proyecto_u1.services;

import com.mycompany.proyecto_u1.db.Conexion;
import com.mycompany.proyecto_u1.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioService {
    
    
    private Conexion conexion = new Conexion();

   
    public Usuario validarUsuario(String nombreUsuario, String password) {
        Usuario user = null;
        
        
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND password = SHA2(?, 256)";

        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, password); 

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setNombreUsuario(rs.getString("nombre_usuario"));
                user.setPassword(rs.getString("password")); // Viene encriptada
                user.setEsAdmin(rs.getBoolean("es_admin"));
                user.setImagen(rs.getString("imagen"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error en validarUsuario: " + e.getMessage());
        }

        return user;
    }

    
    public boolean crearUsuario(Usuario usuario) {
       
        String sql = "INSERT INTO usuarios (id, nombre_usuario, password, es_admin, imagen) VALUES (UUID(), ?, SHA2(?, 256), ?, ?)";
        
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getPassword()); // Se envía plana, MySQL la encripta
            ps.setBoolean(3, usuario.isEsAdmin());
            ps.setString(4, usuario.getImagen());

            int rowsAffected = ps.executeUpdate();

            ps.close();
            con.close();

            return rowsAffected > 0;

        } catch (Exception e) {
            System.out.println("Error en crearUsuario: " + e.getMessage());
            return false;
        }
    }
    
    
    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Usuario u = new Usuario();
                u.setNombreUsuario(rs.getString("nombre_usuario"));
                u.setPassword(rs.getString("password"));
                u.setEsAdmin(rs.getBoolean("es_admin"));
                u.setImagen(rs.getString("imagen"));
                lista.add(u);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error en getUsuarios: " + e.getMessage());
        }
        
        return lista;
    }
}
