package com.uvish.springApi.model;

import org.springframework.stereotype.Component;

@Component
public class UserResponseModel {
    private long id;
    private String username;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
