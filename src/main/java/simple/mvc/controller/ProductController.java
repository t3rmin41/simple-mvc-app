package simple.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.bean.ProductBean;
import simple.mvc.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @RequestMapping(value = "/productsPage", method = RequestMethod.GET)
    public String viewProductspage() {
        return "products";
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<ProductBean> getProducts() {
        return productService.getAllProducts();
    }
    
}
