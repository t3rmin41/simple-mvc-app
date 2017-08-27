package simple.mvc.service;

import java.util.List;

import simple.mvc.bean.OrderBean;

public interface OrderService {

    List<OrderBean> getAllOrders();
    OrderBean getOrderById(Long id);
    OrderBean createOrder(OrderBean bean);
    OrderBean updateOrder(OrderBean bean);
    boolean deleteOrderById(Long id);
}
