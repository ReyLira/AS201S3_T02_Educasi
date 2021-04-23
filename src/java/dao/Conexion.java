/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Conexion {
     private Connection cn = null;

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

    //Metodo de cerrar la conexión
    public void Cerrar() throws Exception {
        if (cn != null) {
            if (cn.isClosed() == false) {
                cn.close();
                cn = null;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Conexion dao = new Conexion();
        dao.Conectar();
        if (dao.getCn() != null) {
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

       


