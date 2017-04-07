package pja.jaz.servlet;

import pja.jaz.model.User;
import pja.jaz.persistance.InMemoryUserRepository;
import pja.jaz.persistance.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.io.IOException;

public class ApplicationServlet extends HttpServlet {

    protected void include(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(page).forward(request, response);
    }

    protected String getField(HttpServletRequest request, String name, boolean required) {
        String value = request.getParameter(name);

        if (null == value || value.trim().isEmpty()) {
            if (required) {
                throw new ValidationException("Field is required");
            } else {
                value = null;
            }
        }

        return value;
    }

    protected boolean isApplicationPath(String url) {
        return null != url && (url.startsWith("/images") || url.startsWith("/css"));
    }

    protected User getUser(HttpSession session) {
        if (null != session && null != session.getAttribute("user")) {
            return (User) session.getAttribute("user");
        }

        return null;
    }

    protected boolean isUser(HttpSession session) {
        return null != getUser(session);
    }

    protected UserRepository getUserProvider() {
        return new InMemoryUserRepository();
    }
}
