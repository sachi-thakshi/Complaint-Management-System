package lk.ijse.gdse.controller.user;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.UserDAO;

import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserDAO userDAO = new UserDAO();

        boolean deleted = userDAO.deleteUser(Integer.parseInt(id));
        if(deleted){
            response.sendRedirect(request.getContextPath() + "/users?msg=User deleted successfully");
        } else {
            response.sendRedirect(request.getContextPath() + "/users?error=Failed to delete user");
        }
    }
}

