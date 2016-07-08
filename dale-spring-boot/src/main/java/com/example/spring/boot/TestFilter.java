package com.example.spring.boot;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.IOException;

public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		BufferedReader reader = servletRequest.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	@Override
	public void destroy() {
	}

}
