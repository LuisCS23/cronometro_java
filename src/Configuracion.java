import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Configuracion extends JFrame{
    //JFrame ventanaConfiguracion
    private JLabel lblHoras, lblMinutos, lblSegundos, lblTitulo, lblCopyRight;
    private JTextField txtHoras, txtMinutos, txtSegundos;
    private JButton btnIniciar, btnSalir;
    private JRadioButton rbRegresivo, rbProgresivo;
    private int hrs, min, seg;
    private ButtonGroup panelModo;
    private Font porDefecto, fuenteRbt, fuenteValores, fuenteDigital;
    private Color colorJText = new Color(250,249,235);//Color de fondo de cajas de texto

    //Constructor
    public Configuracion(){
        //Construimos la ventana
        setTitle("Temporizador DCTE 1.5");
        setSize(400,300);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);//desactiva la ubicación automática de los elementos osea se debe poner manualmente con setBounds en cada uno
        setAlwaysOnTop(true);
        setLocation(1050, 10);
        setResizable(false);//No permite cambiar el tamaño manualmente
        this.getContentPane().setBackground(Color.WHITE);
        

        //Fuentes 
        porDefecto = new Font("Arial",Font.BOLD,50);
        fuenteRbt = new Font("Arial",Font.BOLD,18);
        fuenteValores = new Font("Arial",Font.BOLD,16);

        //Posicionamos y creamos los componentes declarados
        lblTitulo = new JLabel("Configurar:");
        lblTitulo.setBounds(20,5,130,35);
        lblTitulo.setFont(new Font("Arial", Font.BOLD,19));
        lblTitulo.setForeground(Color.BLUE);
        add(lblTitulo);

        lblHoras = new JLabel("Hrs:");
        lblHoras.setBounds(40,38,100,30);
        lblHoras.setFont(fuenteValores);
        lblHoras.setForeground(Color.BLACK);
        add(lblHoras);

        lblMinutos = new JLabel("Min:");
        lblMinutos.setBounds(112,38,100,30);
        lblMinutos.setFont(fuenteValores);
        lblMinutos.setForeground(Color.BLACK);
        add(lblMinutos);

        lblSegundos = new JLabel("Seg:");
        lblSegundos.setBounds(187,38,100,30);
        lblSegundos.setFont(fuenteValores);
        lblSegundos.setForeground(Color.BLACK);
        add(lblSegundos);

        lblCopyRight = new JLabel("© _happyCoders 2026 developer: L. Cutipa");
        lblCopyRight.setFont(new Font("Arial",Font.ITALIC,11));
        lblCopyRight.setBounds(8,234,250,30);
        lblCopyRight.setForeground(Color.GRAY);
        add(lblCopyRight);

        txtHoras = new JTextField("0");
        txtHoras.setBounds(20,65,70,60);
        txtHoras.setFont(porDefecto);
        txtHoras.setHorizontalAlignment(JTextField.CENTER);
        txtHoras.setForeground(Color.BLUE);
        txtHoras.setBackground(colorJText);
        add(txtHoras);

        txtMinutos = new JTextField("0");
        txtMinutos.setBounds(100,65,70,60);
        txtMinutos.setHorizontalAlignment(JTextField.CENTER);
        txtMinutos.setFont(porDefecto);
        txtMinutos.setForeground(Color.BLUE);
        txtMinutos.setBackground(colorJText);
        add(txtMinutos);

        txtSegundos = new JTextField("0");
        txtSegundos.setBounds(180,65,70,60);
        txtSegundos.setHorizontalAlignment(JTextField.CENTER);
        txtSegundos.setFont(porDefecto);
        txtSegundos.setForeground(Color.BLUE);
        txtSegundos.setBackground(colorJText);
        add(txtSegundos);

       /*lblRegresivo = new JLabel("Regresivo");
        lblRegresivo.setBounds(20, 70, 100, 30);
        add(lblRegresivo); */

        rbRegresivo = new JRadioButton("Temporizador");
        rbRegresivo.setBounds(50, 140, 160,  43);
        rbRegresivo.setFont(fuenteRbt);
        rbRegresivo.setSelected(true);
        rbRegresivo.setBackground(Color.WHITE);
        rbRegresivo.setForeground(Color.BLACK);
        add(rbRegresivo);
        
        rbProgresivo = new JRadioButton("Cronómetro");
        rbProgresivo.setBounds(50, 170, 150, 43);
        rbProgresivo.setFont(fuenteRbt);
        rbProgresivo.setBackground(Color.WHITE);
        rbProgresivo.setForeground(Color.BLACK);
        add(rbProgresivo);

       /*lblProgresivo = new JLabel("Progresivo");
        lblProgresivo.setBounds(20, 90, 100, 30);
        add(lblProgresivo);*/

        panelModo = new ButtonGroup();
        panelModo.add(rbRegresivo);
        panelModo.add(rbProgresivo);
        //add(panelModo);

        btnIniciar = new JButton("INICIAR");
        btnIniciar.setBounds(260, 35, 100, 100);
        btnIniciar.setBackground(Color.GREEN);
        btnIniciar.setFont(new Font("Arial",Font.BOLD,15));
        add(btnIniciar);

        btnSalir = new JButton("SALIR");
        btnSalir.setBounds(260,140,100,60);
        btnSalir.setBackground(Color.red);
        btnSalir.setFont(new Font("Arial", Font.BOLD,15));
        btnSalir.setForeground(Color.BLACK);
        add(btnSalir);

        rbRegresivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                txtHoras.setEnabled(true);
                txtMinutos.setEnabled(true);
                txtSegundos.setEnabled(true);
            }
        });

        rbProgresivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                txtHoras.setEnabled(false);
                txtMinutos.setEnabled(false);
                txtSegundos.setEnabled(false);
            }
        });

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean activarModo;
                if(txtHoras.getText().isEmpty()){
                    txtHoras.setText("0");
                }
                if(txtMinutos.getText().isEmpty()){
                    txtMinutos.setText("0");
                }
                if(txtSegundos.getText().isEmpty()){
                    txtSegundos.setText("0");
                }
                if(rbProgresivo.isSelected()){
                    activarModo = false;
                }else{
                activarModo = rbRegresivo.isSelected();}

                hrs = Integer.parseInt(txtHoras.getText());
                min = Integer.parseInt(txtMinutos.getText());
                seg = Integer.parseInt(txtSegundos.getText());

                if(hrs ==0 & min==0 & seg==0 & rbRegresivo.isSelected()){
                    JOptionPane.showMessageDialog(null, "¡Ingrese un valor!","Error",JOptionPane.WARNING_MESSAGE);
                }else{
                Cronometro cronometro = new Cronometro();
                cronometro.setVisible(true);
                cronometro.obtenerDatos(hrs, min, seg, activarModo);
                dispose();
                }
            }
        });
        //Método para cambiar el color del botón iniciar al apuntar con el mouse
        btnIniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                btnIniciar.setBackground(new Color(0,153,51));
                btnIniciar.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent e){
                btnIniciar.setBackground(Color.GREEN);
                btnIniciar.setForeground(Color.BLACK);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
            System.exit(0);//cerrar toda la aplicación
           } 
        });

        getRootPane().setDefaultButton(btnIniciar);//se activa el botón iniciar al presionar enter

        //Limpiar txt al hacer click
        txtHoras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                txtHoras.setText("");
            }
        });

        txtMinutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                txtMinutos.setText("");
            }
        });

        txtSegundos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                txtSegundos.setText("");
            }
        });


    }//Aquí termina el constructor

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                //Configuracion configuracion = new Configuracion();
                //configuracion.setVisible(true);
                new Configuracion().setVisible(true);//método abreviado optimizado
            }
        });
    }*/

    
}
