/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.PersonaModel;
import modelo.UsuarioModel;


/**
 *
 * @author EDGARD
 */
public class MailJava {
    public static String consult = "";
    public static void notificarCorreo(UsuarioModel uss) throws Exception {
        String correo = uss.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("viajeros.miniutmen@gmail.com", "912618335");
            }
        });

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            String thisIp = InetAddress.getLocalHost().getHostAddress();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Correo de notificacion ");
            message.setText("BUENOS DIAS " + uss.getNombre() + " " + uss.getApellido() + " USTED A INICIADO SESION AL SISTEMA EDUCASI"
                    + "\n con el correo: " + uss.getEmail() + "\n desde la direccion IP: " + thisIp
                    + "\n en la Fecha : " + fechSystem
                    + "\n Muchas gracias por ser parte de educasi");

            Transport.send(message);
            Logger.getGlobal().log(Level.INFO, "HECHO");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void enviarCorreo(PersonaModel per) throws Exception {
        String correo = per.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("viajeros.miniutmen@gmail.com", "912618335");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Correo de validacion");
            message.setText("BUENOS DIAS " + per.getNombre() + " " + per.getApellido() + "\n"
                    + "\n Su usuario es: " + per.getDNI() + "\n y su contraseña es: " + per.getPassword()
                    + "\n Muchas gracias por ser parte de educasi");

            Transport.send(message);
            Logger.getGlobal().log(Level.INFO, "HECHO");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void enviarCorreo2(UsuarioModel uss) throws Exception {
        String correo = uss.getEmail();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("viajeros.miniutmen@gmail.com", "912618335");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Correo de validacion");
            message.setText("BUENOS DIAS " + uss.getNombre() + " " + uss.getApellido() + "\n"
                    + "\n Su usuario es: " + uss.getDNI() + "\n y su contraseña es: " + uss.getPass()
                    + "\n Muchas gracias por ser parte de educasi");

            Transport.send(message);
            Logger.getGlobal().log(Level.INFO, "HECHO");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void notificarConsultas(UsuarioModel uss) throws Exception {
        String correo = "viajeros.miniutmen@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("viajeros.miniutmen@gmail.com", "912618335");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Correo de Notificacion de consulta");
            
            message.setText("NOTIFICACION DE " + uss.getNombre() + " " + uss.getApellido() + "\n"
                    + "\n IDENTIFICADO CON DNI : " + uss.getDNI() + "\n SU CONSULTA ES: "+ consult );

            Transport.send(message);
            Logger.getGlobal().log(Level.INFO, "HECHO");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        PersonaModel per = new PersonaModel();
        UsuarioModel uss = new UsuarioModel();
        uss.setEmail("luis.taquire@vallegrande.edu.pe");
        per.setEmail("luis.taquire@vallegrande.edu.pe");
        enviarCorreo(per);
        notificarCorreo(uss);

    }

}
