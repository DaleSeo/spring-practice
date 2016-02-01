package seo.dale.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter({Controller.class, ControllerAdvice.class})})
public class AppConfig {
}
