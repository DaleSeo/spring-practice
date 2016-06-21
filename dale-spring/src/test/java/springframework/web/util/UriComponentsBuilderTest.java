package springframework.web.util;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * @author 서대영(DAEYOUNG SEO)/Onestore/SKP
 */
public class UriComponentsBuilderTest {

	@Test
	public void testBuildTrue() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.scheme("http").host("www.test.com").port(80).path("/abc");
		builder.query("q=%EC%9A%B0%EB%A6%AC");

		// UriComponentsBuilder에서 UriComponents를 build할 때 true 옵션을 주면,
		// UriComponents에서 아무리 encode() 메서드를 호출해도 URL 인코딩을 하지 않으며,
		// UriComponents에서 toUri() 메서드를 호출했을 때, 추가 URL 인코딩을 하지 않는다.
		UriComponents components = builder.build(true);
		components = components.encode();

		URI uri = components.toUri();
		assertEquals("http://www.test.com:80/abc?q=%EC%9A%B0%EB%A6%AC", uri.toString());
		assertEquals("q=우리", uri.getQuery());
	}

	@Test
	public void testBuildFalse() throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		builder.scheme("http").host("www.test.com").port(80).path("/abc");
		builder.query("q=%EC%9A%B0%EB%A6%AC");

		// UriComponentsBuilder에서 UriComponents를 build할 때 false 옵션을 주면,
		// UriComponents에서 encode 메서드를 호출했을 때, 인코딩이 안 된 값이 있으면 인코딩을 하고
		// UriComponents에서 toUri() 메서드를 호출했을 때, 추가 URL 인코딩을 해서 마치 2번 인코등 한 것처럼 되므로 주의한다.
		UriComponents components = builder.build();
		components= components.encode();

		URI uri = components.toUri();
		System.out.println(uri);
		assertEquals("http://www.test.com:80/abc?q=%25EC%259A%25B0%25EB%25A6%25AC", uri.toString());
		assertEquals("q=%EC%9A%B0%EB%A6%AC", uri.getQuery());
	}
}
