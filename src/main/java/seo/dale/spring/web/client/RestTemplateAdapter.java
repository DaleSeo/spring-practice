package seo.dale.spring.web.client;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import seo.dale.spring.web.client.interceptor.LogInterceptor;
import seo.dale.spring.web.client.url.CustomUriTemplateHandler;

import java.util.Arrays;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public class RestTemplateAdapter {

	private static final String BASE_URL = "http://jsonplaceholder.typicode.com";

	private RestTemplate restTemplate;

	public RestTemplateAdapter() {
		this.restTemplate = new RestTemplate();

		restTemplate = new RestTemplate();

		restTemplate.setInterceptors(Arrays.asList(new LogInterceptor()));

		restTemplate.setUriTemplateHandler(new CustomUriTemplateHandler());

		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(30000);
		simpleClientHttpRequestFactory.setReadTimeout(2000);
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(simpleClientHttpRequestFactory));
	}

	public <T> ResponseEntity<T> exchange(RequestEntity<?> requestEntity, Class<T> responseType) {
		return restTemplate.exchange(requestEntity, responseType);
	}

	public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) {
		return restTemplate.getForObject(url, responseType, urlVariables);
	}

}
