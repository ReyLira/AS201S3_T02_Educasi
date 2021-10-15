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
public class ActividadModel {

    int IDactividad;
    String NombreActividad;
    int MontoActividad;
    int CantApoActividad;
    Date FechaActividad;
    String EstadoActividad;

}
