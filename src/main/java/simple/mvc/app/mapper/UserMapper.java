package simple.mvc.app.mapper;

import java.util.List;

import simple.mvc.bean.UserBean;
import simple.mvc.jpa.Role;
import simple.mvc.jpa.User;

public interface UserMapper {

    public UserBean getUserBeanByNameAndPassword(String username, String password);

    void createUser(UserBean bean);
    
    List<UserBean> getAllUsers();
    
    void deleteUser(UserBean bean);
    
    UserBean updateUser(UserBean bean);

    List<String> convertUserRolesToUserBeanRoles(List<Role> roles);
    
}
