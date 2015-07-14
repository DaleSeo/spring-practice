package org.dale.spring.rest.client;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {
	
	RestTemplate restTemplate;
	
	@Before
	public void before() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
