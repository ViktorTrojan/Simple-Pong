package src;

import javax.imageio.ImageIO;
import src.menu.Menu;

public class Main {

    public static Main i;
    public Menu menu;

    public Main() {
        i = this;
        createMenu();
    }

    public void createMenu() {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                menu = new Menu();
                menu.setTitle("Main Menu"); // set the Title
                try {
                    menu.setIconImage(ImageIO.read(getClass().getResource("/src/res/images/Logo.png")));
                } catch (Exception ex) {
                    System.out.println("Couldn't Load Logo for Main Menu!");
                }
                menu.setLocationRelativeTo(null); // Center Window on Screen
                menu.setVisible(true); // Make Window Visible
            }
        });
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
