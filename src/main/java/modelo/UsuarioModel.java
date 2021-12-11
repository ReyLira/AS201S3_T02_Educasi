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

public class UsuarioModel {
    private String DNI;
    private String pass;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String consulta;
    private int ID;
    
    //cuotas

}
