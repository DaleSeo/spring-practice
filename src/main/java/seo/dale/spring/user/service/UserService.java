package seo.dale.spring.user.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import seo.dale.spring.core.exception.DataNotFoundException;
import seo.dale.spring.user.model.User;

import java.util.List;

/**
 * @author 서대영/Store기술개발팀/SKP
 */
public interface UserService {

	List<User> findAll();

	User create(User user);

	User findById(Long id) throws DataNotFoundException;

	User removeById(Long id) throws DataNotFoundException;

	User modify(User user) throws DataNotFoundException;

	void increaseLoginAttemptCount(String username) throws UsernameNotFoundException;

	void resetLoginAttemptCount(String username) throws UsernameNotFoundException;

}
