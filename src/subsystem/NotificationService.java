package subsystem;

public class NotificationService {
    public void sendConfirmationEmail(String customer, String orderId) {
        System.out.println("[NotificationService] Sending confirmation email to " + customer + " for order " + orderId);
    }
}