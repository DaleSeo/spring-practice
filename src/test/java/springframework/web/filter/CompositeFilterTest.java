package springframework.web.filter;

import org.junit.Test;
import org.springframework.mock.web.*;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CompositeFilterTest {



	@Test
	public void testCompositeFilter() throws ServletException, IOException {
		MockFilter filter1 = new MockFilter();
		Filter filter2 = new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
				System.out.println("filter2 is called.");
				request.setAttribute("filter2", true);
			}
		};

		CompositeFilter compositeFilter = new CompositeFilter();
		compositeFilter.setFilters(Arrays.asList(filter1, filter2));

		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		MockFilterChain filterChain = new MockFilterChain();

		compositeFilter.doFilter(request, response, filterChain);

		assertEquals(true, request.getAttribute("filter1"));
		assertEquals(true, request.getAttribute("filter2"));
	}


	public static class MockFilter implements Filter {

		public FilterConfig filterConfig;

		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			this.filterConfig = filterConfig;
		}

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
			System.out.println("filter1 is called.");
			request.setAttribute("filter1", true);
		}

		@Override
		public void destroy() {
			this.filterConfig = null;
		}

	}

}
