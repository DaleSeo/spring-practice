package springframework.web.filter;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class CharacterEncodingFilterTest {

    private CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);

    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    MockFilterChain filterChain = new MockFilterChain();

    @Test
    public void test() throws Exception {
        System.out.println("Request Character Encoding = " + request.getCharacterEncoding());
        System.out.println("Response Character Encoding = " + response.getCharacterEncoding());

        filter.doFilter(request, response, filterChain);

        System.out.println("Request Character Encoding = " + request.getCharacterEncoding());
        System.out.println("Response Character Encoding = " + response.getCharacterEncoding());

        assertEquals("UTF-8", request.getCharacterEncoding());
        assertEquals("UTF-8", response.getCharacterEncoding());
    }

}
