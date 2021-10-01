/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author ZERO
 */
public class CuotaModel {
   private int IDcuota;
   private int CantidadCuota;
   private int MontoCuota;
   private String EstadoCuota;
   private Date FechaCuota;
   private int FKpersona;
   private int FKActividad;
   private String NombrePersona;
   private String NombreActividad;
   
   private Date fechaReporte;
   private Date fechaReportEntrada;
   private Date fechaReportSalida;
   private String idact,nomact,monespact,canapoact,estfinact;
    private Date fecact;
    public int getIDcuota() {
        return IDcuota;
    }

    public void setIDcuota(int IDcuota) {
        this.IDcuota = IDcuota;
    }

    public int getCantidadCuota() {
        return CantidadCuota;
    }

    public void setCantidadCuota(int CantidadCuota) {
        this.CantidadCuota = CantidadCuota;
    }

    public int getMontoCuota() {
        return MontoCuota;
    }

    public void setMontoCuota(int MontoCuota) {
        this.MontoCuota = MontoCuota;
    }

    public String getEstadoCuota() {
        return EstadoCuota;
    }

    public void setEstadoCuota(String EstadoCuota) {
        this.EstadoCuota = EstadoCuota;
    }

    public Date getFechaCuota() {
        return FechaCuota;
    }

    public void setFechaCuota(Date FechaCuota) {
        this.FechaCuota = FechaCuota;
    }

    public int getFKpersona() {
        return FKpersona;
    }

    public void setFKpersona(int FKpersona) {
        this.FKpersona = FKpersona;
    }

    public int getFKActividad() {
        return FKActividad;
    }

    public void setFKActividad(int FKActividad) {
        this.FKActividad = FKActividad;
    }

    public String getNombrePersona() {
        return NombrePersona;
    }

    public void setNombrePersona(String NombrePersona) {
        this.NombrePersona = NombrePersona;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
    }

    public String getIdact() {
        return idact;
    }

    public void setIdact(String idact) {
        this.idact = idact;
    }

    public String getNomact() {
        return nomact;
    }

    public void setNomact(String nomact) {
        this.nomact = nomact;
    }

    public String getMonespact() {
        return monespact;
    }

    public void setMonespact(String monespact) {
        this.monespact = monespact;
    }

    public String getCanapoact() {
        return canapoact;
    }

    public void setCanapoact(String canapoact) {
        this.canapoact = canapoact;
    }

    public String getEstfinact() {
        return estfinact;
    }

    public void setEstfinact(String estfinact) {
        this.estfinact = estfinact;
    }

    public Date getFecact() {
        return fecact;
    }

    public void setFecact(Date fecact) {
        this.fecact = fecact;
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
