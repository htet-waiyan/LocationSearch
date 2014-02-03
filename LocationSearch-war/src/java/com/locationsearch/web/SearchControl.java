package com.locationsearch.web;

import com.locationsearch.ejb.LocationEJB;
import com.locationsearch.entity.Place;
import com.locationsearch.exception.PlaceNotFoundException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ht3t
 */
@ManagedBean(name = "search")
@ViewScoped
public class SearchControl implements Serializable {

    @EJB
    private LocationEJB location;
    @Inject
    private GMapModel map;
    private List<Place> searchedplaces;
    private String searchQuery;
    private String state = "Default";
    Map<String, String> mapData;
    Place selectedPlace;

    public List<String> autoComplete(String query) {
        searchedplaces = location.retrievePlacesForAutoComplete(query);

        List<String> names = new LinkedList<>();
        for (Place placeName : searchedplaces) {

            names.add(placeName.getName());
        }
//        int[] simple=new int[]{23,54,23,454,2323,423};
//        return simple;

        return names;
    }
    
    @PostConstruct
    public void init(){
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public void showThePlaceOnMap() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();

        int index = Integer.parseInt(params.get("index").trim());
        
        System.out.println(index);
        selectedPlace = searchedplaces.get(index);

        searchedplaces.clear();
        searchedplaces.add(selectedPlace);
        searchQuery=selectedPlace.getName();
        
        allocateMap();
        
    }
    
    private void allocateMap(){
        mapData = new HashMap<>();

        mapData.put("name", selectedPlace.getName());
        mapData.put("url", selectedPlace.getUrl());
        mapData.put("logo", selectedPlace.getLogoPath());
        mapData.put("address", selectedPlace.getAddress());

        map.initializeMap(selectedPlace.getLatitude(), selectedPlace.getLongitude(),
                selectedPlace.getName(), mapData);
        
        log();
    }
    
    private void log(){
        SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal=Calendar.getInstance();
        
        location.logSearch(selectedPlace.getName(), searchQuery, fmt.format(cal.getTime()));
    }

    public void searchPlace() {
        if (searchQuery == null || searchQuery.trim().length() < 1) {
            this.state = "Default";
            searchedplaces = null;
        } else {
            try {
                this.searchedplaces = location.search(searchQuery);

                System.out.println("Place Found");
                this.state = "Found";

                //mapData = new HashMap<>();
                selectedPlace = searchedplaces.get(0);
//
//                mapData.put("name", selectedPlace.getName());
//                mapData.put("url", selectedPlace.getUrl());
//                mapData.put("logo", selectedPlace.getLogoPath());
//                mapData.put("address", selectedPlace.getAddress());
//
//                System.out.println("logo:" + selectedPlace.getLogoPath());
//
//                map.initializeMap(selectedPlace.getLatitude(), selectedPlace.getLongitude(), selectedPlace.getName(), mapData);

                allocateMap();

            } catch (PlaceNotFoundException pe) {
                this.state = "Not Found";
                System.out.println("Location Not Found Exception");
            }
        }
    }

    public void searchListener(ActionEvent evt) {
        System.out.println("Search Listener");
        FacesContext ctx = FacesContext.getCurrentInstance();

        UIViewRoot view = ctx.getViewRoot();
        HtmlInputText txtSearch = (HtmlInputText) view.findComponent("f1:txtSearch");

        Object query = txtSearch.getValue();

        if ((query == null) || (query.toString().trim().length() <= 0)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Search Query", "Please enter a keyword");
            ctx.addMessage("f1:txtSearch", msg);
            return;
        }
    }

    public List<Place> getSearchedplace() {
        return searchedplaces;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public GMapModel getMap() {

        return map;
    }

    public void setMap(GMapModel map) {
        this.map = map;
    }

    public Place getSelectedPlace() {
        return selectedPlace;
    }
}
