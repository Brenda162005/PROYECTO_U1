package com.mycompany.proyecto_u1;

import com.mycompany.proyecto_u1.models.Usuario;
import com.mycompany.proyecto_u1.models.Respuesta;
import com.mycompany.proyecto_u1.models.RespuestaEncuesta;
import com.mycompany.proyecto_u1.services.RespuestaService;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;


public class AcusePanel extends javax.swing.JPanel {
    private Usuario usuario;
    
    public AcusePanel(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        areaAcuse.setFont(new java.awt.Font("Consolas", java.awt.Font.PLAIN, 14));
        
        cargarEncuestasContestadas();

        listEncuestasContestadas.addListSelectionListener(this::mostrarDetalleAcuse);
    }

    private void cargarEncuestasContestadas() {
        
        RespuestaService service = new RespuestaService();
        
        
        ArrayList<RespuestaEncuesta> todasLasRespuestas = service.getRespuestas();

        DefaultListModel<RespuestaEncuesta> modelo = new DefaultListModel<>();

       
        for (RespuestaEncuesta r : todasLasRespuestas) {
            if (r.getNombreUsuario().equals(this.usuario.getNombreUsuario())) {
                modelo.addElement(r);
            }
        }

        listEncuestasContestadas.setModel(modelo);
    }

    
    private void mostrarDetalleAcuse(ListSelectionEvent evt) {
        if (!evt.getValueIsAdjusting()) {
            RespuestaEncuesta respuestaSeleccionada = listEncuestasContestadas.getSelectedValue();

            if (respuestaSeleccionada == null) {
                areaAcuse.setText("");
                return;
            }

            StringBuilder acuse = new StringBuilder();
            String separador = "====================================================================\n";

            acuse.append(separador);
            acuse.append("\t\t\tACUSE DE ENCUESTA\n");
            acuse.append(separador);
            acuse.append(String.format("ENCUESTA: \t%s\n", respuestaSeleccionada.getTituloEncuesta()));
            acuse.append(String.format("USUARIO: \t%s\n", respuestaSeleccionada.getNombreUsuario()));
            acuse.append(separador);
            acuse.append("\n\t\t\tTUS RESPUESTAS\n\n");

            double sumaCalificaciones = 0;
            int i = 1;

            for (Respuesta r : respuestaSeleccionada.getRespuestas()) {
                
                acuse.append(String.format("%d. %s\n", i++, r.getTextoPregunta()));
                
                acuse.append(String.format("    Tu puntuación: %d / 5.0 \n", r.getPuntuacion()));
                sumaCalificaciones += r.getPuntuacion();
            }

            // Calcular el promedio. Evitar división por cero.
            double promedio = 0;
            if (!respuestaSeleccionada.getRespuestas().isEmpty()) {
                promedio = sumaCalificaciones / respuestaSeleccionada.getRespuestas().size();
            }

            acuse.append(separador);
            acuse.append("\t\t\tCALIFICACIÓN FINAL\n");
            acuse.append(separador);
            acuse.append(String.format("PROMEDIO TOTAL: \t%.2f / 5.00\n", promedio));
            
            // Mostrarlo en el JTextArea
            areaAcuse.setText(acuse.toString());
            areaAcuse.setCaretPosition(0); 
        }
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listEncuestasContestadas = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaAcuse = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(241, 255, 248));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Mis Encuestas Contestadas:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        listEncuestasContestadas.setBackground(new java.awt.Color(255, 252, 221));
        listEncuestasContestadas.setBorder(null);
        jScrollPane1.setViewportView(listEncuestasContestadas);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1240, 120));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Detalle de Respuestas (Acuse):");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        areaAcuse.setEditable(false);
        areaAcuse.setBackground(new java.awt.Color(238, 250, 238));
        areaAcuse.setColumns(20);
        areaAcuse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        areaAcuse.setRows(5);
        areaAcuse.setOpaque(false);
        jScrollPane2.setViewportView(areaAcuse);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 1240, 320));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaAcuse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<RespuestaEncuesta> listEncuestasContestadas;
    // End of variables declaration//GEN-END:variables
}
