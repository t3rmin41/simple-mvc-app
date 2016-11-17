package simple.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"simple.mvc.service.impl", "simple.mvc.repository.impl"})
public class AppConfig {

}
