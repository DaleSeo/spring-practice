package seo.dale.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter({Controller.class, ControllerAdvice.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebMvcConfig.class)}
)
public class AppConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:org/springframework/security/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(60);
		return messageSource;
	}


	/*
	<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
   <property name="basenames">
       <list>
           <value>/WEB-INF/messages/spring_security_messages</value>
       </list>
   </property>
   <property name="defaultEncoding" value="UTF-8"/>
   <property name="cacheSeconds" value="5"/>
</bean>


	 */

}
