/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EDGARD
 */
public class DashboardImpl extends Conexion{
    public List<Number> dashboardPersonal() throws Exception {
        this.conectar();
        List<Number> lista = new ArrayList();
        try {
            String sql = "SELECT COUNT(CASE ESTPER WHEN 'ACTIVO' THEN 'ACTIVO' END) AS ACTIVO , COUNT(CASE ESTPER WHEN 'INACTIVO' THEN 'INACTIVO' END) AS INACTIVO FROM persona";
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Logger.getGlobal().log(Level.INFO, "Existen datos");
                lista.add(rs.getInt("ACTIVO"));
                lista.add(rs.getInt("INACTIVO"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error en dashboardPersonaImpl {0}", e.getMessage());
        } finally {
            this.Cerrar();
        }
        return lista;
    }
    public List<Number> dashboardRango() throws Exception {
        this.conectar();
        List<Number> lista = new ArrayList();
        try {
            String sql = "SELECT COUNT(CASE ROLPER WHEN 'APODERADO' THEN 'APODERADO' END) AS APODERADO , COUNT(CASE ROLPER WHEN 'ADMIN' THEN 'ADMIN' END) AS ADMINISTRADOR, COUNT(CASE ROLPER WHEN 'ALUMNO' THEN 'ALUMNO' END) AS ALUMNO FROM persona";
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Logger.getGlobal().log(Level.INFO, "Existen cantidad de Rango");
                lista.add(rs.getInt("APODERADO"));
                lista.add(rs.getInt("ADMINISTRADOR"));
                lista.add(rs.getInt("ALUMNO"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error en dashboardRangoImpl {0}", e.getMessage());
        } finally {
            this.Cerrar();
        }
        return lista;
    }
}
