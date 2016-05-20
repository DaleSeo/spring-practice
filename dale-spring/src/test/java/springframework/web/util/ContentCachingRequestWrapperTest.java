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

		// One the inputStream is read,
		String content_1 = IOUtils.toString(inputStream);
		System.out.println("#1. Read inputStream : " + content_1);
		assertEquals(content, content_1);

		// Nothing to read from the inputStream.
		String content_2 = IOUtils.toString(inputStream);
		System.out.println("#2. Read inputStream : " + content_2);
		assertTrue(content_2.isEmpty());

		// Nothing to read from the inputStream
		String content_3 = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
		System.out.println("#3. getContentAsByteArray : " + content_3);
		assertEquals(content, content_3);
	}

}
