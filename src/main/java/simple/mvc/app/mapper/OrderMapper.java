package simple.mvc.app.mapper;

import java.util.List;

import simple.mvc.bean.OrderBean;

public interface OrderMapper {

    List<OrderBean> getAllOrders();
    
    OrderBean getOrderById(Long id);
    
    OrderBean createOrder(OrderBean bean);
    
    OrderBean updateOrder(OrderBean bean);
    
    boolean deleteOrderById(Long id);
    
    OrderBean convertOrderToBeanById(Long id);
    
    List<OrderBean> getUserOrdersByUsername(String username);
}
