package simple.mvc.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simple.mvc.app.enums.OrderStatus;
import simple.mvc.app.mapper.OrderMapper;
import simple.mvc.bean.OrderBean;
import simple.mvc.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderBean> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public OrderBean getOrderById(Long id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public OrderBean createOrder(OrderBean bean) {
        return orderMapper.createOrder(bean);
    }

    @Override
    public OrderBean updateOrder(OrderBean bean) {
        //if (Arrays.asList(OrderStatus.values()).contains(bean.getStatus())) {
        //  return orderMapper.updateOrder(bean);
        //}
        for (OrderStatus status : OrderStatus.values()) {
          if (status.name().equals(bean.getStatus())) {
            return orderMapper.updateOrder(bean);
          }
        }
        return null;
    }

    @Override
    public boolean deleteOrderById(Long id) {
        return orderMapper.deleteOrderById(id);
    }

    @Override
    public List<OrderBean> getUserOrdersByUsername(String username) {
        return orderMapper.getUserOrdersByUsername(username);
    }
    
}
