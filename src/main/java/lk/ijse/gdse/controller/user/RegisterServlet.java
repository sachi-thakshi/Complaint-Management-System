package lk.ijse.gdse.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.UserDAO;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String role = request.getParameter("role");

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.registerUser(username, password, role);

        if (success) {
            request.setAttribute("successMsg", "✅ User registered successfully!");
            request.getRequestDispatcher("/jsp/registration-user.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMsg", "❌ Username already exists.");
            request.getRequestDispatcher("/jsp/registration-user.jsp").forward(request, response);
        }
    }
}
