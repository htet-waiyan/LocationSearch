/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.ejb;

import com.locationsearch.entity.Place;
import com.locationsearch.entity.Statistics;
import com.locationsearch.exception.PlaceNotFoundException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.management.j2ee.statistics.Statistic;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ht3t
 */
@Stateless
public class LocationEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void test() {
        System.out.println("Hello EJB");
    }
    @PersistenceContext(unitName = "LocationSearch-ejbPU")
    private EntityManager entityManager;
    
    public void setEM(EntityManager em){
        this.entityManager=em;
    }

    public List<Place> retrievePlacesForAutoComplete(String chr) {
        TypedQuery<Place> q = entityManager.createNamedQuery("Place.search", Place.class);
        q.setParameter("searchQuery", chr + "%");

        return q.getResultList();
    }

    public void logSearch(String name, String query, String today){
        TypedQuery<Place> q=entityManager.createNamedQuery("Place.searchByName",Place.class);
        q.setParameter("query", name);
        Place place=q.getSingleResult();
        
        Statistics st=new Statistics();
        Date date=parseToToday(today);
        
        st.setKeyword(query);
        st.setSearchDate(date);
        st.setPlace(place);
        
        entityManager.persist(st);
    }
    
    private Date parseToToday(String today){
        Date todayDate=null;
        try{
            SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy");
            todayDate=fmt.parse(today);
        }
        catch(ParseException pe){
            pe.printStackTrace();
        }
        
        return todayDate;
    }
    private int getPostalCode(String query) throws NumberFormatException {

        try {
            int postal = Integer.parseInt(query.trim());
            return postal;
        } catch (NumberFormatException ne) {
            throw new NumberFormatException("Postal Code is not in the correct format");
        }
    }
    

    public List<Place> search(String queryStr) throws PlaceNotFoundException {

        TypedQuery<Place> query = null;
        List<Place> places = null;

        System.out.println(queryStr);
        try {
            int postal = getPostalCode(queryStr);
            query = entityManager.createNamedQuery("Place.searchByPostal", Place.class);
            query.setParameter("code", postal);
            
            System.out.println("Postal Code Searching");

            places = query.getResultList();

            if (!isFound(places)) {
                System.out.println("Place Not Found");
                throw new PlaceNotFoundException("Place Not Found");
            }

            return places;
        } catch (NumberFormatException ne) {

            System.out.println("Name Searching");

            query = entityManager.createNamedQuery("Place.search", Place.class);
            
            query.setParameter("searchQuery", queryStr+"%");
            
            System.out.println("Param : "+query.getParameterValue("searchQuery"));

            places = query.getResultList();
            
            if (!isFound(places)) {
                System.out.println("Place Not Found");
                throw new PlaceNotFoundException("Place Not Found");
            }

            System.out.println("Place Found");
            return places;
        }
    }

    private boolean isFound(List<Place> pls) {
        if (pls.size() <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
