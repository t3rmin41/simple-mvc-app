package simple.mvc.app.mapper;

import java.util.List;

import simple.mvc.bean.ProductBean;
import simple.mvc.jpa.Product;

public interface ProductMapper {

    ProductBean getProductBeanByProduct(Product jpa);
    
    ProductBean getProductBeanById(Long id);
    
    List<ProductBean> getProductBeansByProducts(List<Product> jpas);
    
    List<ProductBean> getAllProducts();
    
    ProductBean createProduct(ProductBean bean);
    
    ProductBean updateProduct(ProductBean bean);
    
    boolean deleteProductById(Long id);
}
