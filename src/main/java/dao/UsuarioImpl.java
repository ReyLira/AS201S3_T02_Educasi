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
public class UsuarioImpl extends Conexion{
    public UsuarioModel login(String user, String pass)throws Exception{
      UsuarioModel usuario;
        usuario = null;
      String sql="select DNIPER,PASPER,NOMPER FROM persona where DNIPER=? and pasper=?";
            ResultSet rs;
          try (PreparedStatement ps = this.conectar().prepareStatement(sql)) {
              ps.setString(1, user);
              ps.setString(2, pass);
              rs = ps.executeQuery();
              if (rs.next()) {
                  usuario=new UsuarioModel();
                  usuario.setDNI(user);
                  usuario.setPass(pass);
                  usuario.setNombre(rs.getString("NOMPER"));
              }
          }
            return usuario;
        } 
    }

