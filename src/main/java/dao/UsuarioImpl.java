/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.UsuarioModel;

/**
 *
 * @author EDGARD
 */
public class UsuarioImpl extends Conexion {

    public static Boolean logueo = false;
    public static String nivel = "";

    public String loginNivel(UsuarioModel usuario) throws Exception {
        String sql = "select DNIPER,PASPER,ROLPER FROM persona where DNIPER=? and pasper=?";
        try {
            PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql);
            ps.setString(1, usuario.getDNI());
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
            System.out.println("Errorr en loginNivel_D " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public UsuarioModel login(UsuarioModel usuario) throws Exception {
        
        String sql = "select DNIPER,PASPER,NOMPER FROM persona where DNIPER=? and pasper=?";
        ResultSet rs;
        try (PreparedStatement ps = (PreparedStatement) this.conectar().prepareStatement(sql)) {
            ps.setString(1, usuario.getDNI());
            ps.setString(2, usuario.getPass());
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setDNI(rs.getString("DNIPER"));
                usuario.setPass(rs.getString("PASPER"));
                usuario.setNombre(rs.getString("NOMPER"));
                logueo = true;
            } else {
                logueo = false;
            }
        }
        return usuario;
    }
}
