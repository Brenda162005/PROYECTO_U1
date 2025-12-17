

package com.mycompany.proyecto_u1;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.mycompany.proyecto_u1.LoginFrame;

public class PROYECTO_U1 {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
