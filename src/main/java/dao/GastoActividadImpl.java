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
import modelo.GastoActividadModel;
import dao.ICRUD;

public class GastoActividadImpl extends dao.Conexion implements ICRUD<GastoActividadModel> {

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToFecha(String fecha) throws ParseException {
        return fecha != null ? new SimpleDateFormat("dd/MM/yyyy").parse(fecha) : null;
    }

    @Override
    public void registrar(GastoActividadModel gastoAct) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO GASTO_ACTIVIDAD (GASACT,DESGASACT,FECGASACT,IDACT) VALUES (?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, gastoAct.getGasact());
            ps.setString(2, gastoAct.getDesgasact());
            ps.setString(3, formatter.format(gastoAct.getFecgasact()));
            ps.setString(4, gastoAct.getIdact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(GastoActividadModel gastoAct) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE GASTO_ACTIVIDAD SET GASACT=?, DESGASACT=?,FECGASACT=?, IDACT=? WHERE IDGASACT LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, gastoAct.getGasact());
            ps.setString(2, gastoAct.getDesgasact());
            ps.setString(3, formatter.format(gastoAct.getFecgasact()));
            ps.setString(4, gastoAct.getIdact());
            ps.setString(5, gastoAct.getIdgasact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(GastoActividadModel gastoAct) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE GASTO_ACTIVIDAD SET ESTGASACT='I' WHERE IDGASACT LIKE ?";
            PreparedStatement ps = getCn().prepareStatement(sql);
            ps.setString(1, gastoAct.getIdgasact());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<GastoActividadModel> listarTodos() throws Exception {
        List<GastoActividadModel> listaGastoActividad;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_GASTO_ACTIVIDAD WHERE ESTGASACT LIKE 'A' ORDER BY IDGASACT DESC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            rs = ps.executeQuery();
            listaGastoActividad = new ArrayList<>();
            GastoActividadModel gastoActividad;
            while (rs.next()) {
                gastoActividad = new GastoActividadModel();
                gastoActividad.setIdgasact(rs.getString("IDGASACT"));
                gastoActividad.setGasact(rs.getString("GASACT"));
                gastoActividad.setDesgasact(rs.getString("DESGASACT"));
                gastoActividad.setFecgasact(rs.getDate("FECGASACT"));
                gastoActividad.setIdact(rs.getString("IDACT"));
                gastoActividad.setNombreactividad(rs.getString("NOMACT"));
                listaGastoActividad.add(gastoActividad);
            }
            return listaGastoActividad;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public String cambiarEstadoGasto(String estado, String idActividad) throws Exception {
        ResultSet rs;
        try {
            this.conectar();
            String SQL = "UPDATE ACTIVIDAD SET ESTFINACT=? WHERE IDACT LIKE ?";
            PreparedStatement ps = this.getCn().prepareStatement(SQL);
            ps.setString(1, estado);
            ps.setString(2, idActividad);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return null;
    }

}
