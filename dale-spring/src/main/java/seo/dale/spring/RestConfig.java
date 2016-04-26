package seo.dale.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import seo.dale.spring.rest.support.LoggingRequestInterceptor;

import java.util.Arrays;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTempalte() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Arrays.asList(new LoggingRequestInterceptor()));
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		return restTemplate;
	}

}
