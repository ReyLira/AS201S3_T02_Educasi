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
public class PersonaModel {
     int  ID;
     String fila;
     String nombre;
     String apellido;
     String password;
     String email;
     String ubigeoFK;
     String DNI;
     String celular;
     String ROL;
     String Estado;
     int PersonaID;
     String relacion;

     int IDubigeo;
     String codigoUbigeo;
     String deparUbigeo;
     String provUbigeo;
     String distUbigeo;
    
   
}
