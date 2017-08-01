package simple.mvc.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"simple.mvc.controller", "simple.mvc.service.impl", "simple.mvc.repository.impl", "simple.mvc.app.mapper.impl", "simple.mvc.util"})
@EntityScan(basePackages = {"simple.mvc.jpa"})
public class AppConfig {

  @Bean
  public ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
      registrationBean.addUrlMappings("/h2-console/*");
      return registrationBean;
  }

}
