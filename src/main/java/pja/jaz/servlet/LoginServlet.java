package pja.jaz.servlet;

import pja.jaz.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends ApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        include("/WEB-INF/html/login.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String username = getField(request, "username", true);
            String password = getField(request, "password", true);

            User user = getUserProvider().findByUsername(username);

            if (null != user && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/");
            } else {
                throw new ValidationException(String.format("Wrong credentials for user: %s", username));
            }
        } catch (ValidationException e) {
            response.sendRedirect("/login");
        }
    }
}
