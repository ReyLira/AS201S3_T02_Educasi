/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.PersonaModel;

/**
 *
 * @author EDGARD
 */
public class MailJava {

    public static void enviarCorreo(PersonaModel per) throws Exception {
        String correo =per.getEmail();
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
                return new PasswordAuthentication("viajeros.miniutmen@gmail.com","912618335");
            }
        });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(correo));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(correo));
        message.setSubject("Testing Subject");
        message.setText("BUENOS DIAS "+per.getNombre() +" "+ per.getApellido()+"\n"+
                "\n Su usuario es: "+per.getDNI()+"\n y su contraseña es: " +per.getPassword()+
                "\n Muchas gracias por ser parte de educasi");

        Transport.send(message);

        System.out.println("HECHO");

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
    }
    public static void main(String[] args) throws Exception {
        PersonaModel per = new PersonaModel();
        per.setEmail("edgard.rodriguez@vallegrande.edu.pe");
        enviarCorreo(per);
        
    }
    
}
