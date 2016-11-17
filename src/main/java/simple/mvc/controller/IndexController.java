package simple.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import simple.mvc.service.ProductService;

@Controller
public class IndexController {

  @Autowired
  private ProductService products;
  
  @RequestMapping(value="/", method = RequestMethod.GET)
  public String index() {
    return "";
  }
}
