<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 13/06/2025
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.gdse.model.User" %>
<%@ page import="lk.ijse.gdse.dao.ComplaintDAO" %>
<%@ page import="lk.ijse.gdse.model.Complaint" %>
<%@ page import="java.util.List" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"employee".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
    if (complaints == null) {
        ComplaintDAO complaintDAO = new ComplaintDAO();
        complaints = complaintDAO.getComplaintsByUserId(user.getId());
    }

    String msg = request.getParameter("msg");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Complaints</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my-complaints.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<h1>Welcome, <%= user.getUsername() %> - My Complaints</h1>

<% if (msg != null) { %>
<p class="success-msg"><%= msg %></p>
<% } %>

<% if (error != null) { %>
<p class="error-msg"><%= error %></p>
<% } %>

<% if (complaints == null || complaints.isEmpty()) { %>
<p>No complaints found.</p>
<% } else { %>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Subject</th>
        <th>Description</th>
        <th>Status</th>
        <th>Remarks</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <% for (Complaint c : complaints) {
        String status = c.getStatus();
        if (status == null) status = "";
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getSubject() %></td>
        <td><%= c.getDescription() %></td>
        <td><%= status %></td>
        <td><%= c.getRemarks() == null ? "" : c.getRemarks() %></td>
        <td>
            <% if (!"Resolved".equalsIgnoreCase(status)) { %>
            <a href="<%= request.getContextPath() %>/jsp/edit-complaint.jsp?id=<%= c.getId() %>" id="edit-btn">Edit</a>

<%--            <form method="post" action="${pageContext.request.contextPath}/delete-complaint" style="display:inline;"--%>
<%--                  onsubmit="return confirm('Are you sure you want to delete this complaint?');">--%>
<%--                <input type="hidden" name="id" value="<%= c.getId() %>"/>--%>
<%--                <input type="submit" value="Delete"/>--%>
<%--            </form>--%>
            <form method="post" action="${pageContext.request.contextPath}/delete-complaint" class="delete-form" style="display:inline;">
                <input type="hidden" name="id" value="<%= c.getId() %>"/>
                <button type="button" class="delete-btn">Delete</button>
            </form>
            <% } else { %>
            <span class="resolved">Resolved</span>
            <% } %>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<% } %>

<br>
<a href="${pageContext.request.contextPath}/employeeDashboard" id="back-btn">⬅️ Back to Dashboard</a>

<script src="${pageContext.request.contextPath}/js/myComplaints.js"></script>

</body>
</html>
