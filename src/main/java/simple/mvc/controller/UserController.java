package simple.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public @ResponseBody UserBean createUser(@RequestBody UserBean bean) {
        return userService.createUser(bean);
    }
    
    @RequestMapping(value = "/userview/{userId}", method = RequestMethod.GET)
    public String viewUserEditPage(@PathVariable("userId") Long userId) {
        return "user";
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody UserBean getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
    
    @RequestMapping(value = "/users/update", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
    public @ResponseBody UserBean updateUser(@RequestBody UserBean bean) {
        return userService.updateUser(bean);
    }
    
    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }
}
