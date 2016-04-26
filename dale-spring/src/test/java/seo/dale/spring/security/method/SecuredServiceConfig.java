package seo.dale.spring.security.method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecuredServiceConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("guest").password("pass").roles("GUEST").and()
                .withUser("user").password("pass").roles("USER").and()
                .withUser("admin").password("pass").roles("ADMIN", "USER");
    }

    @Bean
    public SecuredService securedService() {
        return new SecuredService();
    }

}
