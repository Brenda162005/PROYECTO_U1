
package com.mycompany.proyecto_u1;
<<<<<<< HEAD
import com.mycompany.proyecto_u1.models.Usuario; 
=======
import com.mycompany.proyecto_u1.models.Usuario; // Para saber quién es el admin
>>>>>>> 49e4d57e077d868eb4b56f7a49fea803254f625c
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.mycompany.proyecto_u1.BienvenidaPanel;
public class AdminFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminFrame.class.getName());
    private Usuario usuario;
    public AdminFrame() {
        initComponents();
    }
    
   public AdminFrame(Usuario usuario) {
    initComponents();
    this.usuario = usuario;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setTitle("Panel de Administrador - Bienvenido, " + this.usuario.getNombreUsuario());
    
    
    BienvenidaPanel panelBienvenida = new BienvenidaPanel(this.usuario);

  
    mostrarPanel(panelBienvenida);
    
  
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCrearEncuesta = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menuVerResultados = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        menuCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contentPanel.setLayout(new java.awt.CardLayout());
        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        jMenuBar1.setToolTipText("");

        jMenu1.setText("Encuestas");

        menuCrearEncuesta.setText("Crear Nueva Encuesta");
        menuCrearEncuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCrearEncuestaActionPerformed(evt);
            }
        });
        jMenu1.add(menuCrearEncuesta);
        jMenu1.add(jSeparator1);

        menuVerResultados.setText("Ver Resultados");
        menuVerResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerResultadosActionPerformed(evt);
            }
        });
        jMenu1.add(menuVerResultados);
        jMenu1.add(jSeparator2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Archivo");

        menuCerrarSesion.setText("Cerrar Sesión");
        menuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCerrarSesionActionPerformed(evt);
            }
        });
        jMenu2.add(menuCerrarSesion);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuVerResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerResultadosActionPerformed
        mostrarPanelResultados();
    }//GEN-LAST:event_menuVerResultadosActionPerformed

    private void menuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCerrarSesionActionPerformed
    
    int confirm = JOptionPane.showConfirmDialog(
        this, 
        "¿Estás seguro de que quieres cerrar sesión?", 
        "Cerrar Sesión", 
        JOptionPane.YES_NO_OPTION
    );

    
    if (confirm == JOptionPane.YES_OPTION) {
       
        this.dispose();

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new com.mycompany.proyecto_u1.LoginFrame().setVisible(true);
            }
        });
    }
    
    }//GEN-LAST:event_menuCerrarSesionActionPerformed

    private void menuCrearEncuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCrearEncuestaActionPerformed
        
  
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            // Le pasamos 'this' (el AdminFrame) para que la nueva
            // ventana sepa quién es su "padre", por si acaso
            new CrearEncuestaFrame(AdminFrame.this).setVisible(true);
        }
    });
    }//GEN-LAST:event_menuCrearEncuestaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AdminFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem menuCerrarSesion;
    private javax.swing.JMenuItem menuCrearEncuesta;
    private javax.swing.JMenuItem menuVerResultados;
    // End of variables declaration//GEN-END:variables
/**
 * Método genérico para mostrar cualquier panel en el contentPanel principal
 */
public void mostrarPanel(JPanel panel) {
    contentPanel.removeAll();
    contentPanel.add(panel);
    contentPanel.revalidate();
    contentPanel.repaint();
}


public void mostrarPanelResultados() {
    // Restauramos el título original
    setTitle("Panel de Administrador - Bienvenido, " + this.usuario.getNombreUsuario());
    mostrarPanel(new VerResultadosPanel(this));
}


}
