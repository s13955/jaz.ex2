package pja.jaz.servlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class SignedInUserDenyAccessFilter extends BaseFilter {

    private List<String> restrictedUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        restrictedUrls.add("/login");
        restrictedUrls.add("/register");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        String uri = httpServletRequest.getRequestURI();

        if (isSignedInUser(session) && restrictedUrls.contains(uri)) {
            httpServletResponse.sendRedirect("/profile");
            return;
        }

        chain.doFilter(request, response);
    }
}
