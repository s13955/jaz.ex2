package pja.jaz.servlet.filters;

import org.junit.Test;
import pja.jaz.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LogoutFilterTest {

    @Test
    public void doFilter_PassLogoutParameter_RemoveUserObjectFromSession() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getParameter("logout")).thenReturn("");
        when(request.getSession(false)).thenReturn(session);

        new LogoutFilter().doFilter(request, response, chain);

        verify(session).invalidate();
        verify(response).sendRedirect("/");
    }


}
