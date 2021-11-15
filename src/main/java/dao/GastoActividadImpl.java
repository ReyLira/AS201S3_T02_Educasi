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
import modelo.GastoActividadModel;

/**
 *
 * @author EDGARD
 */
public class GastoActividadImpl extends Conexion implements ICRUD<GastoActividadModel> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    @Override
    public void registrar(GastoActividadModel obj) throws Exception {
        String sql = "insert into GASTO_ACTIVIDAD (CANGASACT,MONGASACT,DESGASACT,FECGASACT,IDACT) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getCantGasActividad());
            ps.setInt(2, obj.getMonGasActividad());
            ps.setString(3, obj.getDesGasActividad());
            ps.setString(4, formatter.format(obj.getFechGasActividad()));
            ps.setInt(5, obj.getFKactividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al registrar gasto Actividad Ac {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(GastoActividadModel obj) throws Exception {
        String sql = "update GASTO_ACTIVIDAD set CANGASACT=?,MONGASACT=?,DESGASACT=?,FECGASACT=?,IDACT=? where IDGASACT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getCantGasActividad());
            ps.setInt(2, obj.getMonGasActividad());
            ps.setString(3, obj.getDesGasActividad());
            ps.setString(4, formatter.format(obj.getFechGasActividad()));
            ps.setInt(5, obj.getFKactividad());
            ps.setInt(6, obj.getIdGastActividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al modificar gasto Actividad Ac {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(GastoActividadModel obj) throws Exception {
        String sql = "delete from GASTO_ACTIVIDAD where IDGASACT=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, obj.getIdGastActividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error al eliminar gasto Actividad Ac {0} ", e.getMessage());
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<GastoActividadModel> listarTodos() throws Exception {
        List<GastoActividadModel> listado = null;
        GastoActividadModel gasAct;
        String sql = "select * from v_gastoAcividad";
        ResultSet rs;
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                gasAct = new GastoActividadModel();
                gasAct.setFila(rs.getString("fila"));
                gasAct.setIDactividad(rs.getInt("idact"));
                gasAct.setIdGastActividad(rs.getInt("IDGASACT"));
                gasAct.setNombreActividad(rs.getString("NOMACT"));
                gasAct.setCantGasActividad(rs.getInt("CANGASACT"));
                gasAct.setMonGasActividad(rs.getInt("MONGASACT"));
                gasAct.setDesGasActividad(rs.getString("DESGASACT"));
                gasAct.setFechGasActividad(rs.getDate("FECGASACT"));
                gasAct.setFKactividad(rs.getInt("IDACT"));
                listado.add(gasAct);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en gasto Actividad act {0} ", e.getMessage());
        }
        return listado;
    }
    
    public List<GastoActividadModel> listarFecha() throws Exception {
        List<GastoActividadModel> lisFech = null;
        GastoActividadModel fech;
        ResultSet rs;
        String sql = "select TO_CHAR( FECGASACT, 'dd/MM/YY' )as FECGASACT from v_gasActividaFech";
        try {
            lisFech = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fech = new GastoActividadModel();
                fech.setFechaReporte(rs.getString("FECGASACT"));
                lisFech.add(fech);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en listarFecha act {0} ", e.getMessage());
        }
        return lisFech;
    }
    public List<GastoActividadModel> listarAct() throws Exception {
        List<GastoActividadModel> listAc = null;
        GastoActividadModel act;
        ResultSet rs;
        String sql = "select * from ACTIVIDAD";
        try {
            listAc = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                act = new GastoActividadModel();
                act.setIDactividad(rs.getInt("IDACT"));
                act.setNombreActividad(rs.getString("NOMACT"));
                act.setMontoActividad(rs.getInt("MONESPACT"));
                act.setCantApoActividad(rs.getInt("CANAPOACT"));
                act.setFechaActividad(rs.getDate("FECACT"));
                act.setEstadoActividad(rs.getString("ESTACT"));
                listAc.add(act);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en listarCuot act {0} ", e.getMessage());
        }
        return listAc;
    }
    public int obtenerSaldoActividad(int idCuota) throws SQLException {
        String sql = "SELECT SaldoActividad(?)  AS  SaldoActividad from dual";
        ResultSet rs;
        int cuota = -1;
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, idCuota);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota = rs.getInt("SaldoActividad");
            }
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en cuota act {0} ", e.getMessage());
        }
        return cuota;
    }
}
