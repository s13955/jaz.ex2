package pja.jaz.servlet;

import pja.jaz.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/error403", "/error404", "/debug", "/premium", "/profile" })
public class StaticPageServlet extends ApplicationServlet {

    private Map<String, String> pages = new HashMap<>();

    @Override
    public void init() throws ServletException {
        pages.put("premium", "/WEB-INF/html/static-pages/premium.jsp");
        pages.put("profile", "/WEB-INF/html/static-pages/profile.jsp");
        pages.put("debug", "/WEB-INF/html/static-pages/debug.jsp");
        pages.put("error403", "/WEB-INF/html/static-pages/error403.jsp");
        pages.put("error404", "/WEB-INF/html/static-pages/error404.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String page = path.substring(1);

        if (!pages.containsKey(page)) {
            page = "error403";
        }

        HttpSession session = request.getSession(false);

        if (null != session) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user);
        }

        include(pages.get(page), request, response);
    }
}
