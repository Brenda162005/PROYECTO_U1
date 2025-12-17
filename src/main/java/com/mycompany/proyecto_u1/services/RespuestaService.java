package com.mycompany.proyecto_u1.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyecto_u1.models.RespuestaEncuesta;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

public class RespuestaService {

<<<<<<< HEAD
    
    private final String URL_BASE = "http://localhost/PROYECTO_U1/";

    
=======
    // Asegúrate de que esta IP/URL sea la correcta
    private final String URL_BASE = "http://localhost/PROYECTO_U1/";

    /**
     * Envía una respuesta al servidor para ser guardada.
     */
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    public boolean guardarRespuesta(RespuestaEncuesta respuestaEncuesta) {
        try {
            Gson gson = new Gson();
            String jsonEnvio = gson.toJson(respuestaEncuesta);

            System.out.println("JAVA: Enviando respuestas... " + jsonEnvio);

            // Enviamos la petición POST con el JSON
            String resp = Request.post(URL_BASE + "respuesta_insertar.php")
                    .bodyString(jsonEnvio, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString(StandardCharsets.UTF_8);

            System.out.println("PHP RESPONDIÓ (Guardar): " + resp);

            // Verificamos si el PHP devolvió éxito
            return resp != null && resp.contains("success");

        } catch (Exception e) {
            System.out.println("Error al guardar respuesta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

<<<<<<< HEAD
    
    public Map<String, int[]> getResultadosEncuesta(int idEncuesta) {
        Map<String, int[]> resultados = new HashMap<>();
        try {
            
=======
    /**
     * Descarga los resultados (conteos) para las gráficas.
     * Retorna un Mapa: "Texto Pregunta" -> [arreglo de votos]
     */
    public Map<String, int[]> getResultadosEncuesta(int idEncuesta) {
        Map<String, int[]> resultados = new HashMap<>();
        try {
            // Solicitamos los resultados al PHP enviando el ID de la encuesta
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
            String jsonResp = Request.post(URL_BASE + "resultados_get.php")
                    .bodyForm(Form.form().add("id_encuesta", String.valueOf(idEncuesta)).build())
                    .execute()
                    .returnContent()
                    .asString(StandardCharsets.UTF_8);
            
<<<<<<< HEAD
           
=======
            // Si la respuesta está vacía o es nula, retornamos mapa vacío
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
            if (jsonResp == null || jsonResp.trim().isEmpty()) {
                return resultados;
            }

            Gson gson = new Gson();
<<<<<<< HEAD
           
=======
            // Definimos que el JSON viene como un Mapa de String a Array de int
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
            Type tipoMapa = new TypeToken<Map<String, int[]>>() {}.getType();
            resultados = gson.fromJson(jsonResp, tipoMapa);

        } catch (Exception e) {
            System.out.println("Error al obtener gráficas: " + e.getMessage());
            e.printStackTrace();
        }
        return resultados;
    }

<<<<<<< HEAD
    
=======
    /**
     * Descarga la lista de todas las respuestas individuales (si se necesita para tablas).
     */
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
    public ArrayList<RespuestaEncuesta> getRespuestas() {
        ArrayList<RespuestaEncuesta> lista = new ArrayList<>();
        try {
            String jsonResp = Request.get(URL_BASE + "respuestas_get.php")
                    .execute()
                    .returnContent()
                    .asString(StandardCharsets.UTF_8);

            if (jsonResp != null && jsonResp.startsWith("[")) {
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<ArrayList<RespuestaEncuesta>>() {}.getType();
                lista = gson.fromJson(jsonResp, tipoLista);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener lista de respuestas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}