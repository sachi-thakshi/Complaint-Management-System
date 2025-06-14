package lk.ijse.gdse.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.ComplaintDAO;
import lk.ijse.gdse.model.Complaint;

import java.io.IOException;

@WebServlet("/update-complaint")
public class UpdateComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                // id missing - redirect to complaints list or error page
                resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp");
                return;
            }

            int id = Integer.parseInt(idParam);
            String subject = req.getParameter("subject");
            String description = req.getParameter("description");

            ComplaintDAO dao = new ComplaintDAO();
            Complaint complaint = dao.getComplaintById(id);

            if (complaint == null) {
                resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp");
                return;
            }

            complaint.setSubject(subject);
            complaint.setDescription(description);

            dao.updateComplaint(complaint);

            // Redirect without id to clear form
            resp.sendRedirect(req.getContextPath() + "/jsp/edit-complaint.jsp?status=success");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/edit-complaint.jsp?status=error");
        }
    }
}
