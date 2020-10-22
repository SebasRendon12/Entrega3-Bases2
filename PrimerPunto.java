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
        SegundoPunto puntos = new SegundoPunto("Segundo punto");
        puntos.setVisible(true);
      }
    };
    boton2.addActionListener(oyenteDeAccion1);
  }

}
