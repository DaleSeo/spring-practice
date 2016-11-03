package springframework.web.util;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletOutputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/util/ContentCachingResponseWrapper.html
 */
public class ContentCachingResponseWrapperTest {

	/**
	 * Content is cached only after the response is wrapped.
	 */
	@Test
	public void testGetContentAsByteArray() throws Exception {
		String content = "{\n" +
				"  \"id\": 1,\n" +
				"  \"name\": \"Leanne Graham\",\n" +
				"  \"username\": \"Bret\",\n" +
				"  \"email\": \"Sincere@april.biz\"\n" +
				"}";

		MockHttpServletResponse response = new MockHttpServletResponse();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(response);

		// raw response
		ServletOutputStream outputStream = response.getOutputStream();
		IOUtils.write(content, outputStream);

		String contentWrittenViaRawResponse = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
		System.out.println("# contentWrittenViaRawResponse : " + contentWrittenViaRawResponse);
		assertTrue(contentWrittenViaRawResponse.isEmpty());

		// response wrapper
		ServletOutputStream outputStreamFromWrapper = wrapper.getOutputStream();
		IOUtils.write(content, outputStreamFromWrapper);

		String contentWrittenViaResponseWrapper = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
		System.out.println("# contentWrittenViaResponseWrapper : " + contentWrittenViaResponseWrapper);
		assertEquals(content, contentWrittenViaResponseWrapper);
	}

}
