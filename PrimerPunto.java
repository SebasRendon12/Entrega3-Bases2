import javax.swing.*;
import java.awt.event.ActionListener;

public class PrimerPunto extends JFrame {

  private static final long serialVersionUID = 1L;

  public PrimerPunto(String titulo) {
    super(titulo); // titulo de la ventana
    this.setSize(700, 400);// tamaño de la ventana
    iniciarComponentes();
  }

  private void iniciarComponentes() {
    JPanel panel = new JPanel();
    panel.setLayout(null); // desactivando el diseño
    this.getContentPane().add(panel);

    JLabel title = new JLabel("seleccione una de las reglas");
    title.setBounds(200, 10, 300, 30);
    panel.add(title);
    JButton boton1 = new JButton("regla 1");
    boton1.setBounds(30, 50, 200, 40);
    panel.add(boton1);
    JButton boton2 = new JButton("regla 4");
    boton2.setBounds(300, 50, 300, 40);
    panel.add(boton2);
    JButton boton3 = new JButton("regla 5");
    boton2.setBounds(150, 150, 300, 40);
    panel.add(boton3);
    ActionListener oyenteDeAccion = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        Regla1 datos = new Regla1("Regla1");
        datos.setVisible(true);
      }
    };
    boton1.addActionListener(oyenteDeAccion);
    ActionListener oyenteDeAccion1 = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        Regla2 puntos = new Regla2("Regla4");
        puntos.setVisible(true);
      }
    };
    boton2.addActionListener(oyenteDeAccion1);
    ActionListener oyenteDeAccion2 = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        Regla3 puntos = new Regla3("Regla5");
        puntos.setVisible(true);
      }
    };
    boton3.addActionListener(oyenteDeAccion2);
  }

}
