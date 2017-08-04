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
        bean.setTitle(jpa.getTitle()).setPrice(jpa.getPrice());
        return bean;
    }

    @Override
    public List<ProductBean> getProductBeansByProducts(List<Product> jpas) {
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for(Product jpa : jpas) {
            beans.add(new ProductBean().setPrice(jpa.getPrice()).setTitle(jpa.getTitle()));
        }
        return beans;
    }

    @Override
    public List<ProductBean> getAllProducts() {
        return getProductBeansByProducts(productRepo.getAllProducts());
    }

}
