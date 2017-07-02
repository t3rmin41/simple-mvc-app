package simple.mvc.repository;

import simple.mvc.jpa.User;

public interface UserRepository {

    User getUserByUsernameAndPassword(String username, String password);
    
}
