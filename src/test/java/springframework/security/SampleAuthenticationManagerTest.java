package springframework.security;

import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SampleAuthenticationManagerTest {

    private AuthenticationManager authenticationManager = new SampleAuthenticationManager();

    @Test
    public void testSuccess() throws Exception {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken("love", "1234");
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            System.out.println("Authentication Failed: " + e.getMessage());
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testFailure() throws Exception {
        try {
            Authentication request = new UsernamePasswordAuthenticationToken("like", "9876");
            Authentication result = authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (AuthenticationException e) {
            System.out.println("Authentication Failed: " + e.getMessage());
        }
        System.out.println("Successfully authenticated. Security context contains: " +
            SecurityContextHolder.getContext().getAuthentication());
    }

}
