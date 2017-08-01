package simple.mvc.app.mapper;

import simple.mvc.bean.UserBean;

public interface UserMapper {

    public UserBean getUserBeanByNameAndPassword(String username, String password);

}
