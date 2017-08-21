package simple.mvc.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody UserBean loginUser(String username, String password) {
        UserBean userBean = userService.getUserByUsernamAndPassword(username, password);
        return userBean;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }
    
    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String successfullLoginPage(Principal principal) {
        return "logged";
    }
    
}
