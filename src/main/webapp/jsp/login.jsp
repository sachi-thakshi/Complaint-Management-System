<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<h1>🏛️ Municipal IT Division - Complaint Management System</h1>
<div class="container">
    <form action="${pageContext.request.contextPath}/auth" method="post" onsubmit="return validateLoginForm()" >
        <h2>Login</h2>
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required/>
        <br/>
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required/>
        <br/>
        <input type="submit" value="Login" />
    </form>
    <p>Don't have an account? <a href="registration-user.jsp">Register Now</a></p>
</div>

<c:if test="${not empty errorMsg}">
    <p style="color:red;">${errorMsg}</p>
</c:if>

<script src="${pageContext.request.contextPath}/js/login-validation.js"></script>
</body>
</html>