package seo.dale.spring.core.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seo.dale.spring.core.security.model.Role;
import seo.dale.spring.user.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dale Seo
 */
@Service
public class UserService implements UserDetailsService {

	private static Map<String, User> table;

	{
		table = new HashMap<>();
		table.put("admin", new User("admin", "admin", Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER"), new Role("ROLE_GUEST"))));
		table.put("user", new User("user", "user", Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_GUEST"))));
		table.put("guest", new User("guest", "guest", Arrays.asList(new Role("ROLE_GUEST"))));
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = table.get(username);
		if (user == null) {
			throw new UsernameNotFoundException("Can't find the username");
		}
		return user;
	}

}
