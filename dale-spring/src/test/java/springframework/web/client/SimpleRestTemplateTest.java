package springframework.web.client;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriTemplateHandler;
import seo.dale.spring.web.client.interceptor.LogInterceptor;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class SimpleRestTemplateTest {
	
	private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
	
	static RestTemplate restTemplate;
	
	@BeforeClass
	public static void beforeCalss() {
		restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Arrays.asList(new LogInterceptor()));

		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		System.out.println("### Message Converters ###");
		converters.forEach(System.out::println);

		ResponseErrorHandler errorHandelr = restTemplate.getErrorHandler();
		System.out.println("### Response Error Handler ###");
		System.out.println(errorHandelr);

		DefaultUriTemplateHandler uriTemplateHandler = (DefaultUriTemplateHandler) restTemplate.getUriTemplateHandler();
		uriTemplateHandler.setBaseUrl(BASE_URL);
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
	public void exchange() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("User-Agent", "curl/7.43.0");

		HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> res = restTemplate.exchange("/users/{id}", HttpMethod.GET, requestEntity, String.class, 1);
		assertTrue(res.getBody().length() > 0);
	}

}
