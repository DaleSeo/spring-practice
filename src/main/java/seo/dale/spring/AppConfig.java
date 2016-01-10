package seo.dale.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class AppConfig {
}
