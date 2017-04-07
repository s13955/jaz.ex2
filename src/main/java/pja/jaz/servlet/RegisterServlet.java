package pja.jaz.servlet;

import pja.jaz.model.Role;
import pja.jaz.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends ApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        include("/WEB-INF/html/register.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = getField(request, "username", true);
            String password = getField(request, "password", true);
            String password2 = getField(request, "password2", true);
            String email = getField(request, "email", true);

            if (!password.equals(password2)) {
                throw new ValidationException("User provided incorrect passwords");
            }

            User user = new User(username, password, email, Role.USER);
            getUserProvider().add(user);

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("/");
        } catch (ValidationException e) {
            response.sendRedirect("/register");
        }
    }
}
