package seo.dale.spring.core.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author Dale Seo
 */
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		UserDetails user = loadUser(username, password);

		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	private UserDetails loadUser(String username, String password) {
		UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(username);
			if (!password.equals(userDetails.getPassword())) {
				throw new BadCredentialsException("The password doesn't match.");
			}
		} catch (AuthenticationException e) {
			logger.info(e.toString());
			throw e;
		}
		return userDetails;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
