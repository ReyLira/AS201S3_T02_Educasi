/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Conexion {
     private Connection cn = null;
     public static Connection cnx = null;
    public void Conectar() throws Exception {
        try {
            if (cn == null) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                cn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-3O2S9G4:1433;database=DBEDUCASI","sa", "rut12345");
//                cn = DriverManager.getConnection("jdbc:sqlserver://192.168.13.6:1433;database=REPORTESQLSERVER", "root", "root");
            }
        } catch (SQLException e) {
            throw e;
        }
    }
        public static Connection conectar() throws Exception {

        InputStream input = Conexion.class.getClassLoader().getResourceAsStream("properties/db.properties");
        Properties properties = new Properties();
        properties.load(input);
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String pwd = properties.getProperty("pwd");
        try {
            Class.forName(driver);
            cnx = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage() + e.getStackTrace());
        }
        return cnx;
    }
    //Metodo de cerrar la conexión
    public void Cerrar() throws Exception {
        if (cn != null) {
            if (cn.isClosed() == false) {
                cn.close();
                cn = null;
            }
        }
    }
     public void cerrar() throws Exception{
        if(cnx !=null){
            cnx.close();
        }
    }
    public static void main(String[] args) throws Exception {
  
        if (conectar() != null) {
            System.out.println("Conectado con éxito");
        } else {
            System.err.println("Error en la Conexión");
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }


}

       


