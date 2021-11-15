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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CuotaModel;

/**
 *
 * @author ZERO
 */
public class CuotaImpl extends Conexion implements ICRUD<CuotaModel> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void registrar(CuotaModel obj) throws Exception {
        String sql = "insert into CUOTA (CANCUOT,MONCUOT,FECCUOT,IDACT,IDPER) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, obj.getCantidadCuota());
            ps.setInt(2, obj.getMontoCuota());
            ps.setString(3, formatter.format(obj.getFechaCuota()));
            ps.setInt(4, obj.getFKActividad());
            ps.setInt(5, obj.getFKpersona());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al Ingresar Cuota cuot {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(CuotaModel obj) throws Exception {
        String sql = "update CUOTA set CANCUOT=?,MONCUOT=?,FECCUOT=?,IDACT=?,IDPER=? where IDCUOT=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, obj.getCantidadCuota());
            ps.setInt(2, obj.getMontoCuota());
            ps.setString(3, formatter.format(obj.getFechaCuota()));
            ps.setInt(4, obj.getFKActividad());
            ps.setInt(5, obj.getFKpersona());
            ps.setInt(6, obj.getIDcuota());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al modificar Cuota cuot {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(CuotaModel obj) throws Exception {
        String sql = "delete from CUOTA where IDCUOT=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, obj.getIDcuota());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al eliminar Cuota cuot {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<CuotaModel> listarTodos() throws Exception {
        List<CuotaModel> listado = null;
        CuotaModel cuot;
        String sql = "select * from V_CUOTA";
        ResultSet rs;
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cuot = new CuotaModel();
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
            Logger.getGlobal().log(Level.SEVERE, "Error al listar Cuota cuot {0} ", e.getMessage());
        }
        return listado;
    }

    public List<CuotaModel> listarFecha() throws Exception {
        List<CuotaModel> lisFech = null;
        CuotaModel fech;
        ResultSet rs;
        String sql = "select TO_CHAR( FECCUOT, 'dd/MM/YY' )as FECCUOT from v_FechaCuotas";
        try {
            lisFech = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fech = new CuotaModel();
                fech.setFechaSan(rs.getString("FECCUOT"));
                lisFech.add(fech);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al listar fecha cuot {0} ", e.getMessage());
        }
        return lisFech;
    }

    public List<CuotaModel> listarAct() throws Exception {
        List<CuotaModel> listAc = null;
        CuotaModel act;
        ResultSet rs;
        String sql = "select * from ACTIVIDAD";
        try {
            listAc = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                act = new CuotaModel();
                act.setIdact(rs.getString("IDACT"));
                act.setNomact(rs.getString("NOMACT"));
                act.setMonespact(rs.getString("MONESPACT"));
                act.setCanapoact(rs.getString("CANAPOACT"));
                act.setFecact(rs.getDate("FECACT"));
                act.setEstfinact(rs.getString("estact"));
                listAc.add(act);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al listarCuota cuot {0} ", e.getMessage());
        }
        return listAc;
    }

    public int obtenerCuota(int idCuota, int idPersona) throws SQLException {
        String sql = "select MONESPACT FROM ACTIVIDAD where IDACT=?";
        ResultSet rs;
        int cuota = -1;
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, idCuota);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota = rs.getInt("MONESPACT");
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al obtener Act cuot cuot {0} ", e.getMessage());
        }
        return cuota;
    }

    public int obtenerSaldoCuota(int idCuota, int idPersona) throws SQLException {
        String sql = "SELECT SaldoCuota(?,?)  AS  saldoCuota from dual";
        ResultSet rs;
        int cuota = -1;
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, idCuota);
            ps.setInt(2, idPersona);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota = rs.getInt("saldoCuota");
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, "Error al obtener saldocuota cuot {0} ", e.getMessage());
        }
        return cuota;
    }
}
