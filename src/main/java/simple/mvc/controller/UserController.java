package simple.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.bean.UserBean;
import simple.mvc.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/usersPage", method = RequestMethod.GET)
    public String viewUserspage() {
        return "users";
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserBean> getUsers() {
        return userService.getAllUsers();
    }
    
    @RequestMapping(value = "/users/create", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
    public void createUser(@RequestBody UserBean bean) {
        userService.createUser(bean);
    }
}
