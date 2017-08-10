package simple.mvc.repository;

import java.util.List;

import simple.mvc.jpa.Role;
import simple.mvc.jpa.User;

public interface UserRepository {

    User getUserByUsernameAndPassword(String username, String password);
    
    User getUserById(Long id);
    
    User createUser(User jpa);
    
    void createRoles(List<Role> roles);
    
    List<User> getAllUsers();
    
    void deleteUser(User jpa);
    
    User updateUser(User jpa);
}
