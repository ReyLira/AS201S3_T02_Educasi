/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DashboardImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import lombok.Data;

@Data
/**
 *
 * @author EDGARD
 */
@Named(value = "dashboardC")
@SessionScoped
public class DashboardC implements Serializable {

    private PieChartModel dashboardPersona;
    private List<Number> listaPersona;
    private DashboardImpl dao;
    private PieChartModel dashboardRango;
    private List<Number> listaRango;
    public DashboardC() {
        dashboardPersona = new PieChartModel();
        dashboardRango = new PieChartModel();
        dao = new DashboardImpl();
    }
    private void dashboardPersonal() throws Exception {
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = listaPersona;
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(113, 175, 220)");
        bgColors.add("rgb(227, 99, 91)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("ACTIVO");
        labels.add("INACTIVO");
        data.setLabels(labels);
        dashboardPersona.setData(data);
    }
    private void dashboardRango() throws Exception {
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = listaRango;
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(218, 247, 166)");
        bgColors.add("rgb(125, 206, 160)");
        bgColors.add("rgb(133, 193, 233)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("APODERADO");
        labels.add("ADMINISTRADOR");
        labels.add("ALUMNO");
        data.setLabels(labels);
        dashboardRango.setData(data);
    }
    @PostConstruct
    public void construir() {
        try {
            listaPersona = dao.dashboardPersonal();
            dashboardPersonal();
             listaRango = dao.dashboardRango();
            dashboardRango();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.WARNING, "Error en el dashboardCargoD {0}", e.getMessage());
        }
    }
    private int number = 20;

    public void activarSesion() {
        number = 20;
    }

    public void decrementoNumero() throws IOException {
        if (number > 0) {
            number--;
        } else if (number == 0) {
            System.out.println("salir de sesion");
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/AS201S3_T02_Educasi/");
        }
    }

}
