import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class SegundoPunto extends JFrame {

  private static final long serialVersionUID = 1L;
  private boolean isConsulta = false;

  public SegundoPunto(String titulo) {
    super(titulo); // titulo de la ventana
    this.setSize(540, 260);// tamaño de la ventana
    iniciarComponentes();
  }

  private void iniciarComponentes() {
    JPanel panel = new JPanel();
    panel.setLayout(null); // desactivando el diseño
    this.getContentPane().add(panel);

    JButton boton1 = new JButton("Generar y Cargar Estadísticas");
    boton1.setBounds(100, 50, 300, 40);
    panel.add(boton1);
    JButton boton2 = new JButton("Visualizar Estadísticas");
    boton2.setBounds(100, 120, 300, 40);
    panel.add(boton2);
    ActionListener oyenteDeAccion = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        GenerarEstadisticas();
      }
    };
    boton1.addActionListener(oyenteDeAccion);
    ActionListener oyenteDeAccion1 = new ActionListener() {
      @Override
      public void actionPerformed(java.awt.event.ActionEvent e) {
        VisualizarEstadisticas();
      }
    };
    boton2.addActionListener(oyenteDeAccion1);
  }

  private void GenerarEstadisticas() {
    Connection conn;
    Statement sentencia;
    ResultSet resultado;
    MongoCollection<Document> coleccion;
    try { // Se carga el driver JDBC-ODBC
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch (Exception err) {
      JOptionPane.showMessageDialog(null, "No se pudo cargar el driver JDBC");
      return;
    }
    try { // Se conecta con mongo
      MongoClient mongo = new MongoClient(
          new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
      MongoDatabase database = mongo.getDatabase("analiticos");
      coleccion = database.getCollection("analiticos");
    } catch (Exception err) {
      JOptionPane.showMessageDialog(null, "No fue posible conectarse a MongoDB ");
      return;
    }
    try {
      conexion connection = new conexion();
      conn = DriverManager.getConnection(connection.getConn(), connection.getUser(), connection.getPass());
      sentencia = conn.createStatement();
    } catch (SQLException err) {
      JOptionPane.showMessageDialog(null, "No hay conexión con la base de datos.");
      return;
    }
    try {
      BasicDBObject doc = new BasicDBObject();
      coleccion.deleteMany(doc);
      resultado = sentencia.executeQuery(
          "Select codSucursal as cod, sucursal.nombre, sucursal.direccion as texto , Sum(valor) as total, max('sucursal') as tipo  from venta inner join sucursal on sucursal.codigo =venta.codsucursal group by codsucursal, sucursal.nombre, sucursal.direccion");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getInt("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("texto", resultado.getString("texto"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT ciudad.codigo as cod, ciudad.nombre as nombre, ciudad.poblacion as texto,SUM(valor) AS total,MAX('ciudad') AS tipo FROM venta INNER JOIN sucursal ON sucursal.codigo = venta.codsucursal INNER JOIN ciudad ON ciudad.codigo = sucursal.codciudad GROUP BY ciudad.codigo, ciudad.nombre, ciudad.poblacion");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getInt("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("texto", resultado.getInt("texto"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT dpto.codigo   AS cod, dpto.nombre   AS nombre, SUM(valor) AS total, MAX('dpto') AS tipo FROM venta INNER JOIN sucursal ON sucursal.codigo = venta.codsucursal  INNER JOIN ciudad ON ciudad.codigo = sucursal.codciudad INNER JOIN dpto ON dpto.codigo = ciudad.coddpto GROUP BY dpto.codigo, dpto.nombre");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getInt("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT pais.nombre   AS cod, pais.moneda   AS nombre, SUM(valor) AS total, MAX('pais') AS tipo FROM venta INNER JOIN sucursal ON sucursal.codigo = venta.codsucursal INNER JOIN ciudad ON ciudad.codigo = sucursal.codciudad INNER JOIN dpto ON dpto.codigo = ciudad.coddpto INNER JOIN pais ON pais.nombre = dpto.nompais GROUP BY pais.nombre, pais.moneda");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getString("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT vendedor.codigo    AS cod, vendedor.nombre    AS nombre, vendedor.salario   AS texto, SUM(valor) AS total, MAX('vendedor') AS tipo FROM venta INNER JOIN vendedor ON vendedor.codigo = venta.codvendedor GROUP BY vendedor.codigo, vendedor.nombre, vendedor.salario");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getInt("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("texto", resultado.getInt("texto"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT gremio.codigo   AS cod, gremio.nombre   AS nombre, SUM(valor) AS total, MAX('gremio') AS tipo FROM venta  INNER JOIN vendedor ON vendedor.codigo = venta.codvendedor INNER JOIN gremio ON gremio.codigo = vendedor.codgremio  GROUP BY gremio.codigo, gremio.nombre");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getInt("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT producto.codbarras   AS cod, producto.nombre      AS nombre, producto.tipo        AS texto, SUM(valor) AS total, MAX('producto') AS tipo FROM venta INNER JOIN producto ON producto.codbarras = venta.codproducto GROUP BY producto.codbarras, producto.nombre, producto.tipo");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getInt("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("texto", resultado.getString("texto"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }

      resultado = sentencia.executeQuery(
          "SELECT marca.nombre        AS cod, marca.descripcion   AS nombre, SUM(valor) AS total, MAX('marca') AS tipo FROM venta INNER JOIN producto ON producto.codbarras = venta.codproducto INNER JOIN marca ON marca.nombre = producto.nommarca GROUP BY marca.nombre, marca.descripcion");
      while (resultado.next()) {
        Document i = new Document();
        i.put("codigo", resultado.getString("cod"));
        i.put("nombre", resultado.getString("nombre"));
        i.put("total", resultado.getInt("total"));
        i.put("tipo", resultado.getString("tipo"));

        coleccion.insertOne(i);
      }
      JOptionPane.showMessageDialog(null, "Consulta y guardado realizado con exito");
      isConsulta = true;

    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error");
    }
  }

  private void VisualizarEstadisticas() {
    if (isConsulta) {
      DashBoard dash = new DashBoard();
      dash.setVisible(true);
    } else {
      JOptionPane.showMessageDialog(null, "Aún no ha generado y cargado las estadisticas");
    }
  }

}