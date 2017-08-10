package simple.mvc.service;

import java.util.List;

import simple.mvc.bean.UserBean;

public interface UserService {

    UserBean getUserByUsernamAndPassword(String userName, String password);
    
    UserBean createUser(UserBean bean);
    
    List<UserBean> getAllUsers();
    
    void deleteUser(UserBean bean);
    
    UserBean updateUser(UserBean bean);
}
