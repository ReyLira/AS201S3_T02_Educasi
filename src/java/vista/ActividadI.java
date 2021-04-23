package interfaces;

import java.util.List;
import modelo.ActividadModel;

public interface ActividadI {

    public List<ActividadModel> listarActividad() throws Exception;

    void registrarActividad(ActividadModel actividad) throws Exception;
    
    void modificarActividad(ActividadModel actividad) throws Exception;

    void eliminarActividad(ActividadModel actividad) throws Exception;
}