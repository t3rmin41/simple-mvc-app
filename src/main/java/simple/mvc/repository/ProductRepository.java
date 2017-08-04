package simple.mvc.repository;

import java.util.List;

import simple.mvc.jpa.Product;

public interface ProductRepository {

    List<Product> getAllProducts();
}
