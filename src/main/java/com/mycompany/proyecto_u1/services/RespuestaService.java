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
    
    // URL Base (Ajusta el puerto si es necesario)
    private final String URL_BASE = "http://localhost/PROYECTO_U1/";

    // --- 1. GUARDAR RESPUESTA (Usa respuesta_insertar.php) ---
    public boolean guardarRespuesta(RespuestaEncuesta respuestaEncuesta) {
        try {
            Gson gson = new Gson();
            String jsonEnvio = gson.toJson(respuestaEncuesta);
            
            System.out.println("JAVA: Enviando respuestas... " + jsonEnvio);

            String resp = Request.post(URL_BASE + "respuesta_insertar.php")
                .bodyString(jsonEnvio, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString(StandardCharsets.UTF_8);
            
            System.out.println("PHP RESPONDIÓ (Guardar): " + resp);

            return resp.contains("success");

        } catch (Exception e) {
            System.out.println("Error al guardar respuesta: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- 2. OBTENER RESULTADOS PARA GRÁFICAS (Usa resultados_get.php) ---
    public Map<String, int[]> getResultadosEncuesta(String tituloEncuesta) {
        Map<String, int[]> resultados = new HashMap<>();
        try {
            // Enviamos el título para saber de qué encuesta queremos la gráfica
            String jsonResp = Request.post(URL_BASE + "resultados_get.php")
                .bodyForm(Form.form().add("titulo", tituloEncuesta).build())
                .execute().returnContent().asString(StandardCharsets.UTF_8);

            // Convertimos el JSON de PHP al Mapa de Java para la gráfica
            Gson gson = new Gson();
            Type tipoMapa = new TypeToken<Map<String, int[]>>(){}.getType();
            resultados = gson.fromJson(jsonResp, tipoMapa);
            
        } catch (Exception e) {
            System.out.println("Error al obtener gráficas: " + e.getMessage());
        }
        return resultados;
    }
    
    // --- 3. OBTENER LISTA DE RESPUESTAS (Usa respuestas_get.php) ---
    public ArrayList<RespuestaEncuesta> getRespuestas() {
        ArrayList<RespuestaEncuesta> lista = new ArrayList<>();
        try {
            String jsonResp = Request.get(URL_BASE + "respuestas_get.php")
                .execute().returnContent().asString(StandardCharsets.UTF_8);
            
            if (jsonResp != null && jsonResp.startsWith("[")) {
                Gson gson = new Gson();
                Type tipoLista = new TypeToken<ArrayList<RespuestaEncuesta>>(){}.getType();
                lista = gson.fromJson(jsonResp, tipoLista);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener lista de respuestas: " + e.getMessage());
        }
        return lista;
    }
}