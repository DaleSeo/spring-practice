package seo.dale.spring.in_action.bean_wiring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CDPlayerJavaConfig {

    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }

    @Bean
    public CompactDisc sgtPeppers() {
        return new SgtPeppers();
    }

}
