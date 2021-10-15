package dao;

import static com.ibm.java.diagnostics.utils.Context.logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class Conexion {

    private Connection cnx = null;

    public void conectar(){
       try {
            String user = ("EDUCASI");
            String pwd = ("1234");
            String driver = ("oracle.jdbc.OracleDriver");
            String url = ("jdbc:oracle:thin:@localhost:1521/XE");
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de conexión, revise xfa");
            logger.log(Level.SEVERE, "Error en la conexion {0}", e.getMessage());
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
            logger.log(Level.INFO, "Conectado con éxito");
        } else {
            logger.log(Level.SEVERE, "Error en la Conexión");
        }
    }

    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }

}
