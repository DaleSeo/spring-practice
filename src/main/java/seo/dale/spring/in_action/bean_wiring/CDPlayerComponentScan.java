package seo.dale.spring.in_action.bean_wiring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CDPlayer.class)
public class CDPlayerComponentScan {
}
