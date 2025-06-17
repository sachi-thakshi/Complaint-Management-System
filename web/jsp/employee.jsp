<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.model.User" %>
<%
  User user = (User) session.getAttribute("user");
  if (user == null || !"employee".equals(user.getRole())) {
    response.sendRedirect("login.jsp");
    return;
  }

  String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
  <title>Employee Dashboard</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee.css">
</head>
<body>
<div class="container">
  <h1>Welcome, <%= user.getUsername() %> (Employee)</h1>
  <p>This is the Employee Dashboard.</p>

  <% if ("success".equals(msg)) { %>
  <p class="success-msg">✅ Complaint submitted successfully!</p>
  <% } %>

  <ul>
    <li><a href="${pageContext.request.contextPath}/submit-complaint" class="submit-btn">Submit a New Complaint</a></li>
    <li><a href="${pageContext.request.contextPath}/employeeComplaints" class="view-btn">View My Complaints</a></li>
    <li><a href="<%= request.getContextPath() %>/auth" class="logout-btn">Logout</a></li>
  </ul>
</div>

</body>
</html>
