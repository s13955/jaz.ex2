package pja.jaz.servlet.filters;

import pja.jaz.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }

    protected boolean isApplicationPath(String url) {
        return null != url && (url.startsWith("/images") || url.startsWith("/css"));
    }

    protected User getSessionUser(HttpSession session) {
        if (null != session && null != session.getAttribute("user")) {
            return (User) session.getAttribute("user");
        }

        return null;
    }

    protected boolean isSignedInUser(HttpSession session) {
        return null != getSessionUser(session);
    }

    protected void redirectToError403(HttpServletResponse response) throws IOException {
        response.sendRedirect("/error403");
    }

}
