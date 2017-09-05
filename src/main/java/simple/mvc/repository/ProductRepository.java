package simple.mvc.repository;

import java.util.List;

import simple.mvc.jpa.Product;

public interface ProductRepository {

    List<Product> getAllProducts();
    
    Product getProductById(Long id);
    
    Product createProduct(Product jpa);
    
    Product updateProduct(Product jpa);
    
    boolean deleteProductById(Long id);
}
