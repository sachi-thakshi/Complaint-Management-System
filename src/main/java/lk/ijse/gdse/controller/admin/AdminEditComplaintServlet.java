package lk.ijse.gdse.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.model.Complaint;
import lk.ijse.gdse.model.User;

import java.io.IOException;

@WebServlet("/adminEditComplaint")
public class AdminEditComplaintServlet extends HttpServlet {

    // Load complaint data into edit form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            ComplaintDAO dao = new ComplaintDAO();
            Complaint complaint = dao.getComplaintById(id);

            if (complaint != null) {
                request.setAttribute("complaint", complaint);
                request.getRequestDispatcher("jsp/admin-edit-complaints.jsp").forward(request, response);
                return;
            }
        }

        response.sendRedirect("adminDashboard");
    }

    // Update complaint data in DB
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect("jsp/login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        String remarks = request.getParameter("remarks");

        ComplaintDAO dao = new ComplaintDAO();
        boolean updated = dao.updateAdminComplaint(id, status, remarks);

        if (updated) {
            request.setAttribute("message", "Complaint updated successfully!");
        } else {
            request.setAttribute("message", "Update failed.");
        }

        response.sendRedirect("adminDashboard");
    }
}
