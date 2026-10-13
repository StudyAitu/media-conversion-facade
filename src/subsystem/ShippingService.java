package subsystem;

public class ShippingService {
    public String arrangeShipping(String orderId, String customer) {
        System.out.println("[ShippingService] Arranging shipping for order " + orderId + " to " + customer);
        return "TRACKING-XYZ-999";
    }
}