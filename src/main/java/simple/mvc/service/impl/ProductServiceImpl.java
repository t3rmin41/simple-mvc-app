package simple.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simple.mvc.repository.ProductRepository;
import simple.mvc.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepo;
  
}
