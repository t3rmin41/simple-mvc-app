package simple.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
