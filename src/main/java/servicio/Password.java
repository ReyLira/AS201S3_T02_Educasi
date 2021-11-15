/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.PersonaModel;

/**
 *
 * @author EDGARD
 */
public class Password {

    public static void passAleatorio(PersonaModel per)throws Exception  {
        int length = 10;
        String symbol = "-/.^&*_!@%=+>)";
        String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small_letter = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String finalString = cap_letter + small_letter
                + numbers + symbol;

        Random random = SecureRandom.getInstanceStrong();

        char[] password = new char[length];

        for (int i = 0; i < length; i++) {
            password[i]= finalString.charAt(random.nextInt(finalString.length()));

        }
        String pass=String.valueOf(password);
        per.setPassword(pass); 
         Logger.getGlobal().log(Level.INFO,pass);
    }

    public static void main(String[] args) throws Exception {
       PersonaModel per = new PersonaModel();
        passAleatorio(per);
       
    }

}