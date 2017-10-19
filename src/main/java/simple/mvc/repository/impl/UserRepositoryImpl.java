package simple.mvc.repository.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import simple.mvc.jpa.Role;
import simple.mvc.jpa.User;
import simple.mvc.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private static Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
  
  @PersistenceContext
  private EntityManager em;
  
  @Override
  @Transactional
  public User getUserByUsernameAndPassword(String username, String password) {
      String q = "SELECT u FROM User u WHERE u.username = :pusername AND u.password = :ppassword";
      TypedQuery<User> query = em.createQuery(q, User.class);
      query.setParameter("pusername", username);
      query.setParameter("ppassword", username);
      List<User> users = query.getResultList();
      if (1 == users.size()) {
          return users.get(0);
      } else {
          return null;
      }
  }

  @Override
  @Transactional
  public User createUser(User jpa) {
      return em.merge(jpa);
  }

  @Override
  @Transactional
  public List<User> getAllUsers() {
      String q = "SELECT u FROM User u WHERE u.enabled = true";
      TypedQuery<User> query = em.createQuery(q, User.class);
      return query.getResultList();
  }

  @Override
  @Transactional
  public boolean deleteUser(User jpa) {
      boolean result = false;
      try {
          em.remove(jpa);
          result = true;
      } catch (IllegalArgumentException e) {
          log.error(e.getMessage());
      } catch (TransactionRequiredException e) {
          log.error(e.getMessage());
      }
      return result;
  }

  @Override
  @Transactional
  public User updateUser(User jpa) {
      User updated = em.merge(jpa);
      return getUserById(updated.getId());
  }

  @Override
  @Transactional
  public void createRoles(Set<Role> roles) {
      for (Role role : roles) {
          em.merge(role);
      }
  }
  
  @Override
  @Transactional
  public void removeRoles(Set<Role> roles) {
      for (Role role : roles) {
          String q = "DELETE FROM Role r WHERE r.role = :role AND r.user = :user AND r.active = 1";
          Query query = em.createQuery(q);
          query.setParameter("role", role.getRole());
          query.setParameter("user", role.getUser());
          int result = query.executeUpdate();
      }
  }

  @Override
  @Transactional
  public User getUserById(Long id) {
      String q = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :pid";
      TypedQuery<User> query = em.createQuery(q, User.class);
      query.setParameter("pid", id);
      return query.getSingleResult();
  }

  @Override
  @Transactional
  public User getUserByUsername(String username) {
      String q = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :pusername";
      TypedQuery<User> query = em.createQuery(q, User.class);
      query.setParameter("pusername", username);
      return query.getSingleResult();
  }

  @Override
  @Transactional
  public Set<Role> getUserRolesByNames(User user, Set<String> roleNames) {
      String q = "SELECT r FROM Role r WHERE r.role IN :roles AND r.user = :user AND r.active = 1";
      TypedQuery<Role> query = em.createQuery(q, Role.class);
      query.setParameter("roles", roleNames);
      query.setParameter("user", user);
      List<Role> resultList = query.getResultList();
      Set<Role> roles = new HashSet<Role>();
      roles.addAll(resultList);
      return roles;
  }
}
