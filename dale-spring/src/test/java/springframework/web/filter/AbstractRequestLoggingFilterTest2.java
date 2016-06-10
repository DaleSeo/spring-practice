package springframework.web.filter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AbstractRequestLoggingFilterTest2 {

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		AbstractRequestLoggingFilter filter = new AbstractRequestLoggingFilter() {
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

		mockMvc = MockMvcBuilders.standaloneSetup(new ReverseController())
				.addFilter(filter)
				.build();
	}

	@Test
	public void test() throws Exception {
		MvcResult result = mockMvc.perform(
				post("/reverse")
						.content("ABCDE")
						.accept(MediaType.parseMediaType("text/plain;charset=UTF-8"))
						.contentType(MediaType.parseMediaType("text/plain;charset=UTF-8"))
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("EDCBA", content);
	}


	private class ReverseController {

		@RequestMapping("/reverse")
		public @ResponseBody String test(@RequestBody String input) {
			return new StringBuilder(input).reverse().toString();
		}

	}

}
