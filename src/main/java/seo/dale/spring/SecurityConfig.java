package seo.dale.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import seo.dale.spring.core.security.CustomAuthenticationProvider;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
			    .authenticationProvider(customAuthenticationProvider)
			    .inMemoryAuthentication()
				    .withUser("user").password("password").roles("USER").and()
				    .withUser("admin").password("password").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
	                .antMatchers("/resources/**", "/").permitAll()
	                .antMatchers("/admin/**").hasRole("ADMIN")
	                .antMatchers("/toDo", "/toDo/**").access("hasRole('USER') or hasRole('DBA')")
	                .anyRequest().authenticated()
	                .and()
                .formLogin()
		            .loginPage("/login")
		            .permitAll()
		            .and()
                .httpBasic();
    }

}
