package com.example.models;

// inheriting from user
public class DeliveryAgent extends User {
    private double rating; 
    private int totalDeliveries; 

    public DeliveryAgent(String userId, String name) {
        super(userId, name);
        this.rating = 0.0;
        this.totalDeliveries = 0;
    }

    public void addRating(double newRating) {
        totalDeliveries++;
        this.rating = ((this.rating * (totalDeliveries - 1)) + newRating) / totalDeliveries; 
    }

    public double getRating() {
        return rating;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }
}
