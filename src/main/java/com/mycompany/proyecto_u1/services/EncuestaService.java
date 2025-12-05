package com.mycompany.proyecto_u1.services;

import com.mycompany.proyecto_u1.db.Conexion;
import com.mycompany.proyecto_u1.models.Encuesta;
import com.mycompany.proyecto_u1.models.Pregunta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EncuestaService {

    
    public static final String IMG_PATH = "src/main/java/com/mycompany/proyecto_u1/images/";
    
    
    private Conexion conexion = new Conexion();

    
    public ArrayList<Encuesta> getEncuestas() {
        ArrayList<Encuesta> lista = new ArrayList<>();
        String sql = "SELECT * FROM encuestas";

        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                boolean publicada = rs.getBoolean("esta_publicada");
                String imagen = rs.getString("imagen");
                int idEncuesta = rs.getInt("id"); 
                
                Encuesta e = new Encuesta(titulo, descripcion);
                e.setEstaPublicada(publicada);
                e.setImagen(imagen);

                
                ArrayList<Pregunta> preguntas = obtenerPreguntas(con, idEncuesta, null);
                e.setPreguntas(preguntas);

                lista.add(e);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error en getEncuestas: " + e.getMessage());
        }

        return lista;
    }

   
    public boolean crearEncuesta(Encuesta encuesta) {
        String sqlEncuesta = "INSERT INTO encuestas (titulo, descripcion, esta_publicada, imagen) VALUES (?, ?, ?, ?)";
        Connection con = null;
        
        try {
            con = conexion.open();
            
            con.setAutoCommit(false); 

          
            PreparedStatement ps = con.prepareStatement(sqlEncuesta, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, encuesta.getTitulo());
            ps.setString(2, encuesta.getDescripcion());
            ps.setBoolean(3, encuesta.isEstaPublicada());
            ps.setString(4, encuesta.getImagen());
            
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                
                ResultSet rsKeys = ps.getGeneratedKeys();
                if (rsKeys.next()) {
                    int idEncuesta = rsKeys.getInt(1);
                    
                   
                    if (encuesta.getPreguntas() != null) {
                        guardarPreguntas(con, encuesta.getPreguntas(), idEncuesta, null);
                    }
                }
                rsKeys.close();
            }
            
            ps.close();
            con.commit(); 
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Error al crear encuesta: " + e.getMessage());
            try {
                if (con != null) con.rollback(); // Deshacer cambios si algo falló
            } catch (Exception ex) { }
            return false;
        }
    }


    private ArrayList<Pregunta> obtenerPreguntas(Connection con, int idEncuesta, Integer idPadre) throws Exception {
        ArrayList<Pregunta> listaPreguntas = new ArrayList<>();
        
        String sql;
        if (idPadre == null) {
            sql = "SELECT * FROM preguntas WHERE id_encuesta = ? AND id_pregunta_padre IS NULL";
        } else {
            sql = "SELECT * FROM preguntas WHERE id_encuesta = ? AND id_pregunta_padre = ?";
        }

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEncuesta);
        if (idPadre != null) {
            ps.setInt(2, idPadre);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String texto = rs.getString("texto_pregunta");
            int idPregunta = rs.getInt("id");

           
            Pregunta p = new Pregunta(texto);
            
           
            ArrayList<Pregunta> subPreguntas = obtenerPreguntas(con, idEncuesta, idPregunta);
            p.setSubPreguntas(subPreguntas);
            
            listaPreguntas.add(p);
        }
        
        rs.close();
        ps.close();
        
        return listaPreguntas;
    }

   
    private void guardarPreguntas(Connection con, ArrayList<Pregunta> preguntas, int idEncuesta, Integer idPadre) throws Exception {
        String sql = "INSERT INTO preguntas (id_encuesta, texto_pregunta, id_pregunta_padre) VALUES (?, ?, ?)";
        
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        for (Pregunta p : preguntas) {
            ps.setInt(1, idEncuesta);
            ps.setString(2, p.getTexto());
            
            if (idPadre == null) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, idPadre);
            }
            
            ps.executeUpdate();
            
            // Obtener ID de la pregunta recién insertada
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idPreguntaGenerado = rs.getInt(1);
                
                
                if (p.getSubPreguntas() != null && !p.getSubPreguntas().isEmpty()) {
                    guardarPreguntas(con, p.getSubPreguntas(), idEncuesta, idPreguntaGenerado);
                }
            }
            rs.close();
        }
        ps.close();
    }
    
   
    public boolean publicarEncuesta(String tituloEncuesta) {
        String sql = "UPDATE encuestas SET esta_publicada = TRUE WHERE titulo = ?";
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tituloEncuesta);
            
            int rows = ps.executeUpdate();
            
            ps.close();
            con.close();
            
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error al publicar encuesta: " + e.getMessage());
            return false;
        }
    }

    
    public boolean borrarEncuesta(String tituloEncuesta) {
        String sql = "DELETE FROM encuestas WHERE titulo = ?";
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tituloEncuesta);
            
            int rows = ps.executeUpdate();
            
            ps.close();
            con.close();
            
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error al borrar encuesta: " + e.getMessage());
            return false;
        }
    }
}