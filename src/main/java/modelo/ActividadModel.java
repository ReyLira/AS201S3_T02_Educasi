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
public class ActividadModel {
    private int IDactividad;
    private String NombreActividad;
    private int MontoActividad;
    private int CantApoActividad;
    private Date FechaActividad;
    private String EstadoActividad;

    public int getIDactividad() {
        return IDactividad;
    }

    public void setIDactividad(int IDactividad) {
        this.IDactividad = IDactividad;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public void setNombreActividad(String NombreActividad) {
        this.NombreActividad = NombreActividad;
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

    
}
