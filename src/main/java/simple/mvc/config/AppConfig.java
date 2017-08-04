package simple.mvc.config;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"simple.mvc.controller", "simple.mvc.service.impl", "simple.mvc.repository.impl", "simple.mvc.app.mapper.impl", "simple.mvc.util"})
@EntityScan(basePackages = {"simple.mvc.jpa"})
public class AppConfig {

  @Value("${spring.profiles.active}")
  private String activeProfile;
    
  @Bean(name = "dataSource")
  public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUsername("sa");
        if ("prod".equals(activeProfile)) {
            driverManagerDataSource.setUrl("jdbc:h2:./db/prod/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE;SCHEMA=WEBAPP");
            driverManagerDataSource.setPassword("pr0ds3cr3t");
        } else if ("local-dev".equals(activeProfile)) {
            driverManagerDataSource.setUrl("jdbc:h2:./db/dev/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE;SCHEMA=WEBAPP");
            driverManagerDataSource.setPassword("d3vs3cr3t");
        }
        return driverManagerDataSource;
  }
    
  @Bean
  public ServletRegistrationBean h2servletRegistration(){
      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
      registrationBean.addUrlMappings("/h2-console/*");
      return registrationBean;
  }

}
