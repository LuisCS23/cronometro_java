import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import javax.swing.Timer;
import java.util.TimerTask;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;//para curvar los bordes
import java.awt.geom.RoundRectangle2D;
import java.applet.AudioClip;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;

import java.io.File;



public class Cronometro extends JFrame {
    private JButton btnPlay, btnPausa, btnStop, btnVolver;
    private JLabel lblMostrarReloj;
    private int horas, minutos, segundos;
    private boolean activo=false;
    private boolean modoRegresivo;
    private Timer reloj;
    private Clip clip;
    private Font fuenteDigital;

    Color colorEncabezado = Color.BLACK;

    //Constructor

    public Cronometro(){

        
        
        setTitle("Temporizador");
        setSize(330,65);

        //Este código sirve para ubicar el cronómetro en la esquina superior derecha de la pantalla
        Toolkit toolkit = Toolkit.getDefaultToolkit();//Solicita información del sistema
        Dimension tamanoPantalla = toolkit.getScreenSize();//Le pide al sistema el tamaño total de la pantalla actual (resolución en píxeles). Guarda tanto el ancho (screenSize.width) como el alto (screenSize.height).
        int x = tamanoPantalla.width-getWidth();//Calcula la coordenada horizontal (X). Toma el ancho total de la pantalla y le resta el ancho de la ventana cronometro (getWidth())
        int y = 0;
        /*int x=0;
        int y=0;*/
        setLocation(x,y);
        //setLocation(1210, 5);//ubicación del frame en la pantalla
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);//Color del Jframe
        getRootPane().getContentPane().setBackground(Color.BLACK);
        setAlwaysOnTop(true);
        setUndecorated(true);//oculta la barra de título
        setShape(new RoundRectangle2D.Double(0,0, getWidth(), getHeight(),20,20));//curvar los bordes del cronómetro
       //setLayout(null);
        setIconImage(new ImageIcon(
            getClass().getResource("resources/ICONO.png")
        ).getImage());

        //Font fuenteDigital;
        try {

            fuenteDigital = Font.createFont(
                Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/DS-DIGIB.TTF")
            ).deriveFont(60f);

            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(fuenteDigital);

        } catch (Exception e) {
            e.printStackTrace();
        }
       /* try{
            fuenteDigital = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("fonts/DS-DIGIB.ttf")).deriveFont(Font.PLAIN, 60f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuenteDigital);
            System.out.println(
                getClass().getResource("fonts/DS-DIGIB.ttf") //Comprobar si la fuente fue incluida en el JAR
            );
        }catch(Exception e){
            e.printStackTrace();
            fuenteDigital = new Font("Arial", Font.PLAIN,60);
        }*/
        
        /*try{
            fuenteDigital = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/DS-DIGIB.ttf")).deriveFont(Font.PLAIN,60f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuenteDigital);
        }catch(Exception e){
            e.printStackTrace();
            fuenteDigital = new Font("Arial",Font.BOLD,60);
        }*/

        lblMostrarReloj = new JLabel("00:00:00");
        lblMostrarReloj.setFont(fuenteDigital);
        lblMostrarReloj.setForeground(Color.WHITE);
        lblMostrarReloj.setHorizontalAlignment(JLabel.LEFT);
      
        btnPlay = new JButton("►");
        btnPausa = new JButton("||");
        btnStop = new JButton("▄");
        btnVolver = new JButton("←");

        btnPlay.setBackground(Color.GREEN);
        btnPlay.setBounds(220, 6, 46, 25);
        btnPlay.setFont(new Font("Arial", Font.BOLD, 12));
        btnPlay.setToolTipText("Iniciar");

        btnPausa.setBackground(Color.ORANGE);
        btnPausa.setBounds(220,35,46,25);
        btnPausa.setFont(new Font("Arial", Font.BOLD, 12));
        btnPausa.setToolTipText("Pausar");

        btnStop.setBackground(Color.RED);
        btnStop.setBounds(267,6,46,25);
        btnStop.setFont(new Font("Arial",Font.BOLD,12));
        btnStop.setToolTipText("Detener");

        btnVolver.setBackground(Color.darkGray);
        btnVolver.setFont(new Font("Arial", Font.BOLD,12));
        btnVolver.setBounds(267, 35, 46, 25);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setToolTipText("Volver");
        

        add(btnPlay, BorderLayout.WEST);
        add(btnPausa, BorderLayout.WEST);
        add(btnStop, BorderLayout.WEST);
        add(btnVolver,BorderLayout.WEST);

        /*JLayeredPane contenedorEtiquetasBtn = new JLayeredPane();
        contenedorEtiquetasBtn.setPreferredSize(new Dimension(130,50));
        contenedorEtiquetasBtn.add(lblEtiquetas);
        contenedorEtiquetasBtn.setComponentZOrder(lblEtiquetas, 0);
        add(contenedorEtiquetasBtn, BorderLayout.CENTER);*/

        /*JPanel panelBotones = new JPanel();
        panelBotones.add(btnPlay);
        panelBotones.add(btnPausa);
        panelBotones.add(btnStop);
        add(panelBotones, BorderLayout.EAST);*/

        //Panel que contiene el label que simula el reloj
        JPanel panelTiempo = new JPanel();
        panelTiempo.add(lblMostrarReloj);//Agregamos lblMostrarReloj al panel
        panelTiempo.setBackground(Color.BLACK);
        add(panelTiempo, BorderLayout.WEST);

        //Acciones de los botones:
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                 Configuracion configuracion = new Configuracion();
                 stopCronometro();
                 dispose();
                 configuracion.setVisible(true);
                 
            }
        });

        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                iniciarTemporizador();
                btnPlay.setEnabled(false);
            }
        });

        btnPausa.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
                pausarCronometro();
                btnPlay.setEnabled(true);
           } 
        });

        btnStop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                stopCronometro();
                btnPlay.setEnabled(true);
            }
        });
        getRootPane().setDefaultButton(btnPlay);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){
                Configuracion configuracion = new Configuracion();
                stopCronometro();
                dispose();
                configuracion.setVisible(true);
            } 

        });
    }//Aquí termina el constructor



    //Métodos para el cronómetro
   /*  private void iniciarCronometro(){
        activo = true;
        btnPlay.setBackground(Color.WHITE);
        btnPausa.setBackground(Color.YELLOW);
        btnStop.setBackground(Color.RED);
        reloj = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                segundos++;
                minutos = (segundos%3600)/60;
                horas = segundos/3600;
                int seg = segundos%60;
                lblMostrarReloj.setText(String.format("%02d:%02d:%02d",horas,minutos,seg));
            }
        });
        reloj.start();
    }*/
    
    private void iniciarTemporizador(){
        activo = true;
        btnPlay.setBackground(Color.WHITE);
        btnPausa.setBackground(Color.ORANGE);
        btnStop.setBackground(Color.RED);

        reloj = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (modoRegresivo) {
                    if (horas == 0 && minutos == 0 && segundos == 0) {
                        reloj.stop();
                        lblMostrarReloj.setText("00:00:00");
                       // JOptionPane.showMessageDialog(null, "Tiempo finalizado");
                        //Configuracion configuracion = new Configuracion();
                        VentanaNotificacion ventanaNot = new VentanaNotificacion();
                        dispose();
                        //configuracion.setVisible(true);
                        ventanaNot.setVisible(true);
                        return;
                    }
                    if(horas == 0 & minutos == 5 & segundos == 5){
                        cargarSonido();
                        reproducir();
                    }

                    if (segundos > 0){
                        segundos--;
                    }else{
                        if (minutos > 0){
                            minutos--;
                            segundos = 59;
                        }else if(horas > 0){
                            horas--;
                            minutos = 59;
                            segundos = 59;
                        }
                    }
                }else{
                    segundos++;
                    if (segundos >= 60) {
                        segundos = 0;
                        minutos++;
                        if (minutos >= 60) {
                            minutos = 0;
                            horas++;
                        }
                    }
                }
                lblMostrarReloj.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
            }
        });
        reloj.start();
    }
    

    private void pausarCronometro(){
        activo = false;
        btnPausa.setBackground(Color.WHITE);
        btnPlay.setBackground(Color.GREEN);
        btnStop.setBackground(Color.RED);
        if(reloj != null){
            reloj.stop();
        }
        
    }

    private void stopCronometro(){
        activo = false;
        segundos = 0;
        horas = 0;
        minutos = 0;
        segundos = 0;
        lblMostrarReloj.setText("00:00:00");
        /*try{
            fuenteDigital = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/DS-DIGIB.ttf")).deriveFont(Font.PLAIN,60f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuenteDigital);
        }catch(Exception e){
            e.printStackTrace();
            fuenteDigital = new Font("Arial",Font.BOLD,60);
        }*/
        lblMostrarReloj.setFont(fuenteDigital);
        btnPausa.setBackground(Color.ORANGE);
        btnPlay.setBackground(Color.GREEN);
        if(reloj!=null){
            reloj.stop();
        }
        modoRegresivo=false;
    }
    //Obtiene los datos de la ventana configuración
    public void obtenerDatos(int hrs, int min, int seg, boolean activarModo){
        this.horas = hrs;
        this.minutos = min;
        this.segundos = seg;
        this.modoRegresivo = activarModo;
        lblMostrarReloj.setText(String.format("%02d:%02d:%02d",horas,minutos,seg));
    }
    //Métodos para el sonido
    public void cargarSonido(){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource("resources/cincominutos.wav"));
            clip = AudioSystem.getClip();
            clip.open(audio);
            /*File archivoSonido = new File("D:/Luis/cronometro/5minutos.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivoSonido);
            clip = AudioSystem.getClip();
            clip.open(audio);*/
        }catch(Exception e){
            e.printStackTrace();
        }
      }
  
      public void reproducir(){
        if(clip!=null){
          clip.setFramePosition(0);
          clip.start();
        }
      }
      public void detener(){
        if(clip!=null && clip.isRunning());
        clip.stop();
      }

}