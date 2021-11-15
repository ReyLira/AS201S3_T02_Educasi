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
public class CuotaModel {

    int IDcuota;
    int CantidadCuota;
    int MontoCuota;
    String EstadoCuota;
    Date FechaCuota;
    int FKpersona;
    int FKActividad;
    String NombrePersona;
    String NombreActividad;

    String fechaSan;
    Date fechaReporte;
    Date fechaReportEntrada;
    Date fechaReportSalida;
    String idact, nomact, monespact, canapoact, estfinact;
    Date fecact;

    int CANTIDAD;
    int MONTO;
    int RESTO;
}
