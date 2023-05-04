package com.mycompany.validadordecorrelatividad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection conectar = null;
    String usuario = "root";
    String contraseña = "root";
    String bd = "validador_correlatividad";
    String ip = "localhost";
    String puerto = "3306";

    String ruta = "jdbc:mysql://"+ip +":" +puerto +"/"+bd;
    
    public Connection establecerConexion() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(ruta, usuario,contraseña);
            //JOptionPane.showMessageDialog(null, "conexion establecida");
       } catch (Exception e){
           JOptionPane.showMessageDialog(null, "error al establecer conexion" + e);
        }
     return conectar; 
   }
    
    public void cerrarConnection() throws SQLException {
        try {
            conectar.close();
        } catch (Exception e) {
        }
    }
}
 
  