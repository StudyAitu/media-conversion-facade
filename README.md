# Assignment #4 — Facade Design Pattern (Online Order Processing)

**Course:** ShP-2216 – Software Design Patterns (OP 6B06102)  
**Student:** Muratbek Ibrai (Group SE-2529, Astana IT University)  

---

## 1. Project Overview
This project implements the **Facade structural design pattern** in Java (JDK 17). 
The chosen domain is an **Online Order Processing System**, where a client needs to place an order that involves interacting with multiple complex, independent subsystem services: inventory reservation, payment processing, shipment arrangement, and customer notification.

Instead of forcing the client class to manage these subsystems directly and handle complex transactional flows, the `OrderFacade` exposes a simple, high-level API (`placeOrder()`).

---

## 2. Architecture & Subsystems

The system consists of the following components:

* **Subsystem Classes:**
  1. `InventoryService` — Handles stock reservation and rollback (releasing stock).
  2. `PaymentProcessor` — Processes financial transactions and supports refunds/rollbacks.
  3. `ShippingService` — Generates tracking and arranges delivery.
  4. `NotificationService` — Sends order confirmation emails to customers.
* **Facade Class:**
  * `OrderFacade` — Coordinates the subsystem calls in a strict sequential order and implements a compensation/rollback mechanism if a step fails (e.g., if a payment or shipping fails, previously reserved stock and funds are safely rolled back).
* **Client (`Main.java`):**
  * Interacts **only** with the `OrderFacade` and is completely decoupled from the underlying subsystem classes.

---

## 3. UML Class Diagram Structure

```text
+-------------------+             +-------------------------+
|      Client       | ----------\ |       OrderFacade       |
|    (Main.java)    |           | | - inventoryService      |
+-------------------+           | | - paymentProcessor      |
                                | | - shippingService       |
                                | | - notificationService   |
                                | +-------------------------+
                                | | + placeOrder()          |
                                | | + cancelOrder()         |
                                | +-------------------------+
                                             |
                  +--------------------------+--------------------------+
                  |                          |                          |
                  v                          v                          v
       +--------------------+     +--------------------+     +--------------------+
       |  InventoryService  |     |  PaymentProcessor  |     |  ShippingService   |
       +--------------------+     +--------------------+     +--------------------+
       | + reserveStock()   |     | + processPayment() |     | + arrangeShipping()|
       | + releaseStock()   |     | + refundPayment()  |     |                    |
4. Clean Code Principles Applied
Meaningful Names: Classes, methods, and variables (InventoryService, reserveStock, placeOrder) clearly express their intent and domain purpose.
Single Responsibility Principle (SRP): The OrderFacade strictly focuses on coordination and transaction flow control, while actual business logic is isolated within individual subsystem services.
Encapsulation / No Leaking of Details: The subsystem classes (InventoryService, PaymentProcessor, etc.) are hidden behind the facade. The client only sees high-level methods.
Small, Focused Classes: Each subsystem service has a single, well-defined responsibility, making the codebase modular and testable.
Robust Error Handling & Rollback: The facade manages failure scenarios gracefully by executing compensatory actions (releaseStock / refundPayment) to maintain data integrity.
5. How to Run
Open the project in IntelliJ IDEA (Java JDK 17 or higher recommended).
Locate src/Main.java.
Run the main method.
Check the console output for the simulated order execution steps.
       +--------------------+     +--------------------+     +--------------------+
