package simple.mvc.app.mapper;

import java.util.List;
import java.util.Set;

import simple.mvc.app.enums.RoleType;
import simple.mvc.bean.UserBean;
import simple.mvc.jpa.Role;

public interface UserMapper {
  UserBean getUserBeanByUsername(String username);
  
  UserBean getUserBeanByNameAndPassword(String username, String password);

  UserBean converUserToBeanByUserId(Long id);
  
  UserBean createUser(UserBean bean);
  
  List<UserBean> getAllUsers();
  
  boolean deleteUserById(Long id);
  
  UserBean updateUser(UserBean bean);

  List<String> convertUserRolesToUserBeanRoles(Set<Role> roles);

  Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles);
  
  Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles);
}
