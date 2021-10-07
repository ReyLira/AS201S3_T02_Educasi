/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.util.Random;
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

        Random random = new Random();

        char[] password = new char[length];

        for (int i = 0; i < length; i++) {
            password[i]= finalString.charAt(random.nextInt(finalString.length()));

        }
        String pass=String.valueOf(password);
        per.setPassword(pass); 
        System.out.println(pass);
    }

    public static void main(String[] args) throws Exception {
       PersonaModel per = new PersonaModel();
        passAleatorio(per);
    }

}
