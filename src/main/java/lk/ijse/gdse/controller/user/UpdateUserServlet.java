package lk.ijse.gdse.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.UserDAO;
import lk.ijse.gdse.model.User;

import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        UserDAO userDAO = new UserDAO();

        User existingUser = userDAO.getUserById(id);
        if (existingUser == null) {
            response.sendRedirect("users?error=UserNotFound");
            return;
        }

        if (password == null || password.trim().isEmpty()) {
            password = existingUser.getPassword();
        }

        boolean isUpdated = userDAO.updateUser(id, username, password, role);
        if (isUpdated) {
            response.sendRedirect("users?message=UserUpdated");
        } else {
            response.sendRedirect("editUser?id=" + id + "&error=UpdateFailed");
        }
    }
}
