/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.unittest;

import com.locationsearch.ejb.LocationEJB;
import com.locationsearch.entity.Place;
import com.locationsearch.exception.PlaceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
/**
 *
 * @author ht3t
 */
public class LocationEJBTest {

    private LocationEJB location;
    
    
    private EntityManager em;
    
    
    private TypedQuery<Place> query;
    private static final String queryStr="jsdfjsjdf";
    
    @Before
    public void init(){
        location=new LocationEJB();
        
        em=mock(EntityManager.class);
        query=mock(TypedQuery.class);
        
        
    }
    
//    @Test(expected = PlaceNotFoundException.class)
//    public void shouldThrowPlaceNotFoundException() throws PlaceNotFoundException{
//        List<Place> places=new ArrayList<>();
//        when(em.createNamedQuery("Place.search",Place.class)).thenReturn(query);
//        when(query.setParameter("searchQuery", queryStr)).thenReturn(query);
//       
//        location.setEM(em);
//        location.search("Haw Par Villa");
//    }
    
      @Test
      public void listSizeShouldBeThree() throws PlaceNotFoundException{
          when(em.createNamedQuery("Place.search",Place.class)).thenReturn(query);
          //when(query.setParameter("searchQuery", queryStr+"%")).thenReturn(query);
          location.setEM(em);
          
          assertTrue(location.search("i").size()>1);
      }
//    @Test
//    public void elementShouldStartWithI() throws PlaceNotFoundException{
//        when(em.createNamedQuery("Place.search",Place.class)).thenReturn(query);
//        location.setEM(em);
//        
//        String prefix="i";
//        List<Place> places=location.search(prefix);
//        for(Place p : places){
//            assertTrue(p.getName().toLowerCase().startsWith(prefix));
//        }
//    }
}
