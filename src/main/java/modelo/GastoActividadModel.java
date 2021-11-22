/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

import lombok.Data;

/**
 *
 * @author ZERO
 */
@Data
public class GastoActividadModel {

    int IdGastActividad;
    int CantGasActividad;
    int MonGasActividad;
    String DesGasActividad;
    Date FechGasActividad;
    int FKactividad;

    String fila;
    String fechaReporte;
    Date fechaReportEntrada;
    Date fechaReportSalida;

    int IDactividad;
    String NombreActividad;
    int MontoActividad;
    int CantApoActividad;
    Date FechaActividad;
    String EstadoActividad;

    int CANTIDAD;
    int MONTO;
    int RESTO;
}
