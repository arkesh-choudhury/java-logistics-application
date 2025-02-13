# Java Logistics Application

This repository contains a Java-based logistics management application designed to streamline and optimize logistics operations. The application provides functionalities for managing customers, orders, delivery agents, and bids, ensuring efficient transportation and delivery processes.

## Features

- **Customer Management**: Create and manage customers with unique IDs and wallet balances.
- **Order Management**: Support for Regular and Express orders using polymorphism.
- **Bid System**: Delivery agents place bids for order fulfillment based on pricing and estimated time.
- **Best Bid Selection**: Automated selection of the best bid based on order type.
- **Payment Processing**: Customers make payments, and wallet balances are updated accordingly.
- **Delivery Agent Management**: Agents are assigned to orders and can be rated based on their performance.
- **JSON-Based Storage**: Persistent data storage using JSON files.

## Object-Oriented Programming (OOP) Features

This project follows core OOP principles such as encapsulation, polymorphism, inheritance, and abstraction:

1. **Encapsulation**: Data fields in classes like `Customer`, `Order`, and `Bid` are private or protected, accessed through getters and setters.
2. **Polymorphism**: The `Order` class is extended by `RegularOrder`, allowing different implementations of the `selectBestBid` method.
3. **Inheritance**: The `User` class acts as a base class, providing common properties (`userId`, `name`) to its subclasses like `Customer`. Similarly, `Order` serves as a parent class for specialized order types such as `RegularOrder`, ensuring code reusability and a structured hierarchy.
4. **Abstraction**: The `Order` class is abstract and provides a framework for different order types without specifying implementation details.

### Example Code Snippets

#### **Encapsulation in `Customer` Class**
```java
package com.example.models;

public class Customer extends User {
    private double walletBalance; 

    public Customer(String userId, String name, double walletBalance) {
        super(userId, name);
        this.walletBalance = walletBalance;
    }

    public double getWalletBalance() {
        return walletBalance;
    }
}
```

#### **Abstraction in `Order` Class**
```java
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

    public abstract String getOrderType();
    public abstract Bid selectBestBid(); 
}
```

#### **Polymorphism in `RegularOrder` Class**
```java
package com.example.models;

import java.util.Comparator;

public class RegularOrder extends Order{
    public RegularOrder(String orderId, String customerId){
        super(orderId, customerId);
    }

    @Override
    public String getOrderType(){
        return "Regular";
    }

    @Override
    public Bid selectBestBid(){
        return bids.stream().min(Comparator.comparingDouble(Bid::getAmount)).orElse(null);
    }
}
```

#### **Inheritance in `User` and `Customer` Classes**
```java
package com.example.models;

public class User {
    protected String userId;
    protected String name;

    public User(String userId, String name){
        this.userId = userId;
        this.name = name;
    }
}
```
```java
package com.example.models;

public class Customer extends User {
    private double walletBalance;

    public Customer(String userId, String name, double walletBalance) {
        super(userId, name);
        this.walletBalance = walletBalance;
    }
}
```
This example demonstrates how `Customer` inherits properties from `User`, avoiding redundancy and promoting reusability.

#### **Error Handling in `JsonStorage` Class**
```java
public List<T> loadFromJson() {
    try {
        return objectMapper.readValue(new File(filePath), typeReference);
    } catch (IOException e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
```
This demonstrates how `JsonStorage` handles potential file-related errors using a try-catch block, ensuring the program does not crash due to missing or unreadable JSON files.

## Technologies Used

- **Java**: Core programming language for the application.
- **Maven**: Dependency management and build automation tool.
- **Jackson Library**: JSON data parsing and storage.
- **Object-Oriented Programming (OOP)**: Utilization of encapsulation, polymorphism, inheritance, and abstraction.
- **Exception Handling**: Robust error handling mechanisms to prevent unexpected failures.

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/arkesh-choudhury/java-logistics-application.git
   ```
2. Navigate to the project directory:
   ```sh
   cd java-logistics-application
   ```
3. Build the project using Maven:
   ```sh
   mvn clean package
   ```
4. Run the generated JAR file:
   ```sh
   java -jar target/maven-app-1.0-SNAPSHOT.jar
   ```

## Usage

- Run the application and follow the prompts to create customers, place orders, and manage deliveries.
- Orders are assigned based on the best bid from delivery agents.
- Payments are deducted from the customer's wallet.
- Agents receive ratings based on delivery performance.
