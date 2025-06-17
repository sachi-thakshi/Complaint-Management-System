<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 14/06/2025
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.model.User" %>

<%
  User user = (User) request.getAttribute("user");
  if (user == null) {
%>
<p>User not found.</p>
<%
} else {
%>

<!DOCTYPE html>
<html>
<head>
  <title>Edit User</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/edit-user.css">
</head>
<body>

<h2>Edit User</h2>

<form action="${pageContext.request.contextPath}/updateUser" method="post">
  <input type="hidden" name="id" value="<%= user.getId() %>" />

  <label for="username">Username:</label>
  <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required />

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" placeholder="Enter new password or leave blank" />

  <label for="role">Role:</label>
  <select id="role" name="role" required>
    <option value="employee" <%= "employee".equals(user.getRole()) ? "selected" : "" %>>Employee</option>
    <option value="admin" <%= "admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
  </select>

  <br><br>
  <button type="submit">Update User</button>
</form>

<a href="${pageContext.request.contextPath}/users">⬅️ Back to Users List</a>

</body>
</html>

<%
  }
%>

