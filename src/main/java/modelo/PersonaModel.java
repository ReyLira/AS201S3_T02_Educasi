/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ZERO
 */
public class PersonaModel {
    private int  ID;
    private String fila;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private String ubigeoFK;
    private String DNI;
    private String celular;
    private String ROL;
    private String Estado;
    private int PersonaID;
    private String relacion;

    private int IDubigeo;
    private String codigoUbigeo;
    private String deparUbigeo;
    private String provUbigeo;
    private String distUbigeo;
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getROL() {
        return ROL;
    }

    public void setROL(String ROL) {
        this.ROL = ROL;
    }

    public int getPersonaID() {
        return PersonaID;
    }

    public void setPersonaID(int PersonaID) {
        this.PersonaID = PersonaID;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    

    public int getIDubigeo() {
        return IDubigeo;
    }

    public void setIDubigeo(int IDubigeo) {
        this.IDubigeo = IDubigeo;
    }

    public String getCodigoUbigeo() {
        return codigoUbigeo;
    }

    public void setCodigoUbigeo(String codigoUbigeo) {
        this.codigoUbigeo = codigoUbigeo;
    }

    public String getDeparUbigeo() {
        return deparUbigeo;
    }

    public void setDeparUbigeo(String deparUbigeo) {
        this.deparUbigeo = deparUbigeo;
    }

    public String getProvUbigeo() {
        return provUbigeo;
    }

    public void setProvUbigeo(String provUbigeo) {
        this.provUbigeo = provUbigeo;
    }

    public String getDistUbigeo() {
        return distUbigeo;
    }

    public void setDistUbigeo(String distUbigeo) {
        this.distUbigeo = distUbigeo;
    }

    public String getUbigeoFK() {
        return ubigeoFK;
    }

    public void setUbigeoFK(String ubigeoFK) {
        this.ubigeoFK = ubigeoFK;
    }

   
}
