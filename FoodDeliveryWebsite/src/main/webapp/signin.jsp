<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In - Food Delivery</title>
    <link rel="stylesheet" href="css/auth.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<%
    String redirect = request.getParameter("redirect");
    String errorMsg = (String) request.getAttribute("error");
%>

<div class="auth-container">
    <div class="auth-box">
        <div class="auth-header">
            <i class="fas fa-utensils"></i>
            <h2>Welcome Back!</h2>
            <p>Sign in to complete your order</p>
        </div>

        <% if (errorMsg != null) { %>
            <div class="error-message">
                <i class="fas fa-exclamation-circle"></i> <%= errorMsg %>
            </div>
        <% } %>

        <form action="LoginServlet" method="post" class="auth-form">
            <input type="hidden" name="redirect" value="<%= redirect != null ? redirect : "home" %>">
            
            <div class="form-group">
                <label for="email"><i class="fas fa-envelope"></i> Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label for="password"><i class="fas fa-lock"></i> Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit" class="btn-primary">
                Sign In <i class="fas fa-arrow-right"></i>
            </button>
        </form>

        <div class="auth-footer">
            <p>Don't have an account? <a href="signup.jsp?redirect=<%= redirect != null ? redirect : "home" %>">Sign Up</a></p>
        </div>

        <a href="home" class="back-link">
            <i class="fas fa-arrow-left"></i> Back to Home
        </a>
    </div>
</div>

</body>
</html>