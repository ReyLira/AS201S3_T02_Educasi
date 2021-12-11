/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CuotaModel;
import modelo.UsuarioDetalleModel;
import modelo.UsuarioModel;

/**
 *
 * @author EDGARD
 */
public class UsuarioImpl extends Conexion {
    public static Boolean logueo = false;
    public static String nivel = "";
    public static Boolean validar = false;
    public static Boolean validarCorreo=false;

    public UsuarioModel ingresoLogin(String DNI, String pass) throws Exception {
        UsuarioModel ingreso = new UsuarioModel();
        String sql = "select DNIPER,PASPER,NOMPER,APEPER,ROLPER,EMAPER,IDPER FROM persona where DNIPER=? and pasper=?";
        ResultSet rs;
        try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, DNI);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                ingreso.setDNI(rs.getString("DNIPER"));
                ingreso.setPass(rs.getString("PASPER"));
                ingreso.setNombre(rs.getString("NOMPER"));
                ingreso.setApellido(rs.getString("APEPER"));
                ingreso.setEmail(rs.getString("EMAPER"));
                ingreso.setRol(rs.getString("ROLPER"));
                ingreso.setID(rs.getInt("IDPER"));
                logueo = true;
            } else {
                logueo = false;
            }
        }
        return ingreso;
    }
    public List<UsuarioDetalleModel> ListarPorPersona(int perss) throws SQLException, Exception {
        List<UsuarioDetalleModel> listado = null;
        UsuarioDetalleModel cuot;
        String sql = "select * from V_CUOTA WHERE IDPER=?";
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, perss);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cuot = new UsuarioDetalleModel();
                cuot.setIDcuota(rs.getInt("IDCUOT"));
                cuot.setNombreActividad(rs.getString("NOMACT"));
                cuot.setNombrePersona(rs.getString("NOMPER"));
                cuot.setCantidadCuota(rs.getInt("CANCUOT"));
                cuot.setMontoCuota(rs.getInt("MONCUOT"));
                cuot.setFechaCuota(rs.getDate("FECCUOT"));
                cuot.setFKActividad(rs.getInt("IDACT"));
                cuot.setFKpersona(rs.getInt("IDPER"));
                listado.add(cuot);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al listar Cuota APODERADO cuot {0} ", e.getMessage());
        }

        return listado;
    }
     public UsuarioDetalleModel total(int idperFK) throws SQLException {
        UsuarioDetalleModel cuotaTotal =  new UsuarioDetalleModel();
        String sql = "select SUM(cancuot) as CANTIDAD,SUM(moncuot) AS MONTO,SUM(cancuot) - SUM(moncuot) as RESTO from cuota where  cuota.idper=?";
        ResultSet rs;
       try (PreparedStatement ps =  this.conectar().prepareStatement(sql)) {
            ps.setInt(1, idperFK);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuotaTotal.setCANTIDAD(rs.getInt("CANTIDAD"));
                cuotaTotal.setMONTO(rs.getInt("MONTO"));
                cuotaTotal.setRESTO(rs.getInt("RESTO"));
            } 
        }
        return cuotaTotal;
    }
    public UsuarioModel validacionUsuario(String DNI, String Correo) throws Exception {
        UsuarioModel ingreso = new UsuarioModel();
        String sql = "select DNIPER,EMAPER,NOMPER,APEPER,ROLPER,PASPER FROM persona where DNIPER=? and EMAPER=?";
        ResultSet rs;
        try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, DNI);
            ps.setString(2, Correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                ingreso.setDNI(rs.getString("DNIPER"));
                ingreso.setEmail(rs.getString("EMAPER"));
                ingreso.setNombre(rs.getString("NOMPER"));
                ingreso.setApellido(rs.getString("APEPER"));
                ingreso.setRol(rs.getString("ROLPER"));
                ingreso.setPass(rs.getString("PASPER"));
            }
        }
        return ingreso;
    }
    public UsuarioModel validacionUsuario2(String DNI) throws Exception {
        UsuarioModel ingreso = new UsuarioModel();
        String sql = "select DNIPER,EMAPER,NOMPER,APEPER,ROLPER,PASPER FROM persona where DNIPER=?";
        ResultSet rs;
        try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, DNI);
            rs = ps.executeQuery();
            if (rs.next()) {
                ingreso.setDNI(rs.getString("DNIPER"));
                ingreso.setEmail(rs.getString("EMAPER"));
                ingreso.setNombre(rs.getString("NOMPER"));
                ingreso.setApellido(rs.getString("APEPER"));
                ingreso.setRol(rs.getString("ROLPER"));
                ingreso.setPass(rs.getString("PASPER"));
                validarCorreo=true;
            }else{
                validarCorreo=false;
            }
        }
        return ingreso;
    }
    public void modificar(UsuarioModel user) throws Exception {
        String sql = "update PERSONA set PASPER=? where emaper=? and dniper=?";

        try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
            ps.setString(1, user.getPass());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getDNI());
            ps.executeUpdate();
            int rstp = ps.executeUpdate();
            if (rstp > 0) {
                validar = true;
            } else {
                validar = false;
            }
        }
    }
}
