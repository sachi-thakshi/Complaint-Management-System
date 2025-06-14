<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 14/06/2025
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>

<div class="form-container">
    <h2>Register</h2>

    <% if (request.getAttribute("successMsg") != null) { %>
    <p style="color:green;"><%= request.getAttribute("successMsg") %></p>
    <% } else if (request.getAttribute("errorMsg") != null) { %>
    <p style="color:red;"><%= request.getAttribute("errorMsg") %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/register" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username"
               value="<%= request.getAttribute("errorMsg") != null ? request.getParameter("username") : "" %>" required />

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required />

        <label for="role">Role:</label>
        <select name="role" id="role" required>
            <option value="">-- Select Role --</option>
            <option value="employee">Employee</option>
            <option value="admin">Admin</option>
        </select>

        <br><br>
        <input type="submit" value="Register" />
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/auth">⬅️ Back to Login</a>
</div>

</body>
</html>


