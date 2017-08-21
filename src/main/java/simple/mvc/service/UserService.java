package simple.mvc.service;

import java.util.List;

import simple.mvc.bean.UserBean;

public interface UserService {

    UserBean getUserByUsernamAndPassword(String userName, String password);
    
    UserBean getUserByUsername(String username);
    
    UserBean getUserById(Long id);
    
    UserBean createUser(UserBean bean);
    
    List<UserBean> getAllUsers();
    
    boolean deleteUserById(Long id);
    
    UserBean updateUser(UserBean bean);
}
