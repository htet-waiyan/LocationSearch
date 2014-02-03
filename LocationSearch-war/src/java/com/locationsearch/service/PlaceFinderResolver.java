/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.service;

import com.locationsearch.entity.Place;
import com.locationsearch.entity.Statistics;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 *
 * @author ht3t
 */
@Provider
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class PlaceFinderResolver {
    private JAXBContext ctx;

    public PlaceFinderResolver() {
        try {
            this.ctx = JAXBContext.newInstance(Place.class, Statistics.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public JAXBContext getContext(Class<?> type) {
        return (type.equals(Place.class) ? ctx : null);
    }
}
