package springframework.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;

public class SampleAuthenticationManager implements AuthenticationManager {

    private static final List<GrantedAuthority> AUTHORITIES = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if ("love".equals(authentication.getName()) && "1234".equals(authentication.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

}
