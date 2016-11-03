package springframework.web.util;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletInputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/util/ContentCachingRequestWrapper.html
 */
public class ContentCachingRequestWrapperTest {

	/**
	 * Make sure that getInputStream() gets called before getContentAsByteArray() is called.
	 */
	@Test
	public void testGetContentAsByteArray() throws Exception {
		String content = "{\n" +
				"  \"id\": 1,\n" +
				"  \"name\": \"Leanne Graham\",\n" +
				"  \"username\": \"Bret\",\n" +
				"  \"email\": \"Sincere@april.biz\"\n" +
				"}";

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContent(content.getBytes());
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
		ServletInputStream inputStream = wrapper.getInputStream();

		// Read the half
		byte[] bytes = new byte[40];
		inputStream.read(bytes);
		String content_1 = new String(bytes, wrapper.getCharacterEncoding());
		System.out.println("#1. Read some inputStream : " + content_1);
		assertTrue(content_1.contains("\"id\": 1"));

		// Read the remaining
		String content_2 = IOUtils.toString(inputStream);
		System.out.println("#2. Read the ramming inputStream : " + content_2);
		assertTrue(content_2.contains("\"email\": \"Sincere@april.biz\""));

		// Nothing to read from the inputStream
		String content_3 = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
		System.out.println("#3. getContentAsByteArray : " + content_3);
		assertEquals(content, content_3);
	}

	@Test
	public void testGetContentAsByteArray2() throws Exception {
		String content = "\"category=app+game%2Bmusic&productId=P12345&key=val&key=%EA%B0%92\"";

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("POST");
		request.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		request.setContent(content.getBytes());
		request.setCharacterEncoding(StandardCharsets.UTF_8.name());

		ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
		ServletInputStream inputStream = wrapper.getInputStream();

		wrapper.getParameter("category");

		// Read the half
		byte[] bytes = new byte[40];
		inputStream.read(bytes);
		String content_1 = new String(bytes, wrapper.getCharacterEncoding());
		System.out.println("#1. Read some inputStream : " + content_1);
		// assertTrue(content_1.contains("\"id\": 1"));

		// Read the remaining
		String content_2 = IOUtils.toString(inputStream);
		System.out.println("#2. Read the ramming inputStream : " + content_2);
		// assertTrue(content_2.contains("\"email\": \"Sincere@april.biz\""));

		// Nothing to read from the inputStream
		String content_3 = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
		System.out.println("#3. getContentAsByteArray : " + content_3);
		// assertEquals(content, content_3);
	}

}
