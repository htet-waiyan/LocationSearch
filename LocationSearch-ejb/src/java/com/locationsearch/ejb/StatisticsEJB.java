/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.ejb;

import com.locationsearch.entity.StatPlace;
import com.locationsearch.entity.TopPlaceProjection;
import com.locationsearch.exception.NoStatisticFoundException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ht3t
 */
@Stateless
public class StatisticsEJB {
    @PersistenceContext(unitName = "LocationSearch-ejbPU")
    private EntityManager em;

    public List<TopPlaceProjection> findTopPlaces(){
        String queryStr="SELECT new com.locationsearch.entity.TopPlaceProjection(p.id,p.name,p.address,COUNT(s.place.id))"
                + "FROM Place AS p INNER JOIN p.statistics s "
                + "GROUP BY p.id,p.name,p.address "
                + "ORDER BY COUNT(s.place.id) desc";
        
        TypedQuery<TopPlaceProjection> query=em.createQuery(queryStr, TopPlaceProjection.class);
        query.setMaxResults(5);
        return query.getResultList();
    }
    
    public List<TopPlaceProjection> getTop5PlaceOn(Date date) throws NoStatisticFoundException{
        String queryStr="SELECT new com.locationsearch.entity.TopPlaceProjection(p.id,p.name,p.address,COUNT(s.place.id)) "
                + "FROM Place AS p LEFT JOIN p.statistics AS s "
                + "WHERE s.searchDate=:date "
                + "GROUP BY p.id,p.name,p.address "
                + "ORDER BY COUNT(s.place.id) desc";
        
        TypedQuery<TopPlaceProjection> query=em.createQuery(queryStr, TopPlaceProjection.class);
        query.setParameter("date", date);
        query.setMaxResults(5);
        
        List<TopPlaceProjection> statPlaces=query.getResultList();
        
        if(statPlaces==null || statPlaces.size() <=0)
            throw new NoStatisticFoundException("No Statistic Data was found");
        return statPlaces;
    }
}
