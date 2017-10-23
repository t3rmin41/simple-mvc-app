package simple.mvc.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        UserBean oldBean = userMapper.converUserToBeanByUserId(bean.getId());
        if (checkValidRoles(bean.getRoles())) {
          Set<String> oldRoles = new HashSet<String>(oldBean.getRoles());
          Set<String> newRoles = new HashSet<String>(bean.getRoles());
          userMapper.removeRoles(bean.getId(), getOldRolesDifference(oldRoles, newRoles));
          userMapper.addRoles(bean.getId(), getNewRolesDifference(oldRoles, newRoles));
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

    @Override
    public Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
      Set<String> commonRoles = new HashSet<String>();
      commonRoles.addAll(newRoles);
      Set<String> newRoleSetDiff = new HashSet<String>();
      newRoleSetDiff.addAll(newRoles);
      commonRoles.retainAll(oldRoles);
      newRoleSetDiff.removeAll(commonRoles);
      return newRoleSetDiff;
    }

    @Override
    public Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
      Set<String> commonRoles = new HashSet<String>();
      commonRoles.addAll(oldRoles);
      Set<String> oldRoleSetDiff = new HashSet<String>();
      oldRoleSetDiff.addAll(oldRoles);
      commonRoles.retainAll(newRoles);
      oldRoleSetDiff.removeAll(commonRoles);
      return oldRoleSetDiff;
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
