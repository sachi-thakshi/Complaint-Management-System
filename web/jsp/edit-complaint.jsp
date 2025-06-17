<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.model.Complaint" %>
<%@ page import="lk.ijse.gdse.model.User" %>
<%@ page import="lk.ijse.gdse.dao.ComplaintDAO" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null || !"employee".equals(user.getRole())) {
    response.sendRedirect("login.jsp");
    return;
  }

  String debugMsg = "";
  String idParam = request.getParameter("id");
  Complaint complaint = null;

  if (idParam != null && !idParam.trim().isEmpty()) {
    try {
      int id = Integer.parseInt(idParam);
      ComplaintDAO dao = new ComplaintDAO();
      complaint = dao.getComplaintById(id);
    } catch (Exception e) {
      debugMsg = "Error loading complaint: " + e.getMessage();
    }
  }

  request.setAttribute("debugMsg", debugMsg);
%>

<!DOCTYPE html>
<html>
<head>
  <title>Edit Complaint</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit-complaint.css">
</head>
<body>

<h1>Edit Complaint</h1>

<% String msg = (String) request.getParameter("status"); %>
<% if ("success".equals(msg)) { %>
<p class="message success">✅ Complaint updated successfully!</p>
<% } else if ("error".equals(msg)) { %>
<p class="message error">❌ Failed to update complaint. Please try again.</p>
<% } %>

<p class="debug"><%= debugMsg %></p>

<form action="<%= request.getContextPath() %>/update-complaint" method="post">
  <input type="hidden" name="id" value="<%= complaint != null ? complaint.getId() : "" %>" />

  <label for="subject">Subject:</label>
  <input type="text" id="subject" name="subject" value="<%= complaint != null && complaint.getSubject() != null ? complaint.getSubject() : "" %>" required />

  <label for="description">Description:</label>
  <textarea id="description" name="description" rows="10" required><%= complaint != null && complaint.getDescription() != null ? complaint.getDescription() : "" %></textarea>

  <button type="submit">Update Complaint</button>
</form>

<br/>
<a href="<%= request.getContextPath() %>/jsp/my-complaints.jsp"> ⬅️ Back to My Complaints</a>

</body>
</html>
