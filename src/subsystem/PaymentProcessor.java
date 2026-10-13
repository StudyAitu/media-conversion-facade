package subsystem;

public class PaymentProcessor {
    public boolean processPayment(String customer, double amount) {
        System.out.println("[PaymentProcessor] Processing payment of $" + amount + " for " + customer);
        return true;
    }

    public void refundPayment(String customer, double amount) {
        System.out.println("[PaymentProcessor] ROLLBACK: Refunding $" + amount + " to " + customer);
    }
}