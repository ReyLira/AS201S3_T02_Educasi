package servicio;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.google.gson.JsonParser.parseReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.xml.ws.Response;
import modelo.PersonaModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ReniecS {

    public static void buscarDni(PersonaModel per) throws Exception {
        String dni = per.getDNI();
        String token = "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImVkZ2FyZC5yb2RyaWd1ZXpAdmFsbGVncmFuZGUuZWR1LnBlIn0.6IG4DxQV4ysccdUC3gwV9kSwgUQ8GFRtHqApHNxBd_g";
        String enlace = "https://dniruc.apisperu.com/api/v1/dni/" + dni + token;
        try {
            URL url = new URL(enlace);
            URLConnection request = url.openConnection();
            request.connect();          
             JsonElement root = parseReader(new InputStreamReader((InputStream) request.getContent()));
            if (root.isJsonObject()) {
                JsonObject rootobj = root.getAsJsonObject();
                String apellido_paterno = rootobj.get("apellidoPaterno").getAsString();
                String apellido_materno = rootobj.get("apellidoMaterno").getAsString();
                String nombres = rootobj.get("nombres").getAsString();
                
                Logger.getGlobal().log(Level.INFO, "Resultado\n ");
                Logger.getGlobal().log(Level.INFO,"\n{}", apellido_paterno);
                Logger.getGlobal().log(Level.INFO,"\n{}", nombres);
                

                per.setNombre(nombres);
                per.setNombre(nombres);
                per.setApellido(apellido_paterno + " " + apellido_materno);
            }
        } catch (Exception ex) {
            Logger.getGlobal().log(Level.WARNING, "Error en loginNivel_C {0} ", ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "DNI no encontrado"));
        }
    }

    

    public static void main(String[] args) throws Exception {
        PersonaModel per = new PersonaModel();
        per.setDNI("41298813");    
        buscarDni(per);
    }

//    public static void buscarRuc2(Persona per) throws Exception {
//        try {
//            String ruc = per.getRuc();
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            Request request = new Request.Builder()
//                    .url("https://apiperu.dev/api/ruc/" + ruc)
//                    .method("GET", null)
//                    .addHeader("Authorization", "Bearer 1bb45424279b9f905b6e89184dfc2e216cd9ccb81f5b1d83ff69fe583e5f787d")
//                    .addHeader("Content-Type", "application/json")
//                    .build();
//            okhttp3.Response response = client.newCall(request).execute();
////            per.setDirección(response.body());
//            
//            JsonParser jp = new JsonParser();
//            JsonElement root = jp.parse(response.body().toString());
//            if (root.isJsonObject()) {
//                JsonObject rootobj = root.getAsJsonObject();
//                String razon = rootobj.get("nombre_o_razon_social").getAsString();
//                String direccion = rootobj.get("direccion").getAsString();
//                String ubigeo = rootobj.get("ubigeo").getAsString();
//
//                System.out.println("Resultado\n");
//                System.out.println(razon + "\n" + direccion + "\n" + ubigeo + "\n");
//
//                per.setRazon(razon);
//                per.setDirección(direccion);
//                per.setUbigeo(ubigeo);
//            }
//            
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Busqueda", "RUC no encontrado"));
//        }
//
//    }
}
