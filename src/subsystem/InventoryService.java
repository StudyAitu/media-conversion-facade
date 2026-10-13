package subsystem;

public class InventoryService {
    public boolean reserveStock(String productId, int quantity) {
        System.out.println("[InventoryService] Reserving " + quantity + " units of product: " + productId);
        return true;
    }

    public void releaseStock(String productId, int quantity) {
        System.out.println("[InventoryService] ROLLBACK: Releasing " + quantity + " units of product: " + productId);
    }
}