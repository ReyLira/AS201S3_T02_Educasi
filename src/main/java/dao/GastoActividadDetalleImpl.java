/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class GastoActividadDetalleImpl extends Conexion implements ICRUD<GastoActividadModel>{
    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }
    public List<GastoActividadModel> ListarPorActividad(String actss) throws SQLException, Exception{
        List<GastoActividadModel> listado = null;
        GastoActividadModel gasAct;
        String sql = "select * from v_gastoAcividad where IDACT =?";
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, actss);
            ResultSet rs = ps.executeQuery();
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
            Logger.getGlobal().log(Level.SEVERE, "Error al listar Cuota APODERADO detalle cuot {0} ", e.getMessage());
        }finally{
            Cerrar();
        }
        return listado;
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
    @Override
    public void registrar(GastoActividadModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(GastoActividadModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(GastoActividadModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public GastoActividadModel total(int idactFK) throws SQLException {
        GastoActividadModel gastoActTotal =  new GastoActividadModel();
        String sql = "select SUM(CANGASACT) as CANTIDAD, SUM(MONGASACT)AS MONTO,SUM(CANGASACT)- SUM(MONGASACT)AS RESTO from gasto_actividad where IDACT=?";
        ResultSet rs;
       try (PreparedStatement ps =  this.conectar().prepareStatement(sql)) {
            ps.setInt(1, idactFK);
            rs = ps.executeQuery();
            if (rs.next()) {
                gastoActTotal.setCANTIDAD(rs.getInt("CANTIDAD"));
                gastoActTotal.setMONTO(rs.getInt("MONTO"));
                gastoActTotal.setRESTO(rs.getInt("RESTO"));
            } 
        }
        return gastoActTotal;
    }
}
