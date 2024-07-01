package com.example.reddit.indexModel;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "community_index")
public class CommunityIndex {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Date)
    private Date creationDate;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Boolean)
    private boolean isSuspended;

    @Field(type = FieldType.Text)
    private String suspendedReason;

    public CommunityIndex(String id, String name, Date creationDate, String description, boolean isSuspended, String suspendedReason) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
    }
    

    public CommunityIndex() {
		super();
	}


	// Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescriptionSr(String description) {
        this.description = description;
    }
    
    public void setDescriptionEn(String description) {
        this.description = description;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason;
    }

    // Constructors, methods (if any)
}
