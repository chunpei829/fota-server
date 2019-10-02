package com.arimacomm.fotaServer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @NotEmpty
    private String oem;
    
    @NotEmpty
    private String name;
    
    private String region;
    
    private String operator;
    
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id) {
    	this.id = id;
    }
    
    public String getOem() {
    	return oem;
    }
    
    public void setOem(String oem) {
    	this.oem = oem;
    }

    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }

    public String getRegion() {
    	return region;
    }
    
    public void setRegion(String region) {
    	this.region = region;
    }

    public String getOperator() {
    	return operator;
    }
    
    public void setOperator(String operator) {
    	this.operator = operator;
    }
}
