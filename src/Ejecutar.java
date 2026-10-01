import javax.swing.SwingUtilities;

public class Ejecutar {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                Configuracion configurar = new Configuracion();
                configurar.setVisible(true);
            }
        });
        
    }
}

