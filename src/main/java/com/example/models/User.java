package com.example.models;

// class keyword
public class User {
    // user ID & string will be inherited by custom child classes
    protected String userId;
    protected String name;

    // constructor
    public User (String userId, String name){
        this.userId = userId;
        this.name = name;
    }

    // getters (set is done via constructor)
    public String getUserId(){ return userId;}
    public String getName(){return name;}
}