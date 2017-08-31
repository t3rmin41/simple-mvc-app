package simple.mvc.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simple.mvc.app.enums.RoleType;
import simple.mvc.app.mapper.UserMapper;
import simple.mvc.bean.UserBean;
import simple.mvc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public UserBean getUserByUsernamAndPassword(String userName, String password) {
        return userMapper.getUserBeanByNameAndPassword(userName, password);
    }

    @Override
    public UserBean createUser(UserBean bean) {
        if (checkValidRoles(bean.getRoles())) {
          return userMapper.createUser(bean);
        }
        return null;
    }

    @Override
    public List<UserBean> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userMapper.deleteUserById(id);
    }
    
    @Override
    public UserBean updateUser(UserBean bean) {
        if (checkValidRoles(bean.getRoles())) {
          return userMapper.updateUser(bean);
        }
        return null;
    }

    @Override
    public UserBean getUserById(Long id) {
        return userMapper.converUserToBeanByUserId(id);
    }

    @Override
    public UserBean getUserByUsername(String username) {
        return userMapper.getUserBeanByUsername(username);
    }

    private boolean checkValidRoles(List<String> roles) {
      if (null == roles || 0 == roles.size()) {
        return false;
      }
      for (String role : roles) {
        if (!Arrays.asList(RoleType.values()).contains(RoleType.getRoleTypeByName(role))) {
          return false;
        }
      }
      return true;
    }
}
