<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 13/06/2025
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.gdse.model.User" %>
<%@ page import="lk.ijse.gdse.model.Complaint" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>

<h1>Welcome, <%= user.getUsername() %> (Admin)</h1>
<p>This is the Admin Dashboard.</p>

<% if (message != null) { %>
<p class="msg"><%= message %></p>
<% } %>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Subject</th>
        <th>Description</th>
        <th>Status</th>
        <th>Remarks</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% if (complaints != null && !complaints.isEmpty()) {
        for (Complaint complaint : complaints) { %>
    <tr>
        <td><%= complaint.getId() %></td>
        <td><%= complaint.getUserId() %></td>
        <td><%= complaint.getSubject() %></td>
        <td><%= complaint.getDescription() %></td>
        <td><%= complaint.getStatus() %></td>
        <td><%= complaint.getRemarks() %></td>
        <td class="actions">
            <form method="GET" action="adminEditComplaint">
                <input type="hidden" name="id" value="<%= complaint.getId() %>" />
                <button type="submit">Edit</button>
            </form>

            <form method="POST" action="adminDashboard" onsubmit="return confirm('Are you sure you want to delete this complaint?');">
                <input type="hidden" name="id" value="<%= complaint.getId() %>" />
                <button type="submit">Delete</button>
            </form>
        </td>
    </tr>
    <% }
    } else { %>
    <tr>
        <td colspan="7">No complaints found.</td>
    </tr>
    <% } %>
    </tbody>
</table>

<br>
<a href="${pageContext.request.contextPath}/auth">Logout</a>

</body>
</html>

