package com.example.reddit.indexModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "post_index")
public class PostIndex {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private Date creationDate;

    @Field(type = FieldType.Text)
    private String imagePath;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text, analyzer = "english")
    private String text;

    public PostIndex(String id, Date creationDate, String imagePath, String title, String text) {
        this.id = id;
        this.creationDate = creationDate;
        this.imagePath = imagePath;
        this.title = title;
        this.text = text;
    }
    
    

    // Getters and Setters

    public PostIndex() {
		super();
	}



	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Constructors, methods (if any)
}
