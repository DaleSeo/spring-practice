package seo.dale.spring.rest.client;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

public class BypassServiceImplTest {

	private static final String DOMAIN_NAME = "http://ec-store.sungsu.skplanet.com";
	
	BypassServiceImpl srvc;
	
	@Before
	public void before() {
		srvc = new BypassServiceImpl();
	}
	
	@Test
	public void test() {
		String url = DOMAIN_NAME + "/example/sample/detail/3";
		HttpMethod method = HttpMethod.GET;
		
		String resposneBody = srvc.bypass(url, method, null);
		System.out.println("# responseBody : " + resposneBody);
	}

}
