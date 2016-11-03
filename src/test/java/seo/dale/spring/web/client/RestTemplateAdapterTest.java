package seo.dale.spring.web.client;

import org.junit.Test;
import springframework.web.User;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public class RestTemplateAdapterTest {

	private RestTemplateAdapter adapter = new RestTemplateAdapter();

	@Test
	public void testGetForObject() throws Exception {
		User res = adapter.getForObject("/users/{id}", User.class, 1);
		System.out.println("# getForObject : " + res);
	}

}