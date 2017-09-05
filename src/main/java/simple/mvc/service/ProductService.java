package simple.mvc.service;

import java.util.List;

import simple.mvc.bean.ProductBean;

public interface ProductService {

    List<ProductBean> getAllProducts();
    
    ProductBean getProductBeanById(Long id);
    
    ProductBean createProduct(ProductBean bean);
    
    ProductBean updateProduct(ProductBean bean);
    
    boolean deleteProductById(Long id);
}
