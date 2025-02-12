package com.example.models;

import java.util.Comparator;

public class ExpressOrder extends Order {
    public ExpressOrder(String orderId, String customerId) {
        super(orderId, customerId);
    }

    @Override
    public String getOrderType() {
        return "Express";
    }

    // polymorphism
    @Override
    public Bid selectBestBid() {
        return bids.stream()
                .min(Comparator.comparingInt(Bid::getEstimatedTime) 
                        .thenComparingDouble(Bid::getAmount))
                .orElseThrow(() -> new IllegalStateException("No bids available for selection"));
    }
}
