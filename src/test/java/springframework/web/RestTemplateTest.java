package springframework.web;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;

import java.util.List;

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

		DefaultUriTemplateHandler uriTemplateHandler = new DefaultUriTemplateHandler();
		uriTemplateHandler.setBaseUrl(BASE_URL);
		restTemplate.setUriTemplateHandler(uriTemplateHandler);
		System.out.println("### Uri Template Handler ###");
		System.out.println(uriTemplateHandler);

		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(30000);
		simpleClientHttpRequestFactory.setReadTimeout(2000);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory));
		System.out.println("### ClientHttpRequestFactory ###");
		System.out.println(simpleClientHttpRequestFactory);
	}
	
	@Test
	public void getForObject() {
		String res = restTemplate.getForObject("/users", String.class, 1);
		System.out.println("# getForObject : " + res);
	}
	
	@Test
	public void getForObjectUsingUriTemplate() {
		User res = restTemplate.getForObject("/users/{id}", User.class, 1);
		System.out.println("# getForObject : " + res);
	}

}
