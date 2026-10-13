import facade.OrderFacade;
import model.Cart;
import model.Order;

public class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();

        Cart cart = new Cart("PROD-MACBOOK-AIR", 1, 1200.0);
        String customer = "Ибрай Муратбек";

        Order order = orderFacade.placeOrder(cart, customer);

        if (order != null) {
            System.out.println("Client received Order ID: " + order.getOrderId());
        }
    }
}