import static com.mongodb.client.model.Filters.eq;

import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;

public class DashBoard extends JFrame {

  private static final long serialVersionUID = 1L;

  public DashBoard() {
    super("Estadisticas"); // titulo de la ventana
    this.setSize(1200, 650);// tamaño de la ventana
    iniciarComponentes();
  }

  private void iniciarComponentes() {
    JPanel panel = new JPanel();
    panel.setLayout(null); // desactivando el diseño
    this.getContentPane().add(panel);

    JLabel title = new JLabel("Estadisticas; Top 3 ventas por categoría");
    title.setBounds(450, 5, 300, 30);
    panel.add(title);

    JLabel sucursales = new JLabel("TOP 3 Sucursales");
    sucursales.setBounds(90, 35, 120, 30);
    panel.add(sucursales);

    JLabel ciudades = new JLabel("TOP 3 Ciudades");
    ciudades.setBounds(390, 35, 120, 30);
    panel.add(ciudades);

    JLabel dptos = new JLabel("TOP 3 Departamentos");
    dptos.setBounds(690, 35, 130, 30);
    panel.add(dptos);

    JLabel paises = new JLabel("TOP 3 Paises");
    paises.setBounds(990, 35, 120, 30);
    panel.add(paises);

    JLabel vendedores = new JLabel("TOP 3 Vendedores");
    vendedores.setBounds(90, 340, 120, 30);
    panel.add(vendedores);

    JLabel gremios = new JLabel("TOP 3 Gremios");
    gremios.setBounds(390, 340, 120, 30);
    panel.add(gremios);

    JLabel productos = new JLabel("TOP 3 Productos");
    productos.setBounds(690, 340, 120, 30);
    panel.add(productos);

    JLabel marcas = new JLabel("TOP 3 Marcas");
    marcas.setBounds(990, 340, 120, 30);
    panel.add(marcas);

    Bson filter = eq("tipo", "sucursal");
    Bson sort = eq("total", -1L);
    DecimalFormat moneda = new DecimalFormat("###,###");
    try {
      MongoClient mongo = new MongoClient(
          new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
      MongoDatabase database = mongo.getDatabase("analiticos");
      MongoCollection<Document> coleccion = database.getCollection("analiticos");

      // Sucursales
      FindIterable<Document> result = coleccion.find(filter).sort(sort).limit((int) 3L);
      MongoCursor<Document> cursor = result.iterator();
      int cont = 0;
      boolean tiene = false;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoA data = new Gson().fromJson(cursor.next().toJson(), tipoA.class);
        String top1suc = cont + " - Código: " + data.codigo + "\n     Nombre: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea topsucursales = new JTextArea(top1suc);
        topsucursales.setEditable(false);
        topsucursales.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          topsucursales.setBounds(40, 77, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          topsucursales.setBounds(40, 140, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          topsucursales.setBounds(40, 203, 250, 63);
        }
        panel.add(topsucursales);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(40, 140, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Ciudades
      filter = eq("tipo", "ciudad");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoA data = new Gson().fromJson(cursor.next().toJson(), tipoA.class);
        String top1suc = cont + " - Código: " + data.codigo + "\n     Nombre: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea top = new JTextArea(top1suc);
        top.setEditable(false);
        top.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          top.setBounds(340, 77, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          top.setBounds(340, 140, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          top.setBounds(340, 203, 250, 63);
        }
        panel.add(top);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(340, 140, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Departamentos
      filter = eq("tipo", "dpto");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoA data = new Gson().fromJson(cursor.next().toJson(), tipoA.class);
        String top1suc = cont + " - Código: " + data.codigo + "\n     Nombre: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea topsucursales = new JTextArea(top1suc);
        topsucursales.setEditable(false);
        topsucursales.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          topsucursales.setBounds(640, 77, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          topsucursales.setBounds(640, 140, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          topsucursales.setBounds(640, 203, 250, 63);
        }
        panel.add(topsucursales);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(640, 140, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Departamentos
      filter = eq("tipo", "pais");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoB data = new Gson().fromJson(cursor.next().toJson(), tipoB.class);
        String top1suc = cont + " - Nombre: " + data.codigo + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea top = new JTextArea(top1suc);
        top.setEditable(false);
        top.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          top.setBounds(940, 77, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          top.setBounds(940, 140, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          top.setBounds(940, 203, 250, 63);
        }
        panel.add(top);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(940, 140, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Vendedores
      filter = eq("tipo", "vendedor");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoA data = new Gson().fromJson(cursor.next().toJson(), tipoA.class);
        String top1suc = cont + " - Código: " + data.codigo + "\n     Nombre: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea top = new JTextArea(top1suc);
        top.setEditable(false);
        top.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          top.setBounds(40, 380, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          top.setBounds(40, 443, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          top.setBounds(40, 506, 250, 63);
        }
        panel.add(top);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(40, 443, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Gremios
      filter = eq("tipo", "gremio");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoA data = new Gson().fromJson(cursor.next().toJson(), tipoA.class);
        String top1suc = cont + " - Código: " + data.codigo + "\n     Nombre: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea top = new JTextArea(top1suc);
        top.setEditable(false);
        top.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          top.setBounds(340, 380, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          top.setBounds(340, 443, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          top.setBounds(340, 506, 250, 63);
        }
        panel.add(top);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(340, 443, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Productos
      filter = eq("tipo", "producto");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoA data = new Gson().fromJson(cursor.next().toJson(), tipoA.class);
        String top1suc = cont + " - Código barras: " + data.codigo + "\n     Nombre: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea top = new JTextArea(top1suc);
        top.setEditable(false);
        top.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          top.setBounds(640, 380, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          top.setBounds(640, 443, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          top.setBounds(640, 506, 250, 63);
        }
        panel.add(top);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(640, 443, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

      // Marcas
      filter = eq("tipo", "marca");
      result = coleccion.find(filter).sort(sort).limit((int) 3L);
      cursor = result.iterator();
      cont = 0;
      while (cursor.hasNext()) {
        tiene = true;
        cont += 1;
        tipoB data = new Gson().fromJson(cursor.next().toJson(), tipoB.class);
        String top1suc = cont + " - Nombre: " + data.codigo + "\n     Descripción: " + data.nombre
            + "\n     Total ventas: $ " + moneda.format(data.total);
        JTextArea top = new JTextArea(top1suc);
        top.setEditable(false);
        top.setOpaque(false);
        if (cont == 1 && data.codigo + " " != " ") {
          top.setBounds(940, 380, 250, 63);
        } else if (cont == 2 && data.codigo + " " != " ") {
          top.setBounds(940, 443, 250, 63);
        } else if (cont == 3 && data.codigo + " " != " ") {
          top.setBounds(940, 506, 250, 63);
        }
        panel.add(top);
      }
      if (!tiene) {
        JLabel vacio = new JLabel("                    No hay datos");
        vacio.setBounds(940, 443, 250, 63);
        panel.add(vacio);
      }
      tiene = false;

    } catch (Exception err) {
      JOptionPane.showMessageDialog(null, "No fue posible cargar todos los datos desde MongoDB ");
      return;
    }

  }

  public class tipoA {
    public int codigo;
    public String nombre;
    public String texto;
    public int total;
    public String tipo;
  }

  public class tipoB {
    public String codigo;
    public String nombre;
    public String texto;
    public int total;
    public String tipo;
  }

}
