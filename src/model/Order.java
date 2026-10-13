package model;

public class Order {
    private String orderId;
    private String customerName;
    private String status;

    public Order(String orderId, String customerName, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}