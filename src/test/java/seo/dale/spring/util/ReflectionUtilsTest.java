package seo.dale.spring.util;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

public class ReflectionUtilsTest {

	@Test
	public void test() {
		ReflectionUtils.doWithLocalFields(Sample.class, System.out::println);
	}

	static class Sample {

		long id;
		String name;

	}

}
