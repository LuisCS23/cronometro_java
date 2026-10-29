import javax.swing.JButton;
import javax.swing.JFrame;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

public class VentanaNotificacion extends JFrame{
  private JLabel lblMensaje, lblMensaje2;
  private JButton btnVolver;
  private Clip clip;

  //Constructor
  public VentanaNotificacion(){
    setTitle("¡Alerta!");
    setSize(1200,900);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setLayout(null);
    getContentPane().setBackground(Color.green);
    setAlwaysOnTop(true);

    setIconImage(new ImageIcon(
        getClass().getResource("resources/ICONO.png")
    ).getImage());

    lblMensaje = new JLabel("!TIEMPO");
    lblMensaje.setFont(new Font("Arial",Font.BOLD,180));
    lblMensaje.setBounds(230,200,800,150);
    add(lblMensaje);

    lblMensaje2 = new JLabel("CUMPLIDO!");
    lblMensaje2.setFont(new Font("Arial",Font.BOLD,180));
    lblMensaje2.setBounds(80,500,1100,150);
    add(lblMensaje2);

    btnVolver = new JButton("VOLVER");
    btnVolver.setFont(new Font("Arial", Font.ITALIC,40));
    btnVolver.setBounds(470,750,200,60);
    btnVolver.setBackground(Color.white);
    add(btnVolver);
    //Llamamos a los métodos del sonido
    cargarSonido();
    reproducir();

  /*lblMensaje = new JLabel("<html>!TIEMPO CUMPLIDO!<html>");
    lblMensaje.setFont(new Font("Arial",Font.BOLD,100));
    lblMensaje.setBounds(60,20,600,600);
    lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
    add(lblMensaje);*/

    btnVolver.addActionListener(new ActionListener (){
      @Override
      public void actionPerformed(ActionEvent e){
        Configuracion configuracion = new Configuracion();
        configuracion.setVisible(true);
        dispose();

        //dispose();
      }
    });
    getRootPane().setDefaultButton(btnVolver);

    addWindowListener(new java.awt.event.WindowAdapter() {
      @Override 
      public void windowClosing(java.awt.event.WindowEvent e){
        Configuracion configuracion = new Configuracion();
        configuracion.setVisible(true);
        dispose();
      }
    });

  }//Aquí termina el constructor

  //Métodos para el sonido
    public void cargarSonido(){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource("resources/tiempoTerminado.wav"));
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
