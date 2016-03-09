package seo.dale.spring.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import seo.dale.spring.core.security.model.User;
import seo.dale.spring.core.security.service.UserService;

/**
 * @author Dale Seo
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		User user = loadUser(username, password);

		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	private User loadUser(String username, String password) {
		User user;
		try {
			user = userService.loadUserByUsername(username);
			if (!password.equals(user.getPassword())) {
				throw new BadCredentialsException("The password doesn't match.");
			}
		} catch (AuthenticationException e) {
			logger.info(e.toString());
			throw e;
		}
		return user;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
