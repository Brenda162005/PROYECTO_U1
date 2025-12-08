package com.mycompany.proyecto_u1.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyecto_u1.models.Encuesta;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
// Imports de Apache HttpClient 5
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;

public class EncuestaService {

    
    private final String URL_BASE = "http://localhost/PROYECTO_U1/";
    
    // Ruta local por si guardar temporales ...
    public static final String IMG_PATH = "src/main/java/com/mycompany/proyecto_u1/images/";

    
    // -CREAR ENCUESTA (JSON de Preguntas + Archivo de Imagen) ---
    public boolean crearEncuesta(Encuesta encuesta, File archivoImagen) {
        try {
           
            Gson gson = new Gson();
            String preguntasJson = gson.toJson(encuesta.getPreguntas());
            
           
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            
            
            builder.addPart("titulo", new StringBody(encuesta.getTitulo(), ContentType.TEXT_PLAIN));
            builder.addPart("descripcion", new StringBody(encuesta.getDescripcion(), ContentType.TEXT_PLAIN));
          
            builder.addPart("esta_publicada", new StringBody(String.valueOf(encuesta.isEstaPublicada()), ContentType.TEXT_PLAIN));
            
           
            builder.addPart("preguntas_json", new StringBody(preguntasJson, ContentType.APPLICATION_JSON));

            
            if (archivoImagen != null && archivoImagen.exists()) {
                builder.addPart("imagen", new FileBody(archivoImagen));
            }

            
            HttpEntity entidad = builder.build();
            String respuesta = Request.post(URL_BASE + "encuesta_insertar.php")
                .body(entidad)
                .execute().returnContent().asString();

            System.out.println("Respuesta Crear Encuesta: " + respuesta);
            
            return respuesta.contains("success");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //  OBTENER ENCUESTAS (GET)
    
    public ArrayList<Encuesta> getEncuestas() {
        ArrayList<Encuesta> lista = new ArrayList<>();
        try {
            String jsonRespuesta = Request.get(URL_BASE + "encuestas_get.php")
                .execute().returnContent().asString();
            
            
            if (jsonRespuesta != null && jsonRespuesta.startsWith("[")) {
                Gson gson = new Gson();
                Type listaType = new TypeToken<ArrayList<Encuesta>>(){}.getType();
                lista = gson.fromJson(jsonRespuesta, listaType);
            } else {
                System.out.println("Respuesta extraña al obtener encuestas: " + jsonRespuesta);
            }
            
        } catch (Exception e) {
            System.out.println("Error al obtener encuestas: " + e.getMessage());
        }
        return lista;
    }
    
    
    public boolean publicarEncuesta(String tituloEncuesta) {
        try {
            
            String resp = Request.post(URL_BASE + "encuesta_publicar.php")
                .bodyForm(Form.form().add("titulo", tituloEncuesta).build())
                .execute().returnContent().asString();
            return resp.contains("success");
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean borrarEncuesta(String tituloEncuesta) {
        try {
           
            String resp = Request.post(URL_BASE + "encuesta_borrar.php")
                .bodyForm(Form.form().add("titulo", tituloEncuesta).build())
                .execute().returnContent().asString();
            return resp.contains("success");
        } catch (Exception e) {
            return false;
        }
    }
}