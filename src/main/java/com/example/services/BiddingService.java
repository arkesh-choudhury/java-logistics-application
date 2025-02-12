package com.example.services;

import com.example.models.Bid;
import com.example.storage.JsonStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import java.util.stream.Collectors;

public class BiddingService {
    private final JsonStorage<Bid> bidStorage;

    public BiddingService(String filePath) {
        this.bidStorage = new JsonStorage<>(filePath, new TypeReference<List<Bid>>() {});
    }

    public void addBid(Bid bid) {
        List<Bid> bids = bidStorage.loadFromJson(); 
        bids.add(bid);
        bidStorage.saveToJson(bids); 
    }

    public List<Bid> getBidsForOrder(String orderId) {
        return bidStorage.loadFromJson().stream()
                .filter(b -> b.getOrderId().equals(orderId)) 
                .collect(Collectors.toList());
    }

    public void clearBidsForOrder(String orderId) {
        List<Bid> updatedBids = bidStorage.loadFromJson().stream()
                .filter(b -> !b.getOrderId().equals(orderId)) 
                .collect(Collectors.toList());
        bidStorage.saveToJson(updatedBids);
    }
}
