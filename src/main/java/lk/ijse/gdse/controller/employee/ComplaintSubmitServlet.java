package lk.ijse.gdse.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.model.Complaint;

import java.io.IOException;

@WebServlet("/submit-complaint")
public class ComplaintSubmitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/jsp/complaint-form.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(req.getParameter("userId"));
            String subject = req.getParameter("subject");
            String description = req.getParameter("description");

            Complaint complaint = new Complaint();
            complaint.setUserId(userId);
            complaint.setSubject(subject);
            complaint.setDescription(description);
            complaint.setStatus("Pending"); // Default status

            ComplaintDAO dao = new ComplaintDAO();
            boolean saved = dao.insertComplaint(complaint);

            if (saved) {
                resp.sendRedirect(req.getContextPath() + "/jsp/employee.jsp?msg=success");
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/complaint-form.jsp?error=savefail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/complaint-form.jsp?error=exception");
        }
    }
}
