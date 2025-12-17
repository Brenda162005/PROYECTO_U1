package com.mycompany.proyecto_u1;

import com.mycompany.proyecto_u1.models.Encuesta;
import com.mycompany.proyecto_u1.services.EncuestaService;
import java.util.ArrayList;
import com.mycompany.proyecto_u1.models.Usuario;
import com.mycompany.proyecto_u1.models.RespuestaEncuesta; 
import com.mycompany.proyecto_u1.services.RespuestaService;
<<<<<<< HEAD

=======
// import com.mycompany.proyecto_u1.GraficasPanel; // Si no lo usas aquí, se puede quitar
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c


    public class VerEncuestasPanel extends javax.swing.JPanel {
        private Usuario usuario;
        private UsuarioFrame parentFrame;


    public VerEncuestasPanel(UsuarioFrame parentFrame, Usuario usuario) {
            initComponents();
            this.parentFrame = parentFrame;
            this.usuario = usuario;
            cargarEncuestas(); 
        }   

    public void cargarEncuestas() {

            RespuestaService respuestaService = new RespuestaService();
            EncuestaService encuestaService = new EncuestaService();


            ArrayList<RespuestaEncuesta> todasLasRespuestas = respuestaService.getRespuestas();


            ArrayList<String> titulosContestados = new ArrayList<>();
            for (RespuestaEncuesta r : todasLasRespuestas) {
                if (r.getNombreUsuario().equals(this.usuario.getNombreUsuario())) {
                    titulosContestados.add(r.getTituloEncuesta());
                }
            }

            panelGrid.removeAll();


            ArrayList<Encuesta> todasLasEncuestas = encuestaService.getEncuestas();

            for (Encuesta e : todasLasEncuestas) {
                // Solo mostrar si está publicada Y el usuario NO la ha contestado
                if (e.isEstaPublicada() && !titulosContestados.contains(e.getTitulo())) {
                   EncuestaCardPanel card = new EncuestaCardPanel(this.parentFrame, this.usuario, e, this);
                    panelGrid.add(card);
                }
            }

            panelGrid.revalidate();
            panelGrid.repaint();
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelGrid = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1090, 500));
        setPreferredSize(new java.awt.Dimension(1500, 900));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Encuestas Disponibles para Contestar:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        panelGrid.setLayout(new java.awt.GridLayout(0, 3, 10, 10));
        jScrollPane1.setViewportView(panelGrid);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1280, 640));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelGrid;
    // End of variables declaration//GEN-END:variables
}
