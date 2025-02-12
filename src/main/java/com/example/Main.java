package com.example;

import com.example.models.*;
import com.example.storage.JsonStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        JsonStorage<Customer> customerStorage = new JsonStorage<>("src/main/resources/customers.json", new TypeReference<List<Customer>>() {});
        JsonStorage<Order> orderStorage = new JsonStorage<>("src/main/resources/orders.json", new TypeReference<List<Order>>() {});
        JsonStorage<DeliveryAgent> agentStorage = new JsonStorage<>("src/main/resources/agents.json", new TypeReference<List<DeliveryAgent>>() {});
        JsonStorage<Bid> bidStorage = new JsonStorage<>("src/main/resources/bids.json", new TypeReference<List<Bid>>() {});

        List<DeliveryAgent> agents = agentStorage.loadFromJson();
        if (agents.isEmpty()) {
            agents.add(new DeliveryAgent("A001", "John"));
            agents.add(new DeliveryAgent("A002", "Doe"));
            agents.add(new DeliveryAgent("A003", "Alice"));
            agentStorage.saveToJson(agents);
        }

        // 1. creating a customer
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        
        Customer customer = new Customer(customerId, customerName, 100.0);
        List<Customer> customers = customerStorage.loadFromJson();
        customers.add(customer);
        customerStorage.saveToJson(customers);
        
        System.out.println("\nCustomer Created!");
        System.out.println("Name: " + customer.getName());
        System.out.println("Wallet Balance: $" + customer.getWalletBalance());
        
        // 2. order type (polymorphism)
        System.out.println("\nChoose Order Type:");
        System.out.println("1. Regular Order");
        System.out.println("2. Express Order");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Order order = (choice == 1) ? new RegularOrder("O001", customer.getUserId()) : new ExpressOrder("O002", customer.getUserId());
        
        List<Order> orders = orderStorage.loadFromJson();
        orders.add(order);
        orderStorage.saveToJson(orders);

        System.out.println("\nOrder Created: " + order.getOrderType());
        

        List<Bid> bids = new ArrayList<>();
        bids.add(new Bid("A001", 50.0, 30));
        bids.add(new Bid("A002", 45.0, 25));
        bids.add(new Bid("A003", 55.0, 20));
        bidStorage.saveToJson(bids);
        
        order.setBids(bids);
        
        System.out.println("\nAvailable Bids:");
        for (Bid bid : bids) {
            System.out.println("Agent: " + bid.getAgentId() + " | Amount: $" + bid.getAmount() + " | Estimated Time: " + bid.getEstimatedTime() + " mins");
        }
        
        // 4. bid selection based on order type (regular/express)
        Bid bestBid = order.selectBestBid();
        System.out.println("\nBest Bid Selected: ");
        System.out.println("Agent: " + bestBid.getAgentId() + " | Amount: $" + bestBid.getAmount());
        
        DeliveryAgent assignedAgent = agents.stream()
                .filter(agent -> agent.getUserId().equals(bestBid.getAgentId()))
                .findFirst()
                .orElse(null);
        
        if (assignedAgent != null) {
            System.out.println("\nAssigned Delivery Agent: " + assignedAgent.getName());
        }
        
        System.out.println("\nProcessing Payment...");
        boolean paymentSuccess = customer.makePayment(bestBid.getAmount());
        customerStorage.saveToJson(customers);
        
        if (paymentSuccess) {
            System.out.println("\nPayment Successful!");
            System.out.println("Remaining Wallet Balance: $" + customer.getWalletBalance());
        } else {
            System.out.println("\nPayment Failed. Insufficient Balance.");
            return;
        }
        
        // 6. rating the delivery agent
        System.out.print("\nRate the delivery agent (1-5): ");
        double rating = scanner.nextDouble();
        if (assignedAgent != null) {
            assignedAgent.addRating(rating);
            System.out.println("Updated Rating for " + assignedAgent.getName() + ": " + assignedAgent.getRating());
            System.out.println("Total Deliveries Completed: " + assignedAgent.getTotalDeliveries());
            agentStorage.saveToJson(agents);
        }
        
        System.out.println("\nOrder Process Complete!");
        scanner.close();
    }
}
