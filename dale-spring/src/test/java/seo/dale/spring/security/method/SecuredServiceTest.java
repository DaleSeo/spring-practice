package seo.dale.spring.security.method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecuredServiceConfig.class)
public class SecuredServiceTest {

    @Autowired
    private SecuredService securedService;

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testWithoutCredential() throws Exception {
        securedService.doSecurely();
    }

    @Test(expected = BadCredentialsException.class)
    public void testWithBadCredential() throws Exception {
        setupUser(new UsernamePasswordAuthenticationToken("user", "1234"));
        securedService.doSecurely();
    }

    @Test(expected = AccessDeniedException.class)
    public void testWithoutAuthority() throws Exception {
        setupUser(new UsernamePasswordAuthenticationToken("user", "user", null));
        securedService.doSecurely();
    }

    @Test(expected = AccessDeniedException.class)
    public void testWithBadAuthority() throws Exception {
        setupUser(new UsernamePasswordAuthenticationToken("user", "pass", Arrays.asList(new SimpleGrantedAuthority("ROLE_GUEST"))));
        securedService.doSecurely();
    }

    @Test
    public void testPassWithRoleUser() throws Exception {
        setupUser(new UsernamePasswordAuthenticationToken("user", "pass"));
        securedService.doSecurely();
    }

    @Test
    public void testPassWithRoleAdmin() throws Exception {
        setupUser(new UsernamePasswordAuthenticationToken("guest", "pass", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        securedService.doSecurely();
    }

    private void setupUser( UsernamePasswordAuthenticationToken authenticationToken) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticationToken);
    }

}