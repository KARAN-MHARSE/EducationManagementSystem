package com.aurionpro.ems.model;

public class Authentication {
    private int authId;
    private String username;
    private String password;
    private int userId;

    
    public Authentication() {
    	
    }

    public Authentication(String username, String password, int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    // Getters and Setters
    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    @Override
    public String toString() {
        return "Authentication [authId=" + authId + ", username=" + username + ", userId=" + userId + "]";
    }
}
