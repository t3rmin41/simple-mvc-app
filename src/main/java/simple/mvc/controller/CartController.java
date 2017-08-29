package simple.mvc.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.bean.CartBean;
import simple.mvc.bean.OrderBean;
import simple.mvc.bean.ProductBean;
import simple.mvc.service.CartService;
import simple.mvc.service.ProductService;

@Controller
@Scope(value = "session")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductService productService;
    
    @Inject
    private CartBean cartBean;
    
    @RequestMapping(value = "/cart", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody CartBean getCart() {
        return cartBean;
    }
    
    @RequestMapping(value = "/cart/addOrder", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public @ResponseBody CartBean addOrderToCart(@RequestBody OrderBean orderBean, Principal principal) {
        ProductBean productBean = productService.getProductBeanById(orderBean.getProductId());
        orderBean.setProductName(productBean.getTitle());
        cartBean.getItems().add(orderBean);
        return cartBean;
    }
    
    @RequestMapping(value = "/cart/submit", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody CartBean submitCart() {
        cartService.submitCart(cartBean);
        cartBean.clear();
        return cartBean;
    }
}
