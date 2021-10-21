package dao;


import java.sql.Connection;
import java.sql.DriverManager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection cnx = null;

    public  Connection conectar() throws Exception{
       try {
            String user = ("EDUCASI");
            String pwd = ("1234");
            String driver = ("oracle.jdbc.OracleDriver");
            String url = ("jdbc:oracle:thin:@localhost:1521/XE");
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de conexión, revise xfa");
             Logger.getGlobal().log(Level.INFO, "Error en la conexion", e.getMessage());
        }
       return cnx;
    }
    //Metodo de cerrar la conexión

    public void Cerrar() throws Exception {
        if (cnx != null) {
            if (cnx.isClosed() == false) {
                cnx.close();
                cnx = null;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Conexion dao = new Conexion();
        if (dao.getCn() != null) {
            Logger.getGlobal().log(Level.INFO,  "Conectado con éxito");
            
        } else {
            Logger.getGlobal().log(Level.WARNING,  "Error en la Conexión");
        }
    }

    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }

}
