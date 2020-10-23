import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.*;

public class Regla2 extends JFrame {

  private static final long serialVersionUID = 1L;

  public Regla2(String titulo) {
    super(titulo); // titulo de la ventana
    this.setSize(900, 500);// tamaño de la ventana
    iniciarComponentes();
  }

  private void iniciarComponentes() {
    JPanel panel = new JPanel();
    panel.setLayout(null); // desactivando el diseño
    this.getContentPane().add(panel);

    JLabel title = new JLabel("Ingrese nombres de las tablas");
    JLabel ta = new JLabel("tabla 1");
    JLabel tb = new JLabel("tabla 2");
    JLabel tc = new JLabel("tabla 3");
    ta.setBounds(60, 40, 50, 30);
    panel.add(ta);
    tb.setBounds(160, 40, 50, 30);
    panel.add(tb);
    tc.setBounds(260, 40, 50, 30);
    panel.add(tc);
    JTextArea A = new JTextArea();
    JTextArea B = new JTextArea();
    JTextArea C = new JTextArea();
    A.setBounds(20, 80, 100, 30);
    panel.add(A);
    B.setBounds(130, 80, 100, 30);
    panel.add(B);
    C.setBounds(240, 80, 100, 30);
    panel.add(C);
    title.setBounds(100, 10, 300, 30);
    panel.add(title);
    JLabel title1 = new JLabel("Ingrese los atributos de las tablas");
    JLabel ata = new JLabel("tabla 1");
    JLabel atb = new JLabel("tabla 2");
    JLabel atc = new JLabel("tabla 3");
    ata.setBounds(440, 40, 50, 30);
    panel.add(ata);
    atb.setBounds(580, 40, 50, 30);
    panel.add(atb);
    atc.setBounds(720, 40, 50, 30);
    panel.add(atc);
    JTextArea atA = new JTextArea();
    JTextArea atB = new JTextArea();
    JTextArea atC = new JTextArea();
    atA.setBounds(400, 80, 130, 30);
    panel.add(atA);
    atB.setBounds(540, 80, 130, 30);
    panel.add(atB);
    atC.setBounds(680, 80, 130, 30);
    panel.add(atC);
    title1.setBounds(500, 10, 300, 30);
    panel.add(title1);
    JLabel ts1 = new JLabel("s1");
    JLabel ts3 = new JLabel("s3");
    ts1.setBounds(130, 140, 50, 30);
    panel.add(ts1);
    ts3.setBounds(730, 140, 50, 30);
    panel.add(ts3);
    JTextArea s1 = new JTextArea();
    JTextArea s3 = new JTextArea();
    s1.setBounds(20, 180, 220, 150);
    panel.add(s1);
    s3.setBounds(620, 180, 220, 150);
    panel.add(s3);
    JButton boton1 = new JButton("GENERAR!");
    boton1.setBounds(350, 400, 100, 50);
    panel.add(boton1);
    ActionListener oyenteDeAccion = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        HashMap<String, String> tablas = new HashMap<String, String>();
        tablas.put(A.getText(), atA.getText());
        tablas.put(B.getText(), atB.getText());
        tablas.put(C.getText(), atC.getText());
        Set<String> Q = new HashSet<String>();
        for (String takumi : tablas.get(A.getText()).split(",")) {
          Q.add(takumi);
        }
        for (String takumi : tablas.get(B.getText()).split(",")) {
          Q.add(takumi);
        }
        for (String takumi : tablas.get(C.getText()).split(",")) {
          Q.add(takumi);
        }
        if (s1.getText().isEmpty() && s3.getText().isEmpty()) {
          String QQ = "{";
          for (String takumi : tablas.get(A.getText()).split(",")) {
            QQ = QQ + takumi + ",";
          }
          QQ = QQ.substring(0, QQ.length() - 1) + "}";
          JOptionPane.showMessageDialog(null, "Q' = " + QQ);

        } else {
          Set<String> S1 = new HashSet<String>();
          Set<String> S3 = new HashSet<String>();
          if (!s1.getText().isEmpty()) {
            for (String takumi : s1.getText().toLowerCase().split(";")) {
              for (String X : takumi.split(" ")) {
                for (String elemento : Q) {
                  if (X.replace(",", "").equals(elemento)) {
                    S1.add(elemento);
                  }
                }
              }
            }
          }
          if (!s3.getText().isEmpty()) {
            for (String takumi : s3.getText().toLowerCase().split(";")) {
              for (String X : takumi.split(" ")) {
                for (String elemento : Q) {
                  if (X.replace(",", "").equals(elemento)) {
                    S3.add(elemento);
                  }
                }
              }
            }
          }
          Set<String> T2S3 = new HashSet<String>();
          for (String lelouch : S3) {
            for (String takumi : tablas.get(B.getText()).split(",")) {
              if (takumi.equals(lelouch)) {
                T2S3.add(lelouch);
              }
            }
          }
          Set<String> T3S3 = new HashSet<String>();
          for (String lelouch : S3) {
            for (String takumi : tablas.get(C.getText()).split(",")) {
              if (takumi.equals(lelouch)) {
                T3S3.add(lelouch);
              }
            }
          }
          ArrayList<Set<String>> SS1t2 = new ArrayList<Set<String>>();

          if (!s1.getText().isEmpty()) {
            for (String takumi : s1.getText().toLowerCase().split(";")) {
              Set<String> S1t2 = new HashSet<String>();
              for (String X : takumi.split(" ")) {
                for (String lelouch : tablas.get(B.getText()).split(",")) {
                  if (X.replace(",", "").equals(lelouch)) {
                    S1t2.add(lelouch);
                  }
                }
              }
              SS1t2.add(S1t2);
            }
          }
          ArrayList<Set<String>> SS1t3 = new ArrayList<Set<String>>();
          if (!s1.getText().isEmpty()) {
            for (String takumi : s1.getText().toLowerCase().split(";")) {
              Set<String> S1t3 = new HashSet<String>();
              for (String X : takumi.split(" ")) {
                for (String lelouch : tablas.get(C.getText()).split(",")) {
                  if (X.replace(",", "").equals(lelouch)) {
                    S1t3.add(lelouch);
                  }
                }
              }
              SS1t3.add(S1t3);
            }
          }

          Set<String> S1t2t = new HashSet<String>();
          Set<String> S1t2ta = new HashSet<String>();
          for (Set<String> F : SS1t2) {
            for (String takumi : F) {
              S1t2ta.add(takumi);
              for (String lelouch : T2S3) {
                if (!takumi.equals(lelouch)) {
                  S1t2t.add(takumi);
                }
              }
            }
          }
          if(S1t2ta.equals(S1t2t)){
            S1t2t.clear();
          }
          Set<String> S1t3t = new HashSet<String>();
          Set<String> S1t3ta = new HashSet<String>();
          for (Set<String> F : SS1t3) {
            for (String takumi : F) {
              S1t3ta.add(takumi);
              for (String lelouch : T3S3) {
                if (!takumi.equals(lelouch)) {
                  S1t3t.add(takumi);
                }
              }
            }
          }
          if(S1t3ta.equals(S1t3t)){
            S1t3t.clear();
          }
          Set<String> T22 = new HashSet<String>();
          for (String lelouch : tablas.get(B.getText()).split(",")) {
            for (String takumi : T2S3) {
              if (!lelouch.equals(takumi)) {
                T22.add(lelouch);
              }
            }
          }
          Set<String> T33 = new HashSet<String>();
          for (String lelouch : tablas.get(C.getText()).split(",")) {
            for (String takumi : T3S3) {
              if (!lelouch.equals(takumi)) {
                T33.add(lelouch);
              }
            }
          }
          String QQ = "{";
          System.out.println(tablas.get(A.getText()));
          System.out.println(tablas.get(B.getText()));
          System.out.println(tablas.get(C.getText()));
          for (String takumi : tablas.get(A.getText()).split(",")) {
            QQ = QQ + takumi + ",";
          }
          QQ = QQ.substring(0, QQ.length() - 1) + ", T'2:{";
          for (String takumi : T2S3) {
            QQ = QQ + takumi + ",";
          }
          for (String takumi : S1t2t) {
            QQ = QQ + takumi + ",";
          }
          QQ = QQ.substring(0, QQ.length() - 1) + "}, T''2:{";
          for (String takumi : T22) {
            QQ = QQ + takumi + ",";
          }
          QQ = QQ.substring(0, QQ.length() - 1) + "}, T'3:{";
          for (String takumi : T3S3) {
            QQ = QQ + takumi + ",";
          }
          for (String takumi : S1t3t) {
            QQ = QQ + takumi + ",";
          }
          QQ = QQ.substring(0, QQ.length() - 1) + "}, T''3:{";
          for (String takumi : T33) {
            QQ = QQ + takumi + ",";
          }
          QQ = QQ.substring(0, QQ.length() - 1) + "}}";
          JOptionPane.showMessageDialog(null, "Q' = " + QQ);
        }

      }

    };
    boton1.addActionListener(oyenteDeAccion);

  }

}
