package pja.jaz.servlet;

import pja.jaz.model.Role;
import pja.jaz.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;

@WebServlet("/admin/premium")
public class AdminPremiumServlet extends ApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        include("/WEB-INF/html/admin_premium.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String username = getField(request, "username", true);

            User user = getUserProvider().findByUsername(username);
            if (null != user) {
                user.setRole(Role.USER == user.getRole() ? Role.PREMIUM : Role.USER);
                getUserProvider().update(user);
                response.sendRedirect("/admin/users");
            } else {
                throw new ValidationException("User doesn't exist!");
            }
        } catch (ValidationException e) {
            response.sendRedirect("/admin/premium");
        }
    }
}
