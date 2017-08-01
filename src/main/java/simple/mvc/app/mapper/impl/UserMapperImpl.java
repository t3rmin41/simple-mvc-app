package simple.mvc.app.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import simple.mvc.app.mapper.UserMapper;
import simple.mvc.bean.UserBean;
import simple.mvc.jpa.User;
import simple.mvc.repository.UserRepository;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserBean getUserBeanByNameAndPassword(String username, String password) {
        User jpa = userRepo.getUserByUsernameAndPassword(username, password);
        return new UserBean().setPassword(jpa.getPassword()).setUsername(jpa.getUsername());
    }

}
