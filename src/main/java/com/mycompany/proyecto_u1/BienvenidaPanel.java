package com.mycompany.proyecto_u1;

import com.mycompany.proyecto_u1.models.Usuario;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BienvenidaPanel extends javax.swing.JPanel {
    private final String URL_BASE_FOTOS = "http://localhost/PROYECTO_U1/imagenes/";

public BienvenidaPanel(Usuario usuario) {
        initComponents();

        
        labelMensaje.setText("¡Bienvenido, " + usuario.getNombreUsuario() + "!" + "  Selecciona 'Encuestas' para comenzar :)");

        
        cargarFotoPerfil(usuario.getImagen());
    }
    
    private void cargarFotoPerfil(String nombreImagen) {
        try {
           
            String imagenABuscar = "no_image_2.jpg"; // Por defecto
            
            
            if (nombreImagen != null && !nombreImagen.isEmpty()) {
                imagenABuscar = nombreImagen;
            }

            // El puente hacia XAMPP
            
            URL url = new URL(URL_BASE_FOTOS + imagenABuscar);
            
            
            Image imagenWeb = ImageIO.read(url);
            
            
            if (imagenWeb != null) {
                // (Opcional) Escalamos la imagen para que quepa bien
                /* Image imagenEscalada = imagenWeb.getScaledInstance(150, 150, Image.SCALE_SMOOTH); 
                   panelFotoPerfil.setIcon(new ImageIcon(imagenEscalada)); */
                
                // Sin escalar (como lo tenías):
                panelFotoPerfil.setIcon(new ImageIcon(imagenWeb));
                panelFotoPerfil.repaint();
            }
            
        } catch (Exception e) {
            System.out.println("No se pudo cargar la imagen desde XAMPP: " + e.getMessage());
            
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFotoPerfil = new org.edisoncor.gui.panel.PanelImage();
        labelMensaje = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelFotoPerfil.setBackground(new java.awt.Color(204, 204, 204));
        panelFotoPerfil.setBorder(new javax.swing.border.MatteBorder(null));
        panelFotoPerfil.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout panelFotoPerfilLayout = new javax.swing.GroupLayout(panelFotoPerfil);
        panelFotoPerfil.setLayout(panelFotoPerfilLayout);
        panelFotoPerfilLayout.setHorizontalGroup(
            panelFotoPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );
        panelFotoPerfilLayout.setVerticalGroup(
            panelFotoPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        add(panelFotoPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 360, 380));

        labelMensaje.setFont(new java.awt.Font("Roboto Condensed ExtraBold", 2, 24)); // NOI18N
        labelMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensaje.setText("jLabel1");
        add(labelMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMensaje;
    private org.edisoncor.gui.panel.PanelImage panelFotoPerfil;
    // End of variables declaration//GEN-END:variables
}
