package com.example.models;

import java.util.Comparator;

public class RegularOrder extends Order{
    public RegularOrder(String orderId, String customerId){
        super(orderId,customerId);
    }

    @Override
    public String getOrderType(){
        return "Regular";
    }

    // bids is present in the parent class of Order
    @Override
    public Bid selectBestBid(){
        return bids.stream().min(Comparator.comparingDouble(Bid::getAmount)).orElse(null);
    }
}