package simple.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simple.mvc.bean.CartBean;
import simple.mvc.bean.OrderBean;
import simple.mvc.service.CartService;
import simple.mvc.service.OrderService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderService orderService;
    
    @Override
    public CartBean submitCart(CartBean bean) {
        for (OrderBean orderBean : bean.getItems()) {
            orderService.createOrder(orderBean);
        }
        return bean;
    }

}
