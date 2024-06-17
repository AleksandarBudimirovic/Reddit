package com.example.reddit.dto;

import com.example.reddit.model.Flair;

public class FlairDTO {

    private Long id;
    private String name;

    public FlairDTO(Flair flair) {
        this.id = flair.getId();
        this.name = flair.getName();
    }

    public FlairDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

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
}
