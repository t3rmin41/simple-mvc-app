package simple.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.bean.ProductBean;
import simple.mvc.bean.UserBean;
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
    
    @RequestMapping(value = "/products/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody ProductBean createProduct(@RequestBody ProductBean bean) {
        return productService.createProduct(bean);
    }

    @RequestMapping(value = "/productview/{productId}", method = RequestMethod.GET)
    public String viewUserEditPage(@PathVariable("productId") Long productId) {
        return "product";
    }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ProductBean getProductById(@PathVariable("id") Long id) {
        return productService.getProductBeanById(id);
    }

    @RequestMapping(value = "/products/update", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
    public @ResponseBody ProductBean updateProduct(@RequestBody ProductBean bean) {
        return productService.updateProduct(bean);
    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody boolean deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }
    
}
