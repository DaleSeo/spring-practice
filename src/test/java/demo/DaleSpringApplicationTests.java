package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seo.dale.spring.DaleSpringApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaleSpringApplication.class)
@WebAppConfiguration
public class DaleSpringApplicationTests {

	@Test
	public void contextLoads() {
	}

}
