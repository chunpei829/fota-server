package com.arimacomm.fotaServer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Version {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private Integer productId;
    
    @NotEmpty
    private String name;
    
    private Integer number;
    
    @NotEmpty
    private String fingerPrint;
    
    private String releaseNote;
    
    public Integer getId() {
    	return id;
    }
    public void setId(Integer id) {
    	this.id = id;
    }
    
    public Integer getProductId() {
    	return productId;
    }
    public void setProductId(Integer productId) {
    	this.productId = productId;
    }
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    public Integer getNumber() {
    	return number;
    }
    public void setNumber(Integer number) {
    	this.number = number;
    }
    
    public String getReleaseNote() {
    	return releaseNote;
    }
    public void setReleaseNote(String releaseNote) {
    	this.releaseNote = releaseNote;
    }
    
    public String getFingerPrint() {
    	return fingerPrint;
    }
    public void setFingerPrint(String fingerPrint) {
    	this.fingerPrint = fingerPrint;
    }

}
