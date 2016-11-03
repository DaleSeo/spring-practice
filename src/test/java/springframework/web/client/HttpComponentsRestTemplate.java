package springframework.web.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HttpComponentsRestTemplate.Config.class)
public class HttpComponentsRestTemplate {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("User-Agent", "curl/7.43.0");

		HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> res = restTemplate.exchange("http://jsonplaceholder.typicode.com/users/{id}", HttpMethod.GET, requestEntity, String.class, 1);
		assertTrue(res.getBody().length() > 0);
	}

	@Configuration
	static class Config {

		@Bean
		public RestTemplate restTemplate() {
			RestTemplate restTemplate = new RestTemplate();
			return restTemplate;
		}

	}

}
