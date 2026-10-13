package facade;

import model.Cart;
import model.Order;
import subsystem.InventoryService;
import subsystem.PaymentProcessor;
import subsystem.ShippingService;
import subsystem.NotificationService;

public class OrderFacade {
    private final InventoryService inventoryService;
    private final PaymentProcessor paymentProcessor;
    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentProcessor = new PaymentProcessor();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }

    public Order placeOrder(Cart cart, String customerName) {
        System.out.println("=== Starting Order Placement Process ===");
        String orderId = "ORD-" + System.currentTimeMillis();

        // Шаг 1: Резерв товара
        boolean stockReserved = inventoryService.reserveStock(cart.getProductId(), cart.getQuantity());
        if (!stockReserved) {
            System.out.println("Order failed: Out of stock.");
            return null;
        }

        // Шаг 2: Обработка платежа с механизмом отката (Rollback)
        boolean paymentSuccess = paymentProcessor.processPayment(customerName, cart.getAmount());
        if (!paymentSuccess) {
            System.out.println("Order failed: Payment declined. Initiating rollback...");
            inventoryService.releaseStock(cart.getProductId(), cart.getQuantity());
            return null;
        }

        // Шаг 3: Организация доставки
        try {
            shippingService.arrangeShipping(orderId, customerName);
        } catch (Exception e) {
            paymentProcessor.refundPayment(customerName, cart.getAmount());
            inventoryService.releaseStock(cart.getProductId(), cart.getQuantity());
            throw new RuntimeException("Shipping failed, order rolled back.");
        }

        // Шаг 4: Уведомление
        notificationService.sendConfirmationEmail(customerName, orderId);

        System.out.println("=== Order Successfully Placed! ===\n");
        return new Order(orderId, customerName, "COMPLETED");
    }

    public void cancelOrder(Order order) {
        System.out.println("=== Cancelling Order: " + order.getOrderId() + " ===");
        order.setStatus("CANCELLED");
        System.out.println("Order successfully cancelled.");
    }
}