package simple.mvc.repository;

import java.util.List;

import simple.mvc.jpa.Order;

public interface OrderRepository {

    Order createOrder(Order order);
    
    List<Order> getOrders();
    
    List<Order> getUserOrders(String username);
    
    Order getOrderById(Long id);
    
    Order updateOrder(Order order);
    
    boolean deleteOrderById(Long id);
}
