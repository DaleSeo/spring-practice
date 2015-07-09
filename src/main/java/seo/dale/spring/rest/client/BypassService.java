package seo.dale.spring.rest.client;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BypassService {

	private static RestTemplate restTemplate;
	
	static {
		restTemplate = new RestTemplate();
	}
	
	public String bypass(String url, HttpMethod method, String requestBody) {    
		URI uri = BypassService.buildUri(url);
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestBody);
		ResponseEntity<String> responseEntity = restTemplate.exchange(uri, method, requestEntity, String.class);
		String responseBody = responseEntity.getBody();
		return responseBody;
	}
	
	private static URI buildUri(String httpUrl) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(httpUrl).build();
		URI uri = uriComponents.toUri();
		return uri;
	}
	
}
