package simple.mvc.mapper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import simple.mvc.app.mapper.UserMapper;
import simple.mvc.app.mapper.impl.UserMapperImpl;
import simple.mvc.bean.UserBean;
import simple.mvc.jpa.Role;

public class UserMapperTest {

    private UserMapper userMapper = new UserMapperImpl();
    
    @Test
    public void testConvertUserRolesToUserBeanRoles() {
        List<Role> roles = new ArrayList<Role>();
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roles.add(adminRole);
        List<String> roleNames = userMapper.convertUserRolesToUserBeanRoles(roles);
        assertEquals(1, roleNames.size());
        assertTrue("ADMIN".equals(roleNames.get(0)));
    }
}
