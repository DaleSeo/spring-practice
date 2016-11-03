package springframework.web.util;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.util.WebUtils;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public class WebUtilsTest {

	@Test
	public void testFindParameterValue() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("encoding", "1;2");
		String value = WebUtils.findParameterValue(request, "encoding");
		System.out.println(value);
	}

}
