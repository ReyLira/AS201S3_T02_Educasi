package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    private Connection cnx = null;

    public void conectar() throws Exception {
       try {
            String user = ("EDUCASI");
            String pwd = ("1234");
            String driver = ("oracle.jdbc.OracleDriver");
            String url = ("jdbc:oracle:thin:@localhost:1521/XE");
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de conexión, revise xfa");
            System.out.println("error de conexion " + e.getMessage());
        }

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
        dao.conectar();
        if (dao.getCn() != null) {
            System.out.println("Conectado con éxito");
        } else {
            System.err.println("Error en la Conexión");
        }
    }

    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }

}
