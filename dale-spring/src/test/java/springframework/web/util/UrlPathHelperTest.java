package springframework.web.util;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.util.UrlPathHelper;

import static org.junit.Assert.assertEquals;

public class UrlPathHelperTest {
	
	UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	@Test
	public void getPathWithinApplicationWithNoContextPath() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/");
		request.setRequestURI("/sac/bypass");
		request.setQueryString("no=1");
		
		String pathWithinApp = urlPathHelper.getPathWithinApplication(request);
		System.out.println("# pathWithinApp : " + pathWithinApp);
		assertEquals("/sac/bypass", pathWithinApp);
	}
	
	@Test
	public void getPathWithinApplicationWithContextPath() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/sac");
		request.setRequestURI("/sac/bypass");
		request.setQueryString("no=1");
		
		String pathWithinApp = urlPathHelper.getPathWithinApplication(request);
		System.out.println("# pathWithinApp : " + pathWithinApp);
		assertEquals("/bypass", pathWithinApp);
	}
	
	@Test
	public void getPathWithinServletMapping() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("/");
		request.setServletPath("/sac");
		request.setRequestURI("/sac/bypass");
		request.setQueryString("no=1");
		
		String pathWithinServletMapping = urlPathHelper.getPathWithinServletMapping(request);
		System.out.println("# pathWithinApp : " + pathWithinServletMapping);
		assertEquals("/bypass", pathWithinServletMapping);
	}

	@Test
	public void getLookupPathForRequest() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContextPath("");
		request.setServletPath("/sac");
		request.setPathInfo("/bypass");
		request.setRequestURI("/sac/bypass");
		request.setQueryString("no=1");
		String lookupPath = urlPathHelper.getLookupPathForRequest(request);
		System.out.println("# lookupPath : " + lookupPath);
	}

}
