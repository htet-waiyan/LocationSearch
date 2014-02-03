/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.web;

import com.locationsearch.entity.StatPlace;
import com.locationsearch.entity.TopPlaceProjection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ht3t
 */
@ManagedBean
@ViewScoped
public class PieChartBean {
    private PieChartModel chartModel;
    
    public PieChartBean(){
        if(chartModel==null)
            System.out.println("Pie Chart Null");
        else
            System.out.println("Pie Chart Not Null");
    }
    public void showChart(List<TopPlaceProjection> sp){
        System.out.println("Calling Show Chart");
        chartModel=new PieChartModel();
        
        for(TopPlaceProjection p:sp){
            chartModel.set(p.getName(), p.getCount());
        }
    }


    public PieChartModel getChartModel() {
        return chartModel;
    }
    
    
}
