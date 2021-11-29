/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String sql = "select DNIPER,PASPER,NOMPER,APEPER,ROLPER,EMAPER FROM persona where DNIPER=? and pasper=?";
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
                logueo = true;
            } else {
                logueo = false;
            }
        }
        return ingreso;
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
