package seo.dale.spring.core.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import seo.dale.spring.core.exception.DataNotFoundException;
import seo.dale.spring.user.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return service.findByUsername(username);
        } catch (DataNotFoundException ex) {
            throw new UsernameNotFoundException("Can't find the username");
        }
    }

}
