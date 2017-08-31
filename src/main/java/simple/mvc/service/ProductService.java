package simple.mvc.service;

import java.util.List;

import simple.mvc.bean.ProductBean;

public interface ProductService {

    List<ProductBean> getAllProducts();
    
    ProductBean getProductBeanById(Long id);
}
