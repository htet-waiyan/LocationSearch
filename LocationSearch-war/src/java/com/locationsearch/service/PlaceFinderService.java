/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.service;

import com.locationsearch.ejb.LocationEJB;
import com.locationsearch.entity.Place;
import com.locationsearch.exception.PlaceNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ht3t
 */
@Path("/finder")
@RequestScoped
public class PlaceFinderService {

    @EJB
    private LocationEJB location;
    
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public Place test(@QueryParam("place") String place){
//        return location.find(place);
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_XML)
    public List<Place> findPlaceXML(@FormParam("place") String param)throws WebApplicationException{
        try{
            return location.search(param);
        }
        catch(PlaceNotFoundException pe){
            pe.printStackTrace();
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
    }
}
