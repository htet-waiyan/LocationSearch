/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlInlineBinaryData;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ht3t
 */

@Entity
@Table(name = "SEARCH_STATISTICS")
//@NamedQueries({
//    @NamedQuery(name="Statistics.topPlaces", query="SELECT p.id, p.name,p.address FROM Place p, Statistics s "
//        + "WHERE p.id=s.place.id "
//        + "GROUP BY p.id,p.name,p.address "
//        + "ORDER BY COUNT(s.place.id) DESC "
//        + "fetch first 5 rows only")
//})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEARCH_ID")
    private Long id;
    
    
    @ManyToOne
    //foreign column
    @JoinColumn(name = "SEARCH_LOCATION_ID")
    @XmlTransient
    private Place place;
    
    @Column(name = "SEARCH_KEYWORD")
    private String keyword;
    
    @Column(name = "SEARCH_DATE")
    @Temporal(TemporalType.DATE)
    private Date searchDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Statistics)) {
            return false;
        }
        Statistics other = (Statistics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.locationsearch.entity.Statistics[ id=" + id + " ]";
    }
    
}
