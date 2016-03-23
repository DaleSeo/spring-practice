package seo.dale.spring.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seo.dale.spring.core.exception.DataNotFoundException;
import seo.dale.spring.user.model.User;
import seo.dale.spring.user.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User found = repository.findByUsername(username);
		if (found == null) {
			throw new UsernameNotFoundException("Can't find the username");
		}
		return found;
	}

    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = false, rollbackFor = DataNotFoundException.class)
    @Override
    public User findById(Long id) throws DataNotFoundException {
        User found = repository.findOne(id);
        if (found == null) {
            throw new DataNotFoundException("No user found with id " + id);
        }
        return found;
    }

    @Transactional(rollbackFor = DataNotFoundException.class)
    @Override
    public User removeById(Long id) throws DataNotFoundException {
        User found = findById(id);
        repository.delete(found);
        return found;
    }

    @Transactional(rollbackFor = DataNotFoundException.class)
    @Override
    public User modify(User user) throws DataNotFoundException {
        User found = findById(user.getId());
        found.update(user);
        return found;
    }

	@Override
	public void increaseLoginAttemptCount(String username) throws UsernameNotFoundException {

	}

	@Override
	public void resetLoginAttemptCount(String username) throws UsernameNotFoundException {

	}

}