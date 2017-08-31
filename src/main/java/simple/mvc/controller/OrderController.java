package simple.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.app.enums.OrderStatus;
import simple.mvc.bean.OrderBean;
import simple.mvc.service.OrderService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @RequestMapping(value = "/ordersPage", method = RequestMethod.GET)
    public String viewOrdersPage() {
        return "orders";
    }
    
    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<OrderBean> getOrders() {
        return orderService.getAllOrders();
    }
    
    @RequestMapping(value = "/orders/user/{username}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<OrderBean> getUserOrders(@PathVariable("username") String username) {
        return orderService.getUserOrdersByUsername(username);
    }
    
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody OrderBean getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderById(id);
    }
    
    @RequestMapping(value = "/orders/create", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public @ResponseBody OrderBean createOrder(@RequestBody OrderBean bean) {
        return orderService.createOrder(bean);
    }

    @RequestMapping(value = "/orders/update", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
    public @ResponseBody OrderBean updateOrder(@RequestBody OrderBean bean) {
        return orderService.updateOrder(bean);
    }

    @RequestMapping(value = "/orders/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody boolean deleteOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrderById(id);
    }
    
    @RequestMapping(value = "/orders/statusmap", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<OrderStatus, String> getOrderStatusMap() {
        Map<OrderStatus, String> statusMap = new HashMap<OrderStatus, String>();
        for (OrderStatus status : OrderStatus.values()) {
          statusMap.put(status, status.getCode());
        }
        return statusMap;
    }
}
