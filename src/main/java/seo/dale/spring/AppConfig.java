package seo.dale.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter({Controller.class, ControllerAdvice.class}),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = EnableWebMvc.class)}
)
public class AppConfig {

}
