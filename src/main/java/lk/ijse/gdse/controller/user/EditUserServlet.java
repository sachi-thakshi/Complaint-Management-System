package lk.ijse.gdse.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.UserDAO;
import lk.ijse.gdse.model.User;

import java.io.IOException;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(Integer.parseInt(id));

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("jsp/edit-user.jsp").forward(request, response);
        } else {
            response.sendRedirect("users");
        }
    }
}
