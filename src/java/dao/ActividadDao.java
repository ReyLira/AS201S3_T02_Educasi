/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.ActividadI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.ActividadModel;

public class ActividadDao extends dao.Conexion implements ActividadI {

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd/MM/yyyy").parse(fecha) : null;
    }

    @Override
    public List<ActividadModel> listarActividad() throws Exception {
        List<ActividadModel> listaActividad;
        try {
            
            String sql = "SELECT * FROM ACTIVIDAD";
            PreparedStatement st = this.conectar().prepareStatement(sql);
            ResultSet rs = st.executeQuery();        
            listaActividad = new ArrayList<>();
            ActividadModel actividad;
            while (rs.next()) {
                actividad = new ActividadModel();
                actividad.setIdact(rs.getString("IDACT"));  //commit
                actividad.setNomact(rs.getString("NOMACT"));
                actividad.setMonespact(rs.getString("MONESPACT"));
                actividad.setCanapoact(rs.getString("CANAPOACT"));
                actividad.setFecact(rs.getDate("FECACT"));
                listaActividad.add(actividad);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listaActividad;
    }

    @Override
    public void registrarActividad(ActividadModel actividad) throws Exception {
        
        try {
            String sql = "INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT) VALUES (?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, actividad.getNomact());
            ps.setString(2, actividad.getMonespact());
            ps.setString(3, actividad.getCanapoact());
            ps.setString(4, formatter.format(actividad.getFecact()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificarActividad(ActividadModel actividad) throws Exception {
        
        try {
            String sql = "UPDATE ACTIVIDAD SET NOMACT=?, MONESPACT=?, CANAPOACT=?, FECACT=? WHERE IDACT LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, actividad.getNomact());
            ps.setString(2, actividad.getMonespact());
            ps.setString(3, actividad.getCanapoact());
            ps.setString(4, formatter.format(actividad.getFecact()));
            ps.setString(5, actividad.getIdact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminarActividad(ActividadModel actividad) throws Exception {
        
        try {
            String sql = "DELETE FROM ACTIVIDAD WHERE IDACT LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, actividad.getIdact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
