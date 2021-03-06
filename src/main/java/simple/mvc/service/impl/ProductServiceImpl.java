package simple.mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simple.mvc.app.mapper.ProductMapper;
import simple.mvc.bean.ProductBean;
import simple.mvc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductMapper productMapper;

  @Override
  public List<ProductBean> getAllProducts() {
      return productMapper.getAllProducts();
  }

  @Override
  public ProductBean getProductBeanById(Long id) {
      return productMapper.getProductBeanById(id);
  }

  @Override
  public ProductBean createProduct(ProductBean bean) {
    return productMapper.createProduct(bean);
  }

  @Override
  public ProductBean updateProduct(ProductBean bean) {
    return productMapper.updateProduct(bean);
  }

  @Override
  public boolean deleteProductById(Long id) {
    return productMapper.deleteProductById(id);
  }
  
}
