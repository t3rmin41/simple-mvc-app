package simple.mvc.service;

import simple.mvc.bean.UserBean;

public interface UserService {

    UserBean getUserByUsernamAndPassword(String userName, String password);
    
}
