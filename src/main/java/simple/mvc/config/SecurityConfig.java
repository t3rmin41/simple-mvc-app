package simple.mvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import simple.mvc.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    private UserDetailsServiceImpl userServiceDetails;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetails);
    }
    /**/


    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
        .authoritiesByUsernameQuery("SELECT user_id, CONCAT('ROLE_',role) AS authority FROM roles WHERE user_id = (SELECT id FROM users WHERE username = ?)");
        //need to return database roles with ROLE_ prefix for Spring Security to process @PreAuthorize correctly in controller
    }
    /**/
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
      .authorizeRequests()
          .antMatchers("/ui-resources/**", "/").permitAll()
          .antMatchers(HttpMethod.POST, "/loginuser").permitAll()
          .anyRequest().authenticated()
          .and()
      .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/loginuser")
          .usernameParameter("username").passwordParameter("password")
          .successForwardUrl("/login/success")
          .failureUrl("/login?loginError=true")
          .permitAll()
          .and()
      .logout()
          .logoutUrl("/logout")
          .deleteCookies("JSESSIONID")
          .permitAll()
          .and()
       .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }
}
