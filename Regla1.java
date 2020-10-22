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
    JLabel tb= new JLabel("tabla 2");
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
    JLabel atb= new JLabel("tabla 2");
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
    JLabel ts2= new JLabel("s2");
    ts1.setBounds(150, 140, 50, 30);
    panel.add(ts1);
    ts2.setBounds(350, 140, 50, 30);
    panel.add(ts2);
    JTextArea s1 = new JTextArea();
    JTextArea s2 = new JTextArea();
    s1.setBounds(20, 180, 200, 150);
    panel.add(s1);
    s2.setBounds(320, 180, 200, 150);
    panel.add(s2);
    JButton boton1 = new JButton("GENERAR!");
    boton1.setBounds(530, 180, 100, 40);
    panel.add(boton1);
    ActionListener oyenteDeAccion = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        HashMap<String, String> tablas = new HashMap<String, String>();
        tablas.put(A.getText(),atA.getText());
        tablas.put(B.getText(),atB.getText());
        String[] pacho = s2.getText().replace(" ","").split(";");
        List<String> S2= new ArrayList<>();
        for (String daniel: pacho){
          String[] lelouch = daniel.toLowerCase().split("select")[1].split("from")[0].split(",");
          for (String kim: lelouch){
            S2.add(kim.replace(" ",""));
          }
        }
        Set<String> setS2 = new HashSet<String>(S2);
        String[] cornelia = s1.getText().replace(" ","").split(";");
        List<String> S1= new ArrayList<>();
        for (String daniel: cornelia){
          String[] lelouch = daniel.toLowerCase().split("select")[1].split("from")[0].split(",");
          for (String kim: lelouch){
            S1.add(kim.replace(" ",""));
          }
        }
        Set<String> setS1 = new HashSet<String>(S1);

        for (String takumi:tablas.get(A.getText()).split(",")){
          if (setS2.contains(takumi)){
            setS2.remove(takumi);
          }
        }

        Set<String> t2S2 = setS2;
        List<String> SS1 = S1;
        for (String takumi:S1){
          if(t2S2.contains(takumi)){
            S1.remove(takumi);
          }
        }

        

        String Q = "{";
        for (String takumi:tablas.get(A.getText()).split(",")){
          Q = Q + takumi+",";
        }
        Q = Q + "T'2:{";

        for (String takumi:t2S2){
          Q = Q + takumi + ",";
        }
        if(SS1.contains(S1) && SS1.size() == S1.size()){ // auxS1 == S1
          //no pasa nada
        }
        else{
          for (String takumi:S1){
            Q = Q + takumi + ",";
          }        
          Q = Q.substring(0, Q.length()-1) + "}}";
          System.out.println(Q);
        }

      }
    };
    boton1.addActionListener(oyenteDeAccion);
    
  }

}
