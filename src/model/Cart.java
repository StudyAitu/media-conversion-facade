package model;

public class Cart {
    private final String productId;
    private final int quantity;
    private final double amount;

    public Cart(String productId, int quantity, double amount) {
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getAmount() { return amount; }
}