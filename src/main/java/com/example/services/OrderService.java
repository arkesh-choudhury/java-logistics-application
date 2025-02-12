package com.example.services;

import com.example.models.Order;
import com.example.storage.JsonStorage;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class OrderService {
    private final JsonStorage<Order> orderStorage;

    public OrderService(String filePath) {
        this.orderStorage = new JsonStorage<>(filePath, new TypeReference<List<Order>>() {});
    }

    public void saveOrder(Order order) {
        List<Order> orders = orderStorage.loadFromJson();
        orders.add(order);
        orderStorage.saveToJson(orders);
    }

    public Order getOrderById(String orderId) {
        return orderStorage.loadFromJson().stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public List<Order> getAllOrders() {
        return orderStorage.loadFromJson();
    }
}
