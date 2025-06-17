<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 13/06/2025
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.model.Complaint" %>
<%@ page import="lk.ijse.gdse.model.User" %>

<%
  User user = (User) session.getAttribute("user");
  if (user == null || !"admin".equals(user.getRole())) {
    response.sendRedirect("login.jsp");
    return;
  }

  Complaint complaint = (Complaint) request.getAttribute("complaint");
  if (complaint == null) {
    response.sendRedirect("adminDashboard");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Edit Complaint</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-edit-complaints.css">
</head>
<body>

<div class="form-container">
  <h2>Edit Complaint</h2>

  <form method="post" action="adminEditComplaint">
    <input type="hidden" name="id" value="<%= complaint.getId() %>"/>

    <label>Subject</label>
    <input type="text" value="<%= complaint.getSubject() %>" readonly/>

    <label>Description</label>
    <input type="text" value="<%= complaint.getDescription() %>" readonly/>

    <label>Status</label>
    <select name="status" required>
      <option value="Pending" <%= "Pending".equals(complaint.getStatus()) ? "selected" : "" %>>Pending</option>
      <option value="Resolved" <%= "Resolved".equals(complaint.getStatus()) ? "selected" : "" %>>Resolved</option>
    </select>

    <label>Remarks</label>
    <input type="text" name="remarks" value="<%= complaint.getRemarks() != null ? complaint.getRemarks() : "" %>" required/>

    <div class="buttons">
      <input type="submit" value="Update"/>
      <a href="adminDashboard">Cancel</a>
    </div>
  </form>
</div>

</body>
</html>
