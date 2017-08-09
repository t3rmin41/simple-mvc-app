package simple.mvc.service.impl;

import java.util.List;

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

    @Override
    public void createUser(UserBean bean) {
        userMapper.createUser(bean);
    }

    @Override
    public List<UserBean> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void deleteUser(UserBean bean) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public UserBean updateUser(UserBean bean) {
        // TODO Auto-generated method stub
        return null;
    }

}
