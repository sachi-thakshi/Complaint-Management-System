package lk.ijse.gdse.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.model.Complaint;
import lk.ijse.gdse.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/employeeComplaints")
public class EmployeeComplaintsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Check session and user
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (!"employee".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        // Get user's complaints
        ComplaintDAO dao = new ComplaintDAO();
        List<Complaint> complaints = dao.getComplaintsByUserId(user.getId());

        req.setAttribute("complaints", complaints);

        String msg = req.getParameter("msg");
        String error = req.getParameter("error");
        if (msg != null) req.setAttribute("msg", msg);
        if (error != null) req.setAttribute("error", error);

        req.getRequestDispatcher("/jsp/my-complaints.jsp").forward(req, resp);
    }
}
