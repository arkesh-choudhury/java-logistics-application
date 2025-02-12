package com.example.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Order {
    protected String orderId;
    protected String customerId;
    protected List<Bid> bids;

    public Order(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.bids = new ArrayList<>(); 
    }

    // abstract methods
    public abstract String getOrderType();
    public abstract Bid selectBestBid(); 

    // getters
    public String getOrderId() { return orderId; }
    public String getCustomerId() { return customerId; }
    public List<Bid> getBids() { return bids; }

    // setter for bids
    public void setBids(List<Bid> bids) {
        if (bids != null) {
            this.bids = bids;
        } else {
            this.bids = new ArrayList<>(); 
        }
    }
}
