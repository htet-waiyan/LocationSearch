/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ht3t
 */
//@Table(name="TOP_PLACE")
//@NamedQueries({
//    @NamedQuery(name = "TopPlace.findTop5", query = "SELECT tp FROM TopPlaceProjection tp")
//})
public class TopPlaceProjection implements Serializable {
    
    private Long id;
    
    private String name;
    
    private String address;
    
    private Long count;

    public TopPlaceProjection() {
    }

    public TopPlaceProjection(Long id, String name, String address,Long count) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.count=count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
