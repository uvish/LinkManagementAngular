package com.uvish.springApi.model;

import org.springframework.stereotype.Component;

@Component
public class UserResponseModel {
    private long id;
    private String username;
    private boolean loggedIn;

    public long getId() {
        return this.id;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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
