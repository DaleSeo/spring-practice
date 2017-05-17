package springframework.web.util;

import org.junit.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.Assert.assertEquals;

/**
 * UriComponents를 인코딩하지 않으면 세팅한 String을 그대로둔다. 
 */
public class UriComponentsTest {

	@Test
	public void extractComponent() {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("https://sub1.sub2.domain.co.kr:8080/path1/path2?q1=v1").build();
		assertEquals("https", uriComponents.getScheme());
		assertEquals("sub1.sub2.domain.co.kr", uriComponents.getHost());
		assertEquals(8080, uriComponents.getPort());
		assertEquals("/path1/path2", uriComponents.getPath());
	}


	@Test
	public void testWithoutUriTempalte() {
		System.out.println("# Before Encoding");
		String uriString = "http://www.host.com";
		UriComponents uriComponets = UriComponentsBuilder.fromUriString(uriString)
				.path("/aaa+bbb ccc&ddd")
				.queryParam("query", "aaa+bbb ccc&ddd", "aaa+bbb ccc&ddd")
				.build();
		System.out.println("## str : " + uriComponets.toUriString());
		System.out.println("## uri : " + uriComponets.toUri());
		
		System.out.println("# After Encoding");
		UriComponents encUriComponets = uriComponets.encode();
		System.out.println("## str : " + encUriComponets.toUriString());
		System.out.println("## uri : " + encUriComponets.toUri());
	}
	
	@Test
	public void testWithUriTempalte() {
		System.out.println("# Before Encoding");
		String uriString = "http://www.host.com";
		UriComponents uriComponets = UriComponentsBuilder.fromUriString(uriString)
				.path("/aaa+bbb ccc&ddd")
				.query("query1={query1}&query2={query2}")
				.buildAndExpand("aaa+bbb ccc&ddd", "aaa+bbb ccc&ddd");
		System.out.println("## str : " + uriComponets.toUriString());
		System.out.println("## uri : " + uriComponets.toUri());
		
		System.out.println("# After Encoding");
		UriComponents encUriComponets = uriComponets.encode();
		System.out.println("## str : " + encUriComponets.toUriString());
		System.out.println("## uri : " + encUriComponets.toUri());
	}
	
	@Test
	public void fromUriStringWithEncoding() {
		String uriString = "/hotels/42?filter={value}";
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(uriString).buildAndExpand("hot&cold");
		URI uri = uriComponents.encode().toUri();
		System.out.println("# uri : " + uri);
		assertEquals("/hotels/42?filter=hot%26cold", uri.toString());
	}

	@Test
	public void fromUriStringWithoutEncoding() {
		String uriString = "http://example.com/hotels/{hotel}/bookings/{booking}";
		UriComponents uriComponents = UriComponentsBuilder.fromUriString(uriString).build();
		URI uri = uriComponents.expand("42", "hot&cold").toUri();
		System.out.println("# uri : " + uri);
		assertEquals("http://example.com/hotels/42/bookings/21", uri.toString());
	}
	
	@Test
	public void newInstance() {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("example.com")
				.path("/hotels/{hotel}/bookings/{booking}")
				.build();
		URI uri = uriComponents.expand("42", "21").encode().toUri();
		System.out.println("# uri : " + uri);
		assertEquals("http://example.com/hotels/42/bookings/21", uri.toString());
	}

}
