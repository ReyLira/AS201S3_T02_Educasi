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
}
