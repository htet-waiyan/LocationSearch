/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ht3t
 */
@Entity
@Table(name = "PLACES")
@NamedQueries({
    @NamedQuery(name="Place.findAll",query="SELECT p FROM Place p"),
    @NamedQuery(name = "Place.search", query = "SELECT p FROM Place p WHERE UPPER(p.name) LIKE UPPER(:searchQuery)"),
    @NamedQuery(name = "Place.searchByPostal", query="SELECT p FROM Place p WHERE p.postalCode=:code"),
    @NamedQuery(name = "Place.searchByName", query="SELECT p FROM Place p WHERE p.name=:query")
})
@XmlType
@XmlRootElement(name = "place")
@XmlAccessorType(XmlAccessType.FIELD)
public class Place implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "LATITUDE")
    private double latitude;
    
    @Column(name = "LONGITUDE")
    private double longitude;
    
    @Column(name = "IMAGE_PATH")
    private String imagePath;
    
    @Column(name ="POSTAL_CODE")
    private int postalCode;
    
    @Column(name="URL")
    private String url;
    
    @Column(name = "LOGO_PATH")
    private String logoPath;
    
    @XmlElement(name="statistics")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "place", targetEntity = Statistics.class)
    private List<Statistics> statistics;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.locationsearch.entity.Place[ id=" + id + " ]";
    }
    
}
