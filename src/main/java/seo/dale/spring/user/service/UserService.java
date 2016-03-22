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
public class UserService implements UserDetailsService {

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
    public User create(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = false, rollbackFor = DataNotFoundException.class)
    public User findById(Long id) throws DataNotFoundException {
        User found = repository.findOne(id);
        if (found == null) {
            throw new DataNotFoundException("No user found with id " + id);
        }
        return found;
    }

    @Transactional(rollbackFor = DataNotFoundException.class)
    public User removeById(Long id) throws DataNotFoundException {
        User found = findById(id);
        repository.delete(found);
        return found;
    }

    @Transactional(rollbackFor = DataNotFoundException.class)
    public User modify(User user) {
        User found = findById(user.getId());
        found.update(user);
        return found;
    }

}