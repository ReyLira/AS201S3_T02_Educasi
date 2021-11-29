/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import lombok.Data;
/**
 *
 * @author ZERO
 */
@Data
public class UsuarioModel {
    String DNI;
    String pass;
    String nombre;
    String apellido;
    String email;
    String rol;
    String consulta;
}
