package modelo;

import java.util.Date;

public class GastoActividadModel {

    private String idgasact, gasact, desgasact, idact, nombreactividad;
    private String radiobutfin;
    private Date fecgasact;

    public String getIdgasact() {
        return idgasact;
    }

    public void setIdgasact(String idgasact) {
        this.idgasact = idgasact;
    }

    public String getGasact() {
        return gasact;
    }

    public void setGasact(String gasact) {
        this.gasact = gasact;
    }

    public String getDesgasact() {
        return desgasact;
    }

    public void setDesgasact(String desgasact) {
        this.desgasact = desgasact;
    }

    public String getIdact() {
        return idact;
    }

    public void setIdact(String idact) {
        this.idact = idact;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public Date getFecgasact() {
        return fecgasact;
    }

    public void setFecgasact(Date fecgasact) {
        this.fecgasact = fecgasact;
    }

    public String getRadiobutfin() {
        return radiobutfin;
    }

    public void setRadiobutfin(String radiobutfin) {
        this.radiobutfin = radiobutfin;
    }
}
