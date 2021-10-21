/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import modelo.UsuarioModel;

/**
 *
 * @author EDGARD
 */
@Data
@Named(value = "usuarioC")
@SessionScoped
public class UsuarioC implements Serializable {

    UsuarioImpl dao;
    UsuarioModel usuarrio;
    String user;
    String pass;

    public UsuarioC() {
        usuarrio = new UsuarioModel();
        dao = new UsuarioImpl();
    }

    public void login() {
        try {
            usuarrio = dao.login(user, pass);
            if (usuarrio != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objetoUsuario", usuarrio);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/AS201S3_T02_Educasi/faces/vistas/menuContenido.xhtml");
            } else {
                System.out.println("no puedes entrar");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Acceso al sistema", "Usuario y/o contraseña incorrecto"));
            }
        } catch (Exception e) {
            System.out.println("error en el login C " + e.getMessage());
            e.printStackTrace();
        }
    }
}
