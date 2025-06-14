package lk.ijse.gdse.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.model.Complaint;
import lk.ijse.gdse.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session & role check
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        // Get all complaints
        ComplaintDAO complaintDAO = new ComplaintDAO();
        List<Complaint> complaints = complaintDAO.getAllComplaints();

        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("jsp/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Session & role check again
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        // Check if this is a delete action
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            ComplaintDAO complaintDAO = new ComplaintDAO();
            boolean deleted = complaintDAO.deleteComplaint(id);

            if (deleted) {
                request.setAttribute("message", "Complaint deleted successfully!");
            } else {
                request.setAttribute("message", "Failed to delete complaint.");
            }
        }

        // Reload data after deletion
        doGet(request, response);
    }
}
