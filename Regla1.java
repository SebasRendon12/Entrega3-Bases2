import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Regla1 extends JFrame {

  private static final long serialVersionUID = 1L;

  public Regla1(String titulo) {
    super(titulo); // titulo de la ventana
    this.setSize(700, 400);// tamaño de la ventana
    iniciarComponentes();
  }

  private void iniciarComponentes() {
    JPanel panel = new JPanel();
    panel.setLayout(null); // desactivando el diseño
    this.getContentPane().add(panel);

    JLabel title = new JLabel("Ingrese nombres de las tablas");
    JLabel ta = new JLabel("tabla 1");
    JLabel tb = new JLabel("tabla 2");
    ta.setBounds(80, 40, 50, 30);
    panel.add(ta);
    tb.setBounds(230, 40, 50, 30);
    panel.add(tb);
    JTextArea A = new JTextArea();
    JTextArea B = new JTextArea();
    A.setBounds(50, 80, 100, 30);
    panel.add(A);
    B.setBounds(200, 80, 100, 30);
    panel.add(B);
    title.setBounds(100, 10, 300, 30);
    panel.add(title);
    JLabel title1 = new JLabel("Ingrese los atributos de las tablas");
    JLabel ata = new JLabel("tabla 1");
    JLabel atb = new JLabel("tabla 2");
    ata.setBounds(380, 40, 50, 30);
    panel.add(ata);
    atb.setBounds(530, 40, 50, 30);
    panel.add(atb);
    JTextArea atA = new JTextArea();
    JTextArea atB = new JTextArea();
    atA.setBounds(350, 80, 130, 30);
    panel.add(atA);
    atB.setBounds(500, 80, 130, 30);
    panel.add(atB);
    title1.setBounds(400, 10, 300, 30);
    panel.add(title1);
    JLabel ts1 = new JLabel("s1");
    JLabel ts2 = new JLabel("s2");
    ts1.setBounds(150, 140, 50, 30);
    panel.add(ts1);
    ts2.setBounds(350, 140, 50, 30);
    panel.add(ts2);
    JTextArea s1 = new JTextArea();
    JTextArea s2 = new JTextArea();
    s1.setBounds(20, 180, 220, 150);
    panel.add(s1);
    s2.setBounds(320, 180, 220, 150);
    panel.add(s2);
    JButton boton1 = new JButton("GENERAR!");
    boton1.setBounds(530, 180, 100, 40);
    panel.add(boton1);
    ActionListener oyenteDeAccion = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        HashMap<String, String> tablas = new HashMap<String, String>();
        tablas.put(A.getText(), atA.getText());
        tablas.put(B.getText(), atB.getText());

        if (s1.getText().isEmpty()) {
          if (s2.getText().isEmpty()) {
            String Q = "{";
            for (String takumi : tablas.get(A.getText()).split(",")) {
              Q = Q + takumi + ",";
            }
            Q = Q.substring(0, Q.length() - 1) + "}";
            JOptionPane.showMessageDialog(null, "Q' = " + Q);
          } else {
            JOptionPane.showMessageDialog(null, "no se genera ningun formato, debe existir S1");
          }
        } else {
          if (s2.getText().isEmpty()) {
            String Q = "{";
            for (String takumi : tablas.get(A.getText()).split(",")) {
              Q = Q + takumi + ",";
            }
            Q = Q.substring(0, Q.length() - 1) + "}";
            JOptionPane.showMessageDialog(null, "Q' = " + Q);
          } else {
            String[] pacho = s2.getText().replace(" ", "").split(";");
            List<String> S2 = new ArrayList<>();
            for (String daniel : pacho) {
              String[] lelouch = daniel.toLowerCase().split("select")[1].split("from")[0].split(",");
              for (String kim : lelouch) {
                S2.add(kim.replace(" ", ""));
              }
            }
            System.out.println(S2);
            Set<String> setS2 = new HashSet<String>(S2);
            Set<Set<String>> setSS2 = new HashSet<Set<String>>();
            String[] cornelia = s1.getText().replace(" ", "").split(";");
            System.out.println(s1.getText());
            List<String> S1 = new ArrayList<>();
            for (String daniel : cornelia) {
              Set<String> setSS1 = new HashSet<String>();
              if (daniel.contains(",")) {
                String[] lelouch = daniel.toLowerCase().split("select")[1].split("from")[0].split(",");
                for (String kim : lelouch) {
                  System.out.println(kim);
                  S1.add(kim.replace(" ", ""));
                  setSS1.add(kim.replace(" ", ""));
                }
                setSS2.add(setSS1);
              } else {
                String lelouch = daniel.toLowerCase().split("select")[1].split("from")[0];
                S1.add(lelouch.replace(" ", ""));
                setSS1.add(lelouch.replace(" ", ""));
                setSS2.add(setSS1);
              }
            }
            Set<String> setS1 = new HashSet<String>(S1);
            List<String> SSS1 = new ArrayList<>(setS1);
            for (String takumi : tablas.get(A.getText()).split(",")) {
              Integer v = 0;
              if (setS2.contains(takumi)) {
                for (String ba : tablas.get(B.getText()).split(",")) {
                  if (takumi.equals(ba)) {
                    v = 1;
                  }
                }
                if (v != 1) {
                  setS2.remove(takumi);
                }
              }
            }

            Set<String> t2S2 = setS2;
            Set<String> SS1 = setS1;
            for (String takumi : SSS1) {
              if (t2S2.contains(takumi)) {
                SSS1.remove(takumi);
              }
            }

            String Q = "{";
            for (String takumi : tablas.get(A.getText()).split(",")) {
              Q = Q + takumi + ",";
            }
            Q = Q + "T'2:{";

            for (String takumi : t2S2) {
              Q = Q + takumi + ",";
            }
            if (SSS1.equals(S1)) { // auxS1 == S1
              // no pasa nada
            } else {
              for (String takumi : SSS1) {
                Q = Q + takumi + ",";
              }
            }
            Q = Q.substring(0, Q.length() - 1) + "}";
            Integer f = 0;
            Integer g = 0;
            for (Set<String> hola : setSS2) {
              for (String takumi : hola) {
                if (t2S2.contains(takumi)) {
                  g = 1;
                  // no dio nulo
                } else {
                  f = 1;
                }
                if (g != 1) {
                  Q = Q + ",T''2:{";
                  for (String takumii : hola) {
                    Q = Q + takumii + ",";
                  }
                  Q = Q.substring(0, Q.length() - 1) + "}";
                }
              }
            }
            Q = Q + "}";
            JOptionPane.showMessageDialog(null, "Q' = " + Q);
          }
        }
      }
    };
    boton1.addActionListener(oyenteDeAccion);

  }

}
