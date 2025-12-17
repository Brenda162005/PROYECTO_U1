package com.mycompany.proyecto_u1;

import com.mycompany.proyecto_u1.models.Encuesta;
import com.mycompany.proyecto_u1.services.RespuestaService;
import java.util.Map;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;

public class GraficasPanel extends javax.swing.JPanel {
    private AdminFrame parentFrame; 
    private Encuesta encuesta;
  
    public GraficasPanel(AdminFrame parent, Encuesta encuesta) {
        initComponents();
        this.parentFrame = parent;
        this.encuesta = encuesta;

        
        this.parentFrame.setTitle("Resultados Gráficos de: " + encuesta.getTitulo());

        
        RespuestaService service = new RespuestaService();
        
        
       Map<String, int[]> resultados = service.getResultadosEncuesta(encuesta.getId());

     
        panelContenidoGraficas.setBorder(new EmptyBorder(10, 10, 10, 10));

        
        if (resultados.isEmpty()) {
            panelContenidoGraficas.add(new javax.swing.JLabel("Esta encuesta aún no tiene respuestas o preguntas."));
        } else {
            for (Map.Entry<String, int[]> entry : resultados.entrySet()) {
                
              
                String tituloOriginal = entry.getKey();
                
                String tituloLimpio = tituloOriginal.replaceAll("^\\d+\\.\\s*", ""); 
                GraficaBarraPanel panelGrafica = new GraficaBarraPanel(tituloLimpio, entry.getValue());
                panelContenidoGraficas.add(panelGrafica);

                panelContenidoGraficas.add(Box.createVerticalStrut(25));
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelContenidoGraficas = new javax.swing.JPanel();

        setBackground(new java.awt.Color(216, 242, 222));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(204, 0, 0));
        btnRegresar.setText("← Regresar a la Lista");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        panelContenidoGraficas.setBackground(new java.awt.Color(255, 255, 255));
        panelContenidoGraficas.setLayout(new javax.swing.BoxLayout(panelContenidoGraficas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelContenidoGraficas);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 1240, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
     this.parentFrame.mostrarPanelResultados();
    }//GEN-LAST:event_btnRegresarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelContenidoGraficas;
    // End of variables declaration//GEN-END:variables
}
