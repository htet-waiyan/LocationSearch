/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.web;

import com.locationsearch.ejb.StatisticsEJB;
import com.locationsearch.entity.StatPlace;
import com.locationsearch.entity.TopPlaceProjection;
import com.locationsearch.exception.NoStatisticFoundException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ht3t
 */
@ManagedBean(name = "stat")
@RequestScoped
public class StatisticControl {

    @EJB
    private StatisticsEJB stat;
    @Inject
    private PieChartBean pieChart;
    private List<TopPlaceProjection> topPlaces;
    //private List<StatPlace> topStatPlaces;
    private Date selectedDate;
    private String state = "found";

    @PostConstruct
    public void retrieveTop5Places() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        topPlaces = stat.findTopPlaces();

        pieChart.showChart(topPlaces);
    }

    public void retrieveStatTop5Places() {
        try {
            topPlaces = stat.getTop5PlaceOn(selectedDate);
            state = "found";

            pieChart.showChart(topPlaces);
        }
        catch(NoStatisticFoundException ne){
            ne.printStackTrace();
            state="not_found";
        }

    }
    
    public void calendarSubmitListener(ActionEvent evt){
        FacesContext ctx=FacesContext.getCurrentInstance();
        UIViewRoot view=ctx.getViewRoot();
        
        HtmlInputText date=(HtmlInputText) view.findComponent("cal:btnCal");
        Object d=date.getValue();
        
        if(d==null || d.toString().trim().length()<=0){
            FacesMessage msg=new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Date Value", "Please fill the date");
            ctx.addMessage("cal:btnCal", msg);
            return;
        }
    }

    public PieChartBean getPieChart() {
        return pieChart;
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }

    public List<TopPlaceProjection> getTopPlaces() {
        return topPlaces;
    }

    public void setTopPlaces(List<TopPlaceProjection> topPlaces) {
        this.topPlaces = topPlaces;
    }

    public StatisticsEJB getStat() {
        return stat;
    }

    public void setStat(StatisticsEJB stat) {
        this.stat = stat;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
