/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author EDGARD
 */
public class GastoActividadModel {

    private int IdGastActividad;
    private int CantGasActividad;
    private int MonGasActividad;
    private String DesGasActividad;
    private Date FechGasActividad;
    private int FKactividad;

    private String fila;
    private Date fechaReporte;
    private Date fechaReportEntrada;
    private Date fechaReportSalida;

    private int IDactividad;
    private String NombreActividad;
    private int MontoActividad;
    private int CantApoActividad;
    private Date FechaActividad;
    private String EstadoActividad;

    public int getIdGastActividad() {
        return IdGastActividad;
    }

    public void setIdGastActividad(int IdGastActividad) {
        this.IdGastActividad = IdGastActividad;
    }

    public int getCantGasActividad() {
        return CantGasActividad;
    }

    public void setCantGasActividad(int CantGasActividad) {
        this.CantGasActividad = CantGasActividad;
    }

    public int getMonGasActividad() {
        return MonGasActividad;
    }

    public void setMonGasActividad(int MonGasActividad) {
        this.MonGasActividad = MonGasActividad;
    }

    public String getDesGasActividad() {
        return DesGasActividad;
    }

    public void setDesGasActividad(String DesGasActividad) {
        this.DesGasActividad = DesGasActividad;
    }

    public Date getFechGasActividad() {
        return FechGasActividad;
    }

    public void setFechGasActividad(Date FechGasActividad) {
        this.FechGasActividad = FechGasActividad;
    }

    public int getFKactividad() {
        return FKactividad;
    }

    public void setFKactividad(int FKactividad) {
        this.FKactividad = FKactividad;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
    }

    public int getIDactividad() {
        return IDactividad;
    }

    public void setIDactividad(int IDactividad) {
        this.IDactividad = IDactividad;
    }

    public int getMontoActividad() {
        return MontoActividad;
    }

    public void setMontoActividad(int MontoActividad) {
        this.MontoActividad = MontoActividad;
    }

    public int getCantApoActividad() {
        return CantApoActividad;
    }

    public void setCantApoActividad(int CantApoActividad) {
        this.CantApoActividad = CantApoActividad;
    }

    public Date getFechaActividad() {
        return FechaActividad;
    }

    public void setFechaActividad(Date FechaActividad) {
        this.FechaActividad = FechaActividad;
    }

    public String getEstadoActividad() {
        return EstadoActividad;
    }

    public void setEstadoActividad(String EstadoActividad) {
        this.EstadoActividad = EstadoActividad;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public Date getFechaReportEntrada() {
        return fechaReportEntrada;
    }

    public void setFechaReportEntrada(Date fechaReportEntrada) {
        this.fechaReportEntrada = fechaReportEntrada;
    }

    public Date getFechaReportSalida() {
        return fechaReportSalida;
    }

    public void setFechaReportSalida(Date fechaReportSalida) {
        this.fechaReportSalida = fechaReportSalida;
    }
    
}
