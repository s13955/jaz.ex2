package pja.jaz.servlet.filters;

import pja.jaz.model.Role;
import pja.jaz.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = { "/admin/*" })
public class AdminAccessFilter extends BaseFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        User user = getSessionUser(session);

        if (null == user || !(Role.ADMIN == user.getRole())) {
            redirectToError403(httpServletResponse);
            return;
        }

        chain.doFilter(request, response);
    }

}
