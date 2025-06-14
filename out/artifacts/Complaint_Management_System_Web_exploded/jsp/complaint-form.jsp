<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 13/06/2025
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.model.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null || !"employee".equals(user.getRole())) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <title>Submit New Complaint</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/complaint-form.css">
</head>
<body>
<h1>Submit a New Complaint</h1>

<form action="<%= request.getContextPath() %>/submit-complaint" method="post" onsubmit="return validateComplaintForm()">
  <input type="hidden" name="userId" value="<%= user.getId() %>" />

  <label>Subject:</label><br>
  <input id="subject" type="text" name="subject" required /><br><br>

  <label>Description:</label><br>
  <textarea id="description" name="description" rows="5" cols="30" required></textarea><br><br>

  <button type="submit">Submit Complaint</button>
</form>

<br>
<a href="employee.jsp">⬅️ Back to Dashboard</a>
</body>
</html>
