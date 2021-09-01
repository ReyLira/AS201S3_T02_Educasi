/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
import dao.ICRUD;

public class ActividadImpl extends dao.Conexion implements ICRUD <ActividadModel>{

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd/MM/yyyy").parse(fecha) : null;
    }

    @Override
    public List<ActividadModel> listarTodos() throws Exception {
        List<ActividadModel> listaActividad;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT IDACT,NOMACT,MONESPACT,CANAPOACT,FECACT,ESTACT,\n"
                    + "CASE ESTFINACT\n"
                    + "WHEN 'C' THEN 'Creado'\n"
                    + "WHEN 'P' THEN 'En Proceso'\n"
                    + "WHEN 'F' THEN 'Finalizado'\n"
                    + "END AS ESTFINACT\n"
                    + "FROM ACTIVIDAD WHERE ESTACT LIKE 'A'\n"
                    + "ORDER BY IDACT DESC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaActividad = new ArrayList<>();
            ActividadModel actividad;
            while (rs.next()) {
                actividad = new ActividadModel();
                actividad.setIdact(rs.getString("IDACT"));
                actividad.setNomact(rs.getString("NOMACT"));
                actividad.setMonespact(rs.getString("MONESPACT"));
                actividad.setCanapoact(rs.getString("CANAPOACT"));
                actividad.setFecact(rs.getDate("FECACT"));
                actividad.setEstfinact(rs.getString("ESTFINACT"));
                listaActividad.add(actividad);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaActividad;
    }

    @Override
    public void registrar(ActividadModel actividad) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT) VALUES (?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, actividad.getNomact());
            ps.setString(2, actividad.getMonespact());
            ps.setString(3, actividad.getCanapoact());
            ps.setString(4, formatter.format(actividad.getFecact()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(ActividadModel actividad) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ACTIVIDAD SET NOMACT=?, MONESPACT=?, CANAPOACT=?, FECACT=? WHERE IDACT LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, actividad.getNomact());
            ps.setString(2, actividad.getMonespact());
            ps.setString(3, actividad.getCanapoact());
            ps.setString(4, formatter.format(actividad.getFecact()));
            ps.setString(5, actividad.getIdact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ActividadModel actividad) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE ACTIVIDAD SET ESTACT='I' WHERE IDACT LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, actividad.getIdact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<ActividadModel> listarComboAct() throws Exception {
        List<ActividadModel> listaActividad;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT IDACT,NOMACT FROM ACTIVIDAD WHERE ESTFINACT NOT LIKE 'F' AND ESTACT LIKE 'A'";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaActividad = new ArrayList<>();
            ActividadModel actividad;
            while (rs.next()) {
                actividad = new ActividadModel();
                actividad.setIdact(rs.getString("IDACT"));
                actividad.setNomact(rs.getString("NOMACT"));
                listaActividad.add(actividad);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listaActividad;
    }

}
