package com.arimacomm.fotaServer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Delta {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
//    @NotEmpty
    private Integer sourceVersionId;
    
//    @NotEmpty
    private Integer versionId;
    
//    @NotEmpty
    private String status;
    //00:initial
    //01:testing
    //02:release

//    @NotEmpty
    private String filePath;

//    @NotEmpty
    private Long fileSize;

//    @NotEmpty
    private String md5Sum;
    
    public Integer getId() {
    	return id;
    }
    public void setId(Integer id) {
    	this.id = id;
    }
    
    public Integer getVersionId() {
    	return versionId;
    }
    public void setVersionId(Integer versionId) {
    	this.versionId = versionId;
    }
    
    public Integer getSourceVersionId() {
    	return sourceVersionId;
    }
    public void setSourceVersionId(Integer sourceVersionId) {
    	this.sourceVersionId = sourceVersionId;
    }
    
    public String getStatus() {
    	return status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public String getFilePath() {
    	return filePath;
    }
    public void setFilePath(String filePath) {
    	this.filePath = filePath;
    }
    
    public Long getFileSize() {
    	return fileSize;
    }
    public void setFileSize(Long fileSize) {
    	this.fileSize = fileSize;
    }
    
    public String getMd5Sum() {
    	return md5Sum;
    }
    public void setMd5Sum(String md5Sum) {
    	this.md5Sum = md5Sum;
    }

}
