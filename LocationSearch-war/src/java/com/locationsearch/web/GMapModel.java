/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author ht3t
 */
@ManagedBean
@ViewScoped
public class GMapModel implements Serializable {

    private LatLng coordinates;
    private MapModel mapModel;
    private Marker marker;
    private Map<String, String> data;
    private static final String markIcon = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";

    public GMapModel() {
        data = new HashMap<>();

        data.put("url", "http://iss.nus.edu.sg");
        data.put("logo", "img/ISS-NUS-Logo.jpg");
        data.put("name", "ISS");
        data.put("address", "Institute Of System Science");
        
        
        initializeMap(1.297306, 103.771019, "ISS", data);
    }

    public MapModel getMapModel() {
        return mapModel;
    }    
    
    public void initializeMap(double lat, double lng, String title, Map<String, String> data) {
        System.out.println("Initialize Map");
        coordinates = new LatLng(lat, lng);
        this.marker = new Marker(coordinates, title, data, markIcon);
        
        mapModel=new DefaultMapModel();
        mapModel.addOverlay(this.marker);
    }
    
    public void onMarkerSelect(OverlaySelectEvent evt){
        System.out.println("Select Event");
        this.marker=(Marker)evt.getOverlay();
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public Marker getMarker() {
        return marker;
    }
    
    
}
