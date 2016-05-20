package springframework.web.filter;

import com.google.common.net.HttpHeaders;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Only read payload is cached and logged.
 */
public class AbstractRequestLoggingFilterTest {

	private AbstractRequestLoggingFilter filter;

	private MockFilterChain filterChain;

	@Before
	public void setUp() throws Exception {
		filter = new AbstractRequestLoggingFilter() {
			@Override
			protected void beforeRequest(HttpServletRequest request, String message) {
				logger.info(message);
			}

			@Override
			protected void afterRequest(HttpServletRequest request, String message) {
				logger.info(message);
			}
		};

		filter.setIncludePayload(true);
		filter.setIncludeQueryString(true);
		filter.setMaxPayloadLength(100);
		filter.setBeforeMessagePrefix("REQUEST_LOG_BEFORE : ");
		filter.setAfterMessagePrefix("REQUEST_LOG_AFTER : ");

		filterChain = new MockFilterChain(new HttpServlet() {
		}, new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
				IOUtils.toString(request.getInputStream()); // consume input stream
			}
		});
	}

	@Test
	public void test() throws Exception {
		String content = "{\n" +
				"  \"id\": 1,\n" +
				"  \"name\": \"Leanne Graham\",\n" +
				"  \"username\": \"Bret\",\n" +
				"  \"email\": \"Sincere@april.biz\"\n" +
				"}";

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/sac");
		request.setRequestURI("/example/sample/detail");
		request.setQueryString("no=1");

		request.setCharacterEncoding(StandardCharsets.UTF_8.name());
		request.setContent(content.getBytes());
		request.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());

		request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8.toString());

		filter.doFilter(request, new MockHttpServletResponse(), filterChain);
	}

}
