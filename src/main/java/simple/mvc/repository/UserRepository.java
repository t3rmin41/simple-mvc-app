package simple.mvc.repository;

import java.util.List;
import java.util.Set;

import simple.mvc.jpa.Role;
import simple.mvc.jpa.User;

public interface UserRepository {

  User getUserByUsername(String username);
  
  User getUserByUsernameAndPassword(String username, String password);
  
  User getUserById(Long id);
  
  User createUser(User jpa);
  
  void createRoles(Set<Role> roles);
  
  void removeRoles(Set<Role> roles);
  
  List<User> getAllUsers();
  
  boolean deleteUser(User jpa);
  
  User updateUser(User jpa);
  
  Set<Role> getUserRolesByNames(User jpa, Set<String> rolesNames);
}
