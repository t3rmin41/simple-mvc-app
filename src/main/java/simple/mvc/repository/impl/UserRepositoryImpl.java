package simple.mvc.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import simple.mvc.jpa.User;
import simple.mvc.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional
    public User getUserByUsernameAndPassword(String username, String password) {
        String q = "SELECT u FROM User u WHERE u.username = '"+username+"' AND u.password = '"+password+"'";
        //String q = "SELECT u FROM User u WHERE u.username = :pusername AND u.password = :ppassword";
        TypedQuery<User> query = em.createQuery(q, User.class);
        //query.setParameter("pusername", username);
        //query.setParameter("ppassword", username);
        List<User> users = query.getResultList();
        if (1 == users.size()) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
