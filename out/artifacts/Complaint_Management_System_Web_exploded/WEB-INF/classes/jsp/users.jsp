<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 14/06/2025
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Users List</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/users.css">
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<h2>Users List</h2>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Username</th>
    <th>Role</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <%
    java.util.List<lk.ijse.gdse.model.User> users = (java.util.List<lk.ijse.gdse.model.User>) request.getAttribute("users");

    if (users == null || users.isEmpty()) {
  %>
  <tr>
    <td colspan="4">No users found.</td>
  </tr>
  <%
  } else {
    for (lk.ijse.gdse.model.User user : users) {
  %>
  <tr>
    <td><%= user.getId() %></td>
    <td><%= user.getUsername() %></td>
    <td><%= user.getRole() %></td>
    <td class="actions">
      <form method="GET" action="editUser">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <button type="submit" class="action-btn edit-btn">Edit</button>
      </form>

<%--      <form action="deleteUser" method="POST" onsubmit="return confirm('Are you sure you want to delete this user?');">--%>
<%--        <input type="hidden" name="id" value="<%= user.getId() %>">--%>
<%--        <button type="submit" class="action-btn delete-btn">Delete</button>--%>
<%--      </form>--%>
      <form action="deleteUser" method="POST" class="delete-user-form">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <button type="button" class="action-btn delete-user-btn">Delete</button>
      </form>
    </td>
  </tr>
  <%
      }
    }
  %>
  </tbody>
</table>

<a href="${pageContext.request.contextPath}/adminDashboard" class="back-link">⬅️ Back to Dashboard</a>

<script src="${pageContext.request.contextPath}/js/users.js"></script>

</body>
</html>

