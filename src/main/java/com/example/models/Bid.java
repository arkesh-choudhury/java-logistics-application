package com.example.models;

public class Bid {
    private String agentId;
    private double amount;
    private int estimatedTime; 
    private String orderId;

    public Bid(String agentId, double amount, int estimatedTime) {
        this.agentId = agentId;
        this.amount = amount;
        this.estimatedTime = estimatedTime;
    }

    // getters
    public String getAgentId() { return agentId; }
    public double getAmount() { return amount; }
    public int getEstimatedTime() { return estimatedTime; }
    public String getOrderId() {
        return orderId;
    }
}
