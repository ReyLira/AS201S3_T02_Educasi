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
    String sql ="insert into GASTO_ACTIVIDAD (CANGASACT,MONGASACT,DESGASACT,FECGASACT,IDACT) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, obj.getCantGasActividad());
            ps.setInt(2, obj.getMonGasActividad());
            ps.setString(3, obj.getDesGasActividad());
            ps.setString(4, formatter.format(obj.getFechGasActividad()));
            ps.setInt(5, obj.getFKactividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al Ingresar Gasto Actividad Dao " + e.getMessage());
        }finally{
            this.Cerrar();
        }
    }

    @Override
    public void modificar(GastoActividadModel obj) throws Exception {
        String sql = "update GASTO_ACTIVIDAD set CANGASACT=?,MONGASACT=?,DESGASACT=?,FECGASACT=?,IDACT=? where IDGASACT=?";
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, obj.getCantGasActividad());
            ps.setInt(2, obj.getMonGasActividad());
            ps.setString(3, obj.getDesGasActividad());
            ps.setString(4, formatter.format(obj.getFechGasActividad()));
            ps.setInt(5, obj.getFKactividad());
            ps.setInt(6, obj.getIdGastActividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error al modificar cuota Dao " + e.getMessage());
        }finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(GastoActividadModel obj) throws Exception {
    String sql = "delete from GASTO_ACTIVIDAD where IDGASACT=?";               
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);             
            ps.setInt(1, obj.getIdGastActividad());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en eliminar cuota" + e.getMessage());
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
        try (Connection conec  = (Connection) this.getCn() ){
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = conec.prepareStatement(sql);
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
             System.out.println("Error en gasto Actividad Dao" + e.getMessage());
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
            PreparedStatement ps = this.getCn().prepareStatement(sql);
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
            System.out.println("Error en listarCuota Dao" + e.getMessage());
        }
        return listAc;
    }
    public int obtenerSaldoActividad(int idCuota) throws SQLException {
        String sql = "SELECT SaldoActividad(?)  AS  SaldoActividad from dual";
        ResultSet rs;
        int cuota = -1;
        try {
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, idCuota);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuota = rs.getInt("SaldoActividad");
            }
        } catch (Exception e) {
            System.out.println("error en cuota Act "+ e.getMessage());
        }
        return cuota;
    }
}
