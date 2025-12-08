package com.mycompany.proyecto_u1.services;

import com.google.gson.Gson;
import com.mycompany.proyecto_u1.models.Usuario;
import java.io.File;
// Imports de Apache HttpClient 5 (Asegúrate de tener la dependencia en pom.xml)
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.entity.mime.StringBody;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;

public class UsuarioService {
    
    
    private final String URL_BASE = "http://localhost/PROYECTO_U1/";

    
    public Usuario validarUsuario(String nombreUsuario, String password) {
        Usuario user = null;
        try {
            
            String respuesta = Request.post(URL_BASE + "login.php")
                .bodyForm(Form.form()
                    .add("usuario", nombreUsuario)
                    .add("password", password)
                    .build())
                .execute().returnContent().asString();

            
            System.out.println("Respuesta Login: " + respuesta);

            if (!respuesta.contains("\"error\"")) {
                user = new Gson().fromJson(respuesta, Usuario.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    
    public boolean crearUsuario(Usuario usuario, File archivoImagen) {
        try {
            
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            
            
            builder.addPart("usuario", new StringBody(usuario.getNombreUsuario(), ContentType.TEXT_PLAIN));
            builder.addPart("password", new StringBody(usuario.getPassword(), ContentType.TEXT_PLAIN));
            // Convertimos el booleano a "1" o "0" para PHP
            builder.addPart("es_admin", new StringBody(usuario.isEsAdmin() ? "1" : "0", ContentType.TEXT_PLAIN));

            // Agregamos la foto SOLO si existe
            if (archivoImagen != null && archivoImagen.exists()) {
                builder.addPart("imagen", new FileBody(archivoImagen));
            }

            
            HttpEntity entidadCompleta = builder.build();

            
            String respuesta = Request.post(URL_BASE + "usuario_insertar.php")
                .body(entidadCompleta)
                .execute().returnContent().asString();

            System.out.println("Respuesta Registro: " + respuesta);

           
            return respuesta.contains("success");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
