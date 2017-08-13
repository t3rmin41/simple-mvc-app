package simple.mvc.app.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import simple.mvc.app.mapper.UserMapper;
import simple.mvc.bean.UserBean;
import simple.mvc.jpa.Role;
import simple.mvc.jpa.User;
import simple.mvc.repository.UserRepository;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserBean getUserBeanByNameAndPassword(String username, String password) {
        User jpa = userRepo.getUserByUsernameAndPassword(username, password);
        if (null != jpa) {
            return convertJpaToBean(jpa);
        } else {
            return null;
        }
    }

    @Override
    public UserBean createUser(UserBean bean) {
        User jpa = new User();
        jpa.setUsername(bean.getUsername());
        jpa.setPassword(bean.getPassword());
        jpa.setEnabled(true);
        User created = userRepo.createUser(jpa);
        List<Role> roles = convertRoleStringToRoles(bean.getRoles(), created);
        userRepo.createRoles(roles);
        return converUserToBeanByUserId(created.getId());
    }

    @Override
    public List<UserBean> getAllUsers() {
        List<UserBean> beans = new ArrayList<UserBean>();
        for (User jpa : userRepo.getAllUsers()) {
            beans.add(convertJpaToBean(jpa));
        }
        return beans;
    }

    @Override
    public boolean deleteUserById(Long id) {
        User jpa = userRepo.getUserById(id);
        return userRepo.deleteUser(jpa);
    }

    @Override
    public UserBean updateUser(UserBean bean) {
        User jpa = userRepo.getUserById(bean.getId());
        if (!bean.getPassword().isEmpty()) {
            jpa.setPassword(bean.getPassword());
        }
        jpa.setUsername(bean.getUsername());
        List<Role> newRoles = convertRoleStringToRoles(bean.getRoles(), jpa);
        userRepo.removeRoles(jpa.getRoles());
        userRepo.createRoles(newRoles);
        return convertJpaToBean(userRepo.updateUser(jpa));
    }

    @Override
    public List<String> convertUserRolesToUserBeanRoles(List<Role> roles) {
        List<String> roleNames = new ArrayList<String>();
        for (Role role : roles) {
            roleNames.add(role.getRole());
        }
        return roleNames;
    }

    @Override
    public UserBean converUserToBeanByUserId(Long id) {
        User jpa = userRepo.getUserById(id);
        if (null != jpa) {
            return convertJpaToBean(jpa);
        } else {
            return null;
        }
    }
    
    private List<Role> convertRoleStringToRoles(List<String> roleNames, User jpa) {
        List<Role> roles = new ArrayList<Role>();
        for (String rolename : roleNames) {
            Role jpaRole = new Role();
            jpaRole.setUser(jpa);
            jpaRole.setRole(rolename);
            roles.add(jpaRole);
        }
        return roles;
    }
    
    private UserBean convertJpaToBean(User jpa) {
        return new UserBean().setPassword(jpa.getPassword())
                .setUsername(jpa.getUsername())
                .setId(jpa.getId())
                .setRoles(convertUserRolesToUserBeanRoles(jpa.getRoles())).setEnabled(jpa.getEnabled());
    }

}
