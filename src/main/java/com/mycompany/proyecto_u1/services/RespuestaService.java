package com.mycompany.proyecto_u1.services;

import com.mycompany.proyecto_u1.db.Conexion;
import com.mycompany.proyecto_u1.models.Respuesta;
import com.mycompany.proyecto_u1.models.RespuestaEncuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RespuestaService {
    
    private Conexion conexion = new Conexion();

    /**
     * Obtiene la lista de TODAS las encuestas contestadas (Headers y Detalles).
     */
    public ArrayList<RespuestaEncuesta> getRespuestas() {
        ArrayList<RespuestaEncuesta> lista = new ArrayList<>();
        // Obtenemos encabezados
        String sqlHeader = "SELECT re.id, re.nombre_usuario, e.titulo FROM respuestas_encabezado re JOIN encuestas e ON re.id_encuesta = e.id";

        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sqlHeader);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idEncabezado = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String usuario = rs.getString("nombre_usuario");

                RespuestaEncuesta re = new RespuestaEncuesta(titulo, usuario);
                
                // --- SUB-CONSULTA: Cargar los detalles ---
                String sqlDetalles = "SELECT rd.puntuacion, p.texto_pregunta FROM respuestas_detalle rd JOIN preguntas p ON rd.id_pregunta = p.id WHERE rd.id_respuesta_encabezado = ?";
                PreparedStatement psDet = con.prepareStatement(sqlDetalles);
                psDet.setInt(1, idEncabezado);
                ResultSet rsDet = psDet.executeQuery();
                
                while(rsDet.next()){
                    String textoP = rsDet.getString("texto_pregunta");
                    int puntos = rsDet.getInt("puntuacion");
                    re.agregarRespuesta(new Respuesta(textoP, puntos));
                }
                rsDet.close();
                psDet.close();

                lista.add(re);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error al obtener respuestas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Guarda toda la encuesta contestada en la BD (Encabezado + Detalles).
     */
    public boolean guardarRespuesta(RespuestaEncuesta respuestaEncuesta) {
        Connection con = null;
        try {
            con = conexion.open();
            con.setAutoCommit(false); // Transacción

            // 1. Obtener el ID de la encuesta basándonos en el Título
            int idEncuesta = -1;
            String sqlGetId = "SELECT id FROM encuestas WHERE titulo = ?";
            PreparedStatement psId = con.prepareStatement(sqlGetId);
            psId.setString(1, respuestaEncuesta.getTituloEncuesta());
            ResultSet rsId = psId.executeQuery();
            if (rsId.next()) {
                idEncuesta = rsId.getInt("id");
            }
            rsId.close();
            psId.close();

            if (idEncuesta == -1) {
                con.close();
                return false; 
            }

            // 2. Insertar el Encabezado
            String sqlHeader = "INSERT INTO respuestas_encabezado (id_encuesta, nombre_usuario, fecha_respuesta) VALUES (?, ?, NOW())";
            PreparedStatement psHeader = con.prepareStatement(sqlHeader, Statement.RETURN_GENERATED_KEYS);
            psHeader.setInt(1, idEncuesta);
            psHeader.setString(2, respuestaEncuesta.getNombreUsuario());
            
            psHeader.executeUpdate();
            
            ResultSet rsHeaderKey = psHeader.getGeneratedKeys();
            int idEncabezado = -1;
            if (rsHeaderKey.next()) {
                idEncabezado = rsHeaderKey.getInt(1);
            }
            rsHeaderKey.close();
            psHeader.close();

            // 3. Insertar los Detalles
            String sqlDetalle = "INSERT INTO respuestas_detalle (id_respuesta_encabezado, id_pregunta, puntuacion) VALUES (?, ?, ?)";
            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);

            String sqlBuscaPregunta = "SELECT id FROM preguntas WHERE texto_pregunta = ? AND id_encuesta = ?";
            PreparedStatement psBuscaP = con.prepareStatement(sqlBuscaPregunta);

            for (Respuesta r : respuestaEncuesta.getRespuestas()) {
                // Buscar ID pregunta
                psBuscaP.setString(1, r.getTextoPregunta());
                psBuscaP.setInt(2, idEncuesta);
                ResultSet rsP = psBuscaP.executeQuery();
                
                if (rsP.next()) {
                    int idPregunta = rsP.getInt("id");
                    psDetalle.setInt(1, idEncabezado);
                    psDetalle.setInt(2, idPregunta);
                    psDetalle.setInt(3, r.getPuntuacion());
                    psDetalle.executeUpdate();
                }
                rsP.close();
            }
            
            psBuscaP.close();
            psDetalle.close();

            con.commit();
            con.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            return false;
        }
    }

    // --- MÉTODO PARA GRÁFICAS (ESTE ES EL QUE TE FALTABA) ---
    
    /**
     * Obtiene un mapa con los resultados de una encuesta específica.
     * Clave: Texto de la pregunta.
     * Valor: Array de 5 enteros con el conteo de votos.
     */
    public Map<String, int[]> getResultadosEncuesta(String tituloEncuesta) {
        Map<String, int[]> resultados = new HashMap<>();
        
        // 1. Obtener ID de la encuesta
        int idEncuesta = -1;
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement("SELECT id FROM encuestas WHERE titulo = ?");
            ps.setString(1, tituloEncuesta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idEncuesta = rs.getInt("id");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) { e.printStackTrace(); return resultados; }

        if (idEncuesta == -1) return resultados;

        // 2. Inicializar el mapa con todas las preguntas (para que aparezcan aunque tengan 0 votos)
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement("SELECT texto_pregunta FROM preguntas WHERE id_encuesta = ?");
            ps.setInt(1, idEncuesta);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                resultados.put(rs.getString("texto_pregunta"), new int[5]);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) { e.printStackTrace(); }

        // 3. Llenar con los votos reales
        String sql = "SELECT p.texto_pregunta, rd.puntuacion " +
                     "FROM respuestas_detalle rd " +
                     "JOIN preguntas p ON rd.id_pregunta = p.id " +
                     "JOIN respuestas_encabezado re ON rd.id_respuesta_encabezado = re.id " +
                     "WHERE re.id_encuesta = ?";
        
        try {
            Connection con = conexion.open();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEncuesta);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String pregunta = rs.getString("texto_pregunta");
                int puntos = rs.getInt("puntuacion");
                
                if (resultados.containsKey(pregunta) && puntos >= 1 && puntos <= 5) {
                    resultados.get(pregunta)[puntos - 1]++;
                }
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            System.out.println("Error al obtener resultados para gráfica: " + e.getMessage());
        }
        
        return resultados;
    }
}