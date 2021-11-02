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

    public String loginNivel(UsuarioModel usuario) throws Exception {
        String sql = "select DNIPER,PASPER,ROLPER,EMAPER FROM persona where EMAPER=? and pasper=?";
        try {
            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPass());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nivel = rs.getString("ROLPER");
                logueo = true;
            } else {
                logueo = false;
            }
            ps.close();
            rs.close();
            return nivel;
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Errorr en loginNivel_D {0} ", e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public UsuarioModel login(UsuarioModel usuario) throws Exception {
        
        String sql = "select DNIPER,PASPER,NOMPER,EMAPER FROM persona where EMAPER=? and pasper=?";
        ResultSet rs;
        try (PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql)) {
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPass());
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setDNI(rs.getString("DNIPER"));
                usuario.setPass(rs.getString("PASPER"));
                usuario.setNombre(rs.getString("NOMPER"));
                usuario.setEmail(rs.getString("EMAPER"));
                logueo = true;
            } else {
                logueo = false;
            }
        }
        return usuario;
    }
}
