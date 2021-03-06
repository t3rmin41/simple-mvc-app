package simple.mvc.app.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import simple.mvc.app.mapper.ProductMapper;
import simple.mvc.bean.ProductBean;
import simple.mvc.jpa.Product;
import simple.mvc.repository.ProductRepository;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ProductRepository productRepo;
    
    @Override
    public ProductBean getProductBeanByProduct(Product jpa) {
        ProductBean bean = new ProductBean();
        bean.setId(jpa.getId()).setTitle(jpa.getTitle()).setPrice(jpa.getPrice());
        return bean;
    }

    @Override
    public List<ProductBean> getProductBeansByProducts(List<Product> jpas) {
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for(Product jpa : jpas) {
            beans.add(new ProductBean().setId(jpa.getId()).setPrice(jpa.getPrice()).setTitle(jpa.getTitle()));
        }
        return beans;
    }

    @Override
    public List<ProductBean> getAllProducts() {
        return getProductBeansByProducts(productRepo.getAllProducts());
    }

    @Override
    public ProductBean getProductBeanById(Long id) {
        return convertJpaToBean(productRepo.getProductById(id));
    }

    @Override
    public ProductBean createProduct(ProductBean bean) {
      Product jpa = new Product();
      jpa.setPrice(bean.getPrice());
      jpa.setTitle(bean.getTitle());
      jpa = productRepo.createProduct(jpa);
      return convertJpaToBean(jpa);
    }

    @Override
    public ProductBean updateProduct(ProductBean bean) {
      Product jpa = productRepo.getProductById(bean.getId());
      jpa.setPrice(bean.getPrice());
      return convertJpaToBean(productRepo.updateProduct(jpa));
    }

    @Override
    public boolean deleteProductById(Long id) {
      return productRepo.deleteProductById(id);
    }
    
    private ProductBean convertJpaToBean(Product jpa) {
        return new ProductBean().setId(jpa.getId()).setTitle(jpa.getTitle()).setPrice(jpa.getPrice()).setDeleted(jpa.isDeleted());
    }

}
