/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.PersonaModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author EDGARD
 */
public class ReniecPrueba {

    public static void buscarDniReniec(PersonaModel per) throws Exception {
        String dni = per.getDNI();
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url("https://apiperu.dev/api/dni/" + dni)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer 6c345b3dcf9970c97f1f22c585d9eab356dbdf3219923af2d09e6a14308606a0")
                .build();
        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            Logger.getGlobal().log(Level.INFO, json);
            JsonElement root = JsonParser.parseString​(json).getAsJsonObject();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            if (root.isJsonObject()) {
                Logger.getGlobal().log(Level.INFO, "es un j");
                JsonObject rootobj = root.getAsJsonObject().getAsJsonObject("data");
                String nombres = rootobj.get("nombres").getAsString();
                String apellido_paterno = rootobj.get("apellido_paterno").getAsString();
                String apellido_materno = rootobj.get("apellido_materno").getAsString();

                Logger.getGlobal().log(Level.INFO, "Resultado\n ");
                Logger.getGlobal().log(Level.INFO, "\n{}", apellido_paterno);
                Logger.getGlobal().log(Level.INFO, "\n{}", apellido_materno);
                Logger.getGlobal().log(Level.INFO, "\n{}", nombres);

                per.setNombre(nombres);
                per.setApellido(apellido_paterno + " " + apellido_materno);
            }

        }
    }

    public static void main(String[] args) throws Exception {
        PersonaModel per = new PersonaModel();
        per.setDNI("72717476");
        buscarDniReniec(per);

    }

}
