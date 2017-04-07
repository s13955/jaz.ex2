package pja.jaz.servlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter(urlPatterns = "/*")
public class GuestAccessFilter extends BaseFilter {

    private ArrayList<String> allowedUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedUrls.add("/");
        allowedUrls.add("/debug");
        allowedUrls.add("/login");
        allowedUrls.add("/register");
        allowedUrls.add("/error403");
        allowedUrls.add("/error403.jsp");
        allowedUrls.add("/error404");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        String url = httpServletRequest.getRequestURI();

        if (!isApplicationPath(url) && !isSignedInUser(session) && !allowedUrls.contains(url)) {
            redirectToError403(httpServletResponse);
            return;
        }

        chain.doFilter(request, response);
    }
}
