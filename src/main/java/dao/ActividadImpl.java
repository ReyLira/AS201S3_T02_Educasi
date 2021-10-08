/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.ActividadModel;

/**
 *
 * @author EDGARD
 */
public class ActividadImpl extends Conexion implements ICRUD<ActividadModel> {
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }
    @Override
    public void registrar(ActividadModel obj) throws Exception {
         String sql ="insert into ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT) values (?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, obj.getNombreActividad());
            ps.setInt(2, obj.getMontoActividad());
            ps.setInt(3, obj.getCantApoActividad());
            ps.setString(4, formatter.format(obj.getFechaActividad()));
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Actividad Dao " + e.getMessage());
        }
    }
    public void registrarD(ActividadModel obj) throws Exception {
         String sql ="insert into ACTIVIDAD (NOMACT,MONESPACT,CANAPOACT,FECACT) values (?,?,?,?)";
        try (Connection conec  = (Connection) this.getCn() ){
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, obj.getNombreActividad());
            ps.setInt(2, obj.getMontoActividad());
            ps.setInt(3, obj.getCantApoActividad());
            ps.setString(4, formatter.format(obj.getFechaActividad()));
            ps.executeUpdate();
            ps.close();
        } 
    }

    @Override
    public void modificar(ActividadModel obj) throws Exception {
        String sql = "update ACTIVIDAD set NOMACT=?,MONESPACT=?,CANAPOACT=?,FECACT=?,ESTACT=? where IDACT=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, obj.getNombreActividad());
            ps.setInt(2, obj.getMontoActividad());
            ps.setInt(3, obj.getCantApoActividad());
            ps.setString(4, formatter.format(obj.getFechaActividad()));
            ps.setString(5, obj.getEstadoActividad());
            ps.setInt(6, obj.getIDactividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar Actividad Dao " + e.getMessage());
        }finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(ActividadModel obj) throws Exception {
        String sql = "delete from ACTIVIDAD where IDACT=?";               
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);             
            ps.setInt(1, obj.getIDactividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en eliminar Actividad" + e.getMessage());
        }finally {
            this.Cerrar();
        } 
    }
    

    @Override
    public List<ActividadModel> listarTodos() throws Exception {
        List<ActividadModel> listado = null;
        ActividadModel act;
        String sql = "select *from ACTIVIDAD";
        ResultSet rs;
        try (Connection conec  = (Connection) this.getCn() ){
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = conec.prepareStatement(sql);
            rs = ps.executeQuery(); 
            while (rs.next()) {
             act = new ActividadModel();
             act.setIDactividad(rs.getInt("IDACT"));
             act.setNombreActividad(rs.getString("NOMACT"));
             act.setMontoActividad(rs.getInt("MONESPACT"));
             act.setCantApoActividad(rs.getInt("CANAPOACT"));
             act.setFechaActividad(rs.getDate("FECACT"));
             act.setEstadoActividad(rs.getString("ESTACT")); 
             listado.add(act);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
             System.out.println("Error en listar actividad Dao" + e.getMessage());
        }
         return listado;
    }
    public List<ActividadModel> ListarPorEstado(String est) throws SQLException{
       
        List<ActividadModel> ListarPorEst = null;
        ActividadModel act;
        String sql = "";
        switch(est){
            case "ACTIVO":
                sql = "SELECT * FROM ACTIVIDAD where estact='ACTIVO'";
                break;
            case "INACTIVO":
                sql = "SELECT * FROM ACTIVIDAD where estact='INACTIVO'";
                break;
        }
         try {
            ListarPorEst = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                act = new ActividadModel();
             act.setIDactividad(rs.getInt("IDACT"));
             act.setNombreActividad(rs.getString("NOMACT"));
             act.setMontoActividad(rs.getInt("MONESPACT"));
             act.setCantApoActividad(rs.getInt("CANAPOACT"));
             act.setFechaActividad(rs.getDate("FECACT"));
             act.setEstadoActividad(rs.getString("ESTACT")); 
             ListarPorEst.add(act);
            }
            rs.close();
            ps.close();
        }catch (Exception e) {
            System.out.println("Error en listar por Rol Dao" + e.getMessage());
        }
        return ListarPorEst;
    }
}
