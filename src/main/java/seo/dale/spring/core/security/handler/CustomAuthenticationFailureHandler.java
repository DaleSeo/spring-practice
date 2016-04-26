package seo.dale.spring.core.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

	private static final String DEFAULT_FAILURE_URL = "/login?error";

	public CustomAuthenticationFailureHandler() {
		this(DEFAULT_FAILURE_URL);
	}

	public CustomAuthenticationFailureHandler(String failureUrl) {
		super(failureUrl);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		logger.warn("Authentication Failure");
		super.onAuthenticationFailure(request, response, exception);
	}
}
