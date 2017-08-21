package simple.mvc.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import simple.mvc.app.mapper.UserMapper;
import simple.mvc.bean.UserBean;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBean bean = userMapper.getUserBeanByUsername(username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for (String rolename : bean.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+rolename));
        }
        return new User(bean.getUsername(), bean.getPassword(), authorities);
    }

}
