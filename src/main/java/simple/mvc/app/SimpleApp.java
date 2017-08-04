package simple.mvc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import simple.mvc.config.AppConfig;
import simple.mvc.config.MvcConfig;
import simple.mvc.config.SecurityConfig;

@SpringBootApplication
@Import({AppConfig.class, MvcConfig.class, SecurityConfig.class})
public class SimpleApp {

  private static Logger log = LoggerFactory.getLogger(SimpleApp.class);
  
  public static void main(String[] args) {
      SpringApplication springApplication = new SpringApplication(SimpleApp.class);
      ApplicationContext context = springApplication.run(args);
      log.info("Context : " + context.getId());
  }
}
