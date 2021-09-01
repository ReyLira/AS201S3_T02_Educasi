/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.ICRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.PersonaModel;

/**
 *
 * @author ZERO
 */
public class PersonaImpl extends Conexion implements ICRUD<PersonaModel>{

    @Override
    public void registrar(PersonaModel per) throws Exception {
       String sql = "insert into PERSONA (NOMPER, APEPER, PASPER, EMAPER, DIREPER, DNIPER, CELPER, ROLPER,PERSONA_IDPER)values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApellido());
            ps.setString(3, per.getPassword());
            ps.setString(4, per.getEmail());
            ps.setString(5, per.getDireccion());
            ps.setString(6, per.getDNI());
            ps.setString(7, per.getCelular());
            ps.setString(8, per.getROL());
            if (per.getPersonaID()==0) {
                ps.setNull(9, Types.INTEGER);
            }else{
                ps.setInt(9, per.getPersonaID());
            }
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Persona Dao " + e.getMessage());
        }finally{
            this.Cerrar();
        }
    }

    @Override
    public void modificar(PersonaModel per) throws Exception {
       String sql = "update PERSONA set NOMPER=?, APEPER=?, PASPER=?, EMAPER=?, DIREPER=?, DNIPER=?, CELPER=?, ROLPER=?,ESTPER=? where IDPER=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, per.getNombre());
            ps.setString(2, per.getApellido());
            ps.setString(3, per.getPassword());
            ps.setString(4, per.getEmail());
            ps.setString(5, per.getDireccion());
            ps.setString(6, per.getDNI());
            ps.setString(7, per.getCelular());
            ps.setString(8, per.getROL());
            ps.setString(9, per.getEstado());
            ps.setInt(10, per.getID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar Persona Dao " + e.getMessage());
        }
    }

    @Override
    public void eliminar(PersonaModel per) throws Exception {
     String sql = "delete from PERSONA where IDPER=?";               
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);             
            ps.setInt(1, per.getID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en eliminarD" + e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<PersonaModel> listarTodos() throws Exception {
       List<PersonaModel> listado = null;
        PersonaModel per;
        String sql = "SELECT* FROM V_PERSONA";
        ResultSet rs;
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery(); 
            while (rs.next()) {
                per = new PersonaModel();
                per.setFila(rs.getString("FILA"));
                per.setID(rs.getInt("IDPER"));
                per.setNombre(rs.getString("NOMPER"));
                per.setApellido(rs.getString("APEPER"));
                per.setPassword(rs.getString("PASPER"));
                per.setEmail(rs.getString("EMAPER"));
                per.setDireccion(rs.getString("DIREPER"));
                per.setDNI(rs.getString("DNIPER"));
                per.setCelular(rs.getString("CELPER"));
                per.setROL(rs.getString("ROLPER"));
                per.setEstado(rs.getString("ESTPER"));
                per.setRelacion(rs.getString("RELACION"));
                //per.setPersonaID(rs.getInt("PERSONA_IDPER"));
                listado.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en listarTodos Dao" + e.getMessage());
        }
        return listado;
    }
    
    public List<PersonaModel> ListarApoderados() throws SQLException{
        List<PersonaModel> listadoA = null;
        PersonaModel per;
        ResultSet rs;
        String sql = "select IDPER,NOMPER,APEPER from PERSONA WHERE ROLPER='APODERADO'";
        try {
            listadoA = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery(); 
            while (rs.next()) {
                per = new PersonaModel();
                per.setID(rs.getInt("IDPER"));
                per.setNombre(rs.getString("NOMPER"));
                per.setApellido(rs.getString("APEPER"));
                listadoA.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en listarApoderado Dao" + e.getMessage());
        }
        return listadoA;
    }
    public List<PersonaModel> ListarPorRol(String rol) throws SQLException{
        List<PersonaModel> listadoRol = null;
        PersonaModel per;
        String sql = "select * from V_PERSONA_ROL WHERE ROLPER=?";
        try {
            listadoRol = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, rol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                per = new PersonaModel();
                per.setFila(rs.getString("FILA"));
                per.setID(rs.getInt("IDPER"));
                per.setNombre(rs.getString("NOMPER"));
                per.setApellido(rs.getString("APEPER"));
                per.setPassword(rs.getString("PASPER"));
                per.setEmail(rs.getString("EMAPER"));
                per.setDireccion(rs.getString("DIREPER"));
                per.setDNI(rs.getString("DNIPER"));
                per.setCelular(rs.getString("CELPER"));
                per.setROL(rs.getString("ROLPER"));
                per.setEstado(rs.getString("ESTPER"));
                per.setRelacion(rs.getString("RELACION"));
                listadoRol.add(per);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en listarApoderado Dao" + e.getMessage());
        }
        return listadoRol;
    }
    
    public static void main(String[] args) {
        PersonaImpl lists = new PersonaImpl();
        try {
            for (PersonaModel ListarApoderado : lists.ListarApoderados()) {
                System.out.println("ss= "+ ListarApoderado);
            }
            lists.ListarApoderados();
        } catch (Exception e) {
        }
    }

    
}
