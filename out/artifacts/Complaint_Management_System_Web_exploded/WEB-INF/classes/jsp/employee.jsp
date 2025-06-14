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
  <style>
    .success-msg {
      color: green;
      font-weight: bold;
    }
  </style>
</head>
<body>
<h1>Welcome, <%= user.getUsername() %> (Employee)</h1>
<p>This is the Employee Dashboard.</p>

<% if ("success".equals(msg)) { %>
<p class="success-msg">Complaint submitted successfully!</p>
<% } %>

<ul>
  <li><a href="${pageContext.request.contextPath}/submit-complaint">Submit a New Complaint</a></li>
  <li><a href="${pageContext.request.contextPath}/employeeComplaints">View My Complaints</a></li>
  <li><a href="<%= request.getContextPath() %>/auth">Logout</a></li>
</ul>

</body>
</html>
