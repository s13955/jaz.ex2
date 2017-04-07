package pja.jaz.servlet;

import pja.jaz.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends ApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (!(null == session.getAttribute("user"))) {
            System.out.println(String.format("Username: %s", ((User) session.getAttribute("user")).getUsername()));
            include("/login.jsp", request, response);
        }
    }
}
