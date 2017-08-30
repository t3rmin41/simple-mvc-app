package simple.mvc.app.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simple.mvc.app.mapper.OrderMapper;
import simple.mvc.bean.OrderBean;
import simple.mvc.jpa.Order;
import simple.mvc.jpa.Product;
import simple.mvc.jpa.User;
import simple.mvc.repository.OrderRepository;
import simple.mvc.repository.ProductRepository;
import simple.mvc.repository.UserRepository;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderRepository orderRepo;
    
    @Autowired
    private ProductRepository productRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public List<OrderBean> getAllOrders() {
        List<Order> orders = orderRepo.getOrders();
        List<OrderBean> beans = new ArrayList<OrderBean>();
        for (Order jpa : orders) {
            beans.add(convertJpaToBean(jpa));
        }
        return beans;
    }

    @Override
    public List<OrderBean> getUserOrdersByUsername(String username) {
        List<Order> orders = orderRepo.getUserOrders(username);
        List<OrderBean> beans = new ArrayList<OrderBean>();
        for (Order jpa : orders) {
            beans.add(convertJpaToBean(jpa));
        }
        return beans;
    }
    
    @Override
    public OrderBean getOrderById(Long id) {
        return convertJpaToBean(orderRepo.getOrderById(id));
    }

    @Override
    public OrderBean createOrder(OrderBean bean) {
        return convertJpaToBean(orderRepo.createOrder(convertBeanToJpa(bean)));
    }

    @Override
    public OrderBean updateOrder(OrderBean bean) {
        Order jpa = orderRepo.getOrderById(bean.getId());
        if (null != bean.getPrice()) {
            jpa.setPrice(bean.getPrice());
        }
        if (null != bean.getStatus()) {
            jpa.setStatus(bean.getStatus());
        }
        return convertJpaToBean(orderRepo.updateOrder(jpa));
    }

    @Override
    public boolean deleteOrderById(Long id) {
        return orderRepo.deleteOrderById(id);
    }

    @Override
    public OrderBean convertOrderToBeanById(Long id) {
        Order jpa = orderRepo.getOrderById(id);
        if (null != jpa) {
            return convertJpaToBean(jpa);
        } else {
            return null;
        }
    }

    private OrderBean convertJpaToBean(Order jpa) {
        return new OrderBean().setId(jpa.getId()).setProductId(jpa.getProduct().getId())
                  .setProductName(jpa.getTitle()).setPrice(jpa.getPrice()).setOrderedBy(jpa.getOrderedBy().getUsername())
                  .setStatus(jpa.getStatus()).setCreated(jpa.getCreated()).setUpdated(jpa.getUpdated());
    }
    
    private Order convertBeanToJpa(OrderBean bean) {
        Order jpa = new Order();
        jpa.setId(bean.getId());
        Product product = productRepo.getProductById(bean.getProductId());
        User user = userRepo.getUserByUsername(bean.getOrderedBy());
        jpa.setProduct(product);
        jpa.setPrice(product.getPrice());
        jpa.setTitle(product.getTitle());
        jpa.setStatus(bean.getStatus());
        jpa.setOrderedBy(user);
        jpa.setCreated(bean.getCreated());
        jpa.setUpdated(bean.getUpdated());
        return jpa;
    }


    
}
