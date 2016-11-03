package springframework.web.util;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.util.UrlPathHelper;

import static org.junit.Assert.assertEquals;

public class UrlPathHelperTest2 {

	private static final String CONTEXT_PATH = "/context";
	private static final String SERVLET_PATH = "/servlet";
	private static final String REMAINING_PATH = "/path.do?no=1";

	private static final String REQUEST_URI = "/context/servlet/path.do?no=1";

	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	private MockHttpServletRequest defaultMappingRequest;
	private MockHttpServletRequest pathMappingRequest;
	private MockHttpServletRequest extensionMappingRequest;

	@Before
	public void setUp() {
		// path mapping = /servlet/*
		pathMappingRequest = new MockHttpServletRequest();
		pathMappingRequest.setRequestURI(REQUEST_URI);
		pathMappingRequest.setContextPath("/context");
		pathMappingRequest.setServletPath("/servlet");
		pathMappingRequest.setPathInfo("/path.do");

		// default mapping = /
		defaultMappingRequest = new MockHttpServletRequest();
		defaultMappingRequest.setRequestURI(REQUEST_URI);
		defaultMappingRequest.setContextPath("/context");
		defaultMappingRequest.setServletPath("/servlet/path.do?no=1");
		defaultMappingRequest.setPathInfo(null);

		// extension mapping = *.do
		extensionMappingRequest = new MockHttpServletRequest();
		extensionMappingRequest.setRequestURI(REQUEST_URI);
		extensionMappingRequest.setContextPath("/context");
		extensionMappingRequest.setServletPath("/servlet/path.do?no=1");
		extensionMappingRequest.setPathInfo(null);
	}

	/**
	 * Remove the context path from the request uri
	 */
	@Test
	public void getPathWithinApplication() {
		String pathWithinApplication = urlPathHelper.getPathWithinApplication(pathMappingRequest);
		System.out.println("# pathWithinApplication : " + pathWithinApplication);
		assertEquals(SERVLET_PATH + REMAINING_PATH, pathWithinApplication);

		String pathWithinApplication2 = urlPathHelper.getPathWithinApplication(defaultMappingRequest);
		System.out.println("# pathWithinApplication2 : " + pathWithinApplication2);
		assertEquals(SERVLET_PATH + REMAINING_PATH, pathWithinApplication2);

		String pathWithinApplication3 = urlPathHelper.getPathWithinApplication(extensionMappingRequest);
		System.out.println("# pathWithinApplication3 : " + pathWithinApplication3);
		assertEquals(SERVLET_PATH + REMAINING_PATH, pathWithinApplication3);
	}

	/**
	 * Remove the servlet path from the request uri
	 * (The servlet path is the same as the request uri unless the servlet uses path mapping.)
	 */
	@Test
	public void getPathWithinServletMapping() {
		String pathWithinServletMapping = urlPathHelper.getPathWithinServletMapping(pathMappingRequest);
		System.out.println("# pathWithinServletMapping : " + pathWithinServletMapping);
		assertEquals(REMAINING_PATH, pathWithinServletMapping);

		String pathWithinServletMapping2 = urlPathHelper.getPathWithinServletMapping(defaultMappingRequest);
		System.out.println("# pathWithinServletMapping2 : " + pathWithinServletMapping2);
		assertEquals("", pathWithinServletMapping2);

		String pathWithinServletMapping3 = urlPathHelper.getPathWithinServletMapping(extensionMappingRequest);
		System.out.println("# pathWithinServletMapping3 : " + pathWithinServletMapping3);
		assertEquals("", pathWithinServletMapping3);
	}

	/**
	 * The lookup path is the
	 * (The servlet path is the same as the request uri unless the servlet uses path mapping.)
	 */
	@Test
	public void getLookupPathForRequest() {
		String lookupPathForRequest1 = urlPathHelper.getLookupPathForRequest(pathMappingRequest);
		System.out.println("# lookupPathForRequest : " + lookupPathForRequest1);
		assertEquals(REMAINING_PATH, lookupPathForRequest1);

		String lookupPathForRequest2 = urlPathHelper.getLookupPathForRequest(defaultMappingRequest);
		System.out.println("# lookupPathForRequest2 : " + lookupPathForRequest2);
		assertEquals(SERVLET_PATH + REMAINING_PATH, lookupPathForRequest2);

		String lookupPathForRequest3 = urlPathHelper.getLookupPathForRequest(extensionMappingRequest);
		System.out.println("# lookupPathForRequest3 : " + lookupPathForRequest3);
		assertEquals(SERVLET_PATH + REMAINING_PATH, lookupPathForRequest3);

		urlPathHelper.setAlwaysUseFullPath(true);

		String lookupPathUsingFullPath = urlPathHelper.getLookupPathForRequest(pathMappingRequest);
		assertEquals(SERVLET_PATH + REMAINING_PATH, lookupPathUsingFullPath);
		System.out.println("# lookupPathUsingFullPath : " + lookupPathUsingFullPath);
	}

}
