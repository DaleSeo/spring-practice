package seo.dale.spring.util;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

public class ReflectionUtilsTest {

	@Test
	public void testDoWithLocalFields() {
		ReflectionUtils.doWithLocalFields(Sample.class, System.out::println);
	}

	@Test
	public void testDoWithLocalMethods() {
		Sample sample = new Sample();

		ReflectionUtils.doWithLocalMethods(Sample.class, method -> {
			if (method.getName().startsWith("setId")) {
				ReflectionUtils.invokeMethod(method, sample, 1);
			}
			if (method.getName().startsWith("setName")) {
				ReflectionUtils.invokeMethod(method, sample, "ABC");
			}
		});

		ReflectionUtils.doWithLocalMethods(Sample.class, method -> {
			if (method.getName().startsWith("get")) {
				Object value = ReflectionUtils.invokeMethod(method, sample);
				System.out.println(value);
			}
		});
	}

	public static class Sample {

		private long id;
		private String name;

		public Sample() {
		}

		public Sample(long id, String name) {
			this.id = id;
			this.name = name;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
