package pja.jaz.servlet.filters;


import org.junit.Before;
import org.junit.Test;
import pja.jaz.model.Role;
import pja.jaz.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class AdminAccessFilterTest {

    private User userWithNormalRole;
    private User userWithAdminRole;

    @Before
    public void setup() {
        userWithNormalRole = new User();
        userWithNormalRole.setUsername("test");
        userWithNormalRole.setPassword("test");
        userWithNormalRole.setRole(Role.USER);

        userWithAdminRole = new User();
        userWithAdminRole.setUsername("test");
        userWithAdminRole.setPassword("test");
        userWithAdminRole.setRole(Role.ADMIN);
    }

    @Test
    public void doFilter_AnonymousUser_RedirectToError403() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getRequestURI()).thenReturn("/admin/premium");

        AdminAccessFilter filter = new AdminAccessFilter();
        filter.doFilter(request, response, chain);

        verify(response).sendRedirect("/error403");
    }

    @Test
    public void doFilter_NormalUser_RedirectToError403() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(session.getAttribute("user")).thenReturn(userWithNormalRole);
        when(request.getRequestURI()).thenReturn("/admin/premium");
        when(request.getSession(false)).thenReturn(session);

        AdminAccessFilter filter = new AdminAccessFilter();
        filter.doFilter(request, response, chain);

        verify(response).sendRedirect("/error403");
    }

    @Test
    public void doFilter_AdminUser_Pass() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        FilterChain chain = mock(FilterChain.class);

        when(session.getAttribute("user")).thenReturn(userWithAdminRole);
        when(request.getRequestURI()).thenReturn("/admin/premium");
        when(request.getSession(false)).thenReturn(session);

        AdminAccessFilter filter = new AdminAccessFilter();
        filter.doFilter(request, response, chain);
    }

}
