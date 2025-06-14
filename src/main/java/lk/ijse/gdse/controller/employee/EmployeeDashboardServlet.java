package lk.ijse.gdse.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/employeeDashboard")
public class EmployeeDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null || !"employee".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
            return;
        }

        ComplaintDAO dao = new ComplaintDAO();
        List complaints = dao.getComplaintsByUserId(user.getId());

        req.setAttribute("complaints", complaints);

        // success/error message redirect param capture
        String msg = req.getParameter("msg");
        String error = req.getParameter("error");

        req.setAttribute("msg", msg);
        req.setAttribute("error", error);

        req.getRequestDispatcher("/jsp/employee.jsp").forward(req, resp);
    }
}
