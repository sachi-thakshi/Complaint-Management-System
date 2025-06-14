package lk.ijse.gdse.controller.employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse.dao.ComplaintDAO;

import java.io.IOException;

@WebServlet("/delete-complaint")
public class DeleteComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complaintIdStr = req.getParameter("id");

        if (complaintIdStr == null || complaintIdStr.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp?error=missingid");
            return;
        }

        try {
            int complaintId = Integer.parseInt(complaintIdStr);
            ComplaintDAO dao = new ComplaintDAO();
            boolean deleted = dao.deleteComplaint(complaintId);

            if (deleted) {
                resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp?msg=deleted");
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp?error=deletefail");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp?error=invalidid");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/jsp/my-complaints.jsp?error=exception");
        }
    }
}
