/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author EDGARD
 */
public class CuotaDetalleImpl extends Conexion implements ICRUD<CuotaModel> {

    DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd-MM-yyyy").parse(fecha) : null;
    }

    public List<CuotaModel> ListarPorPersona(String perss) throws SQLException, Exception {
        List<CuotaModel> listado = null;
        CuotaModel cuot;
        String sql = "select * from V_CUOTA WHERE IDPER=?";
        try {
            this.conectar();
            listado = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, perss);
            ResultSet rs = ps.executeQuery();
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
            Logger.getGlobal().log(Level.SEVERE, "Error al listar Cuota APODERADO cuot {0} ", e.getMessage());
        }

        return listado;
    }

    @Override
    public void registrar(CuotaModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(CuotaModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(CuotaModel obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public CuotaModel total(int idperFK) throws SQLException {
        CuotaModel cuotaTotal =  new CuotaModel();
        String sql = "select SUM(cancuot) as CANTIDAD,SUM(moncuot) AS MONTO,SUM(cancuot) - SUM(moncuot) as RESTO from cuota where  cuota.idper=?";
        ResultSet rs;
       try (PreparedStatement ps =  this.conectar().prepareStatement(sql)) {
            ps.setInt(1, idperFK);
            rs = ps.executeQuery();
            if (rs.next()) {
                cuotaTotal.setCANTIDAD(rs.getInt("CANTIDAD"));
                cuotaTotal.setMONTO(rs.getInt("MONTO"));
                cuotaTotal.setRESTO(rs.getInt("RESTO"));
            } 
        }
        return cuotaTotal;
    }
   
}