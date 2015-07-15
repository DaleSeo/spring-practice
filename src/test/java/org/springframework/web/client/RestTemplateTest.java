package org.springframework.web.client;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;

public class RestTemplateTest {
	
	private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
	
	static RestTemplate restTemplate;
	
	@BeforeClass
	public static void beforeCalss() {
		restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		System.out.println("### Message Converters ###");
		for(HttpMessageConverter<?> each : converters) {
			System.out.println(each);
		}
		
		ResponseErrorHandler errorHandelr = restTemplate.getErrorHandler();
		System.out.println("### Response Error Handler ###");
		System.out.println(errorHandelr);
	}
	
	@Test
	public void getForObject() {
		String res = restTemplate.getForObject(BASE_URL + "/users", String.class, 1);
		System.out.println("# getForObject : " + res);
	}
	
	@Test
	public void getForObjectUsingUriTemplate() {
		User res = restTemplate.getForObject(BASE_URL + "/users/{id}", User.class, 1);
		System.out.println("# getForObject : " + res);
	}

}
