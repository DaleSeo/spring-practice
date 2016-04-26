package seo.dale.spring;

/**
 * @author 서대영/Store기술개발팀/SKP
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seo.dale.spring.core.security.model.Role;
import seo.dale.spring.user.model.User;
import seo.dale.spring.user.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
@Service
public class DataInitializer {

	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initialize() {
		List<User> userList = new ArrayList<>();
		userList.add(new User("dale0713", "yeji0818", Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER"), new Role("ROLE_GUEST"))));
		userList.add(new User("kimrla38", "kate2637", Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_GUEST"))));
		userList.add(new User("yeji818", "dale0713", Arrays.asList(new Role("ROLE_GUEST"))));

		userList.forEach(repository::save);
	}

}
