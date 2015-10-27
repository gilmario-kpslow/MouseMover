package br.edu.fatene.mouseapp;

/**
 * Inicializa a aplicação
 *
 * @author gilmario
 */
public class MouseMover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mover.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        new Mover();
    }

}
