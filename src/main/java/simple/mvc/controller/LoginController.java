package simple.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import simple.mvc.bean.UserBean;
import simple.mvc.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
    
    @RequestMapping(value = "/loginuser", method = RequestMethod.POST, produces = "application/json")
    public UserBean loginUser(String username, String password) {
        return userService.getUserByUsernamAndPassword(username, password);
    }
    
    
}
