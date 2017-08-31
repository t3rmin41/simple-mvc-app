package simple.mvc.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import simple.mvc.app.enums.OrderStatus;
import simple.mvc.app.enums.RoleType;
import simple.mvc.bean.UserBean;
import simple.mvc.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/usersPage", method = RequestMethod.GET)
    public String viewUserspage(Principal principal) {
        return "users";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserBean> getUsers(Principal principal) {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/update", method = RequestMethod.PUT, consumes="application/json", produces = "application/json")
    public @ResponseBody UserBean updateUser(@RequestBody UserBean bean) {
        return userService.updateUser(bean);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }

    @RequestMapping(value = "/users/roles", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Map<RoleType, String> getUserRoleMap() {
        Map<RoleType, String> roleMap = new HashMap<RoleType, String>();
        for (RoleType role : RoleType.values()) {
          roleMap.put(role, role.getCode());
        }
        return roleMap;
    }
}
