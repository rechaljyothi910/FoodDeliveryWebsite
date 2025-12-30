<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - Food Delivery</title>
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
            <i class="fas fa-user-plus"></i>
            <h2>Create Account</h2>
            <p>Join us and start ordering!</p>
        </div>

        <% if (errorMsg != null) { %>
            <div class="error-message">
                <i class="fas fa-exclamation-circle"></i> <%= errorMsg %>
            </div>
        <% } %>

        <form action="SignupServlet" method="post" class="auth-form">
            <input type="hidden" name="redirect" value="<%= redirect != null ? redirect : "home" %>">
            
            <div class="form-group">
                <label for="name"><i class="fas fa-user"></i> Full Name</label>
                <input type="text" id="name" name="name" placeholder="Enter your name" required>
            </div>

            <div class="form-group">
                <label for="email"><i class="fas fa-envelope"></i> Email</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group">
                <label for="phone"><i class="fas fa-phone"></i> Phone Number</label>
                <input type="tel" id="phone" name="phone" placeholder="Enter your phone" required>
            </div>

            <div class="form-group">
                <label for="password"><i class="fas fa-lock"></i> Password</label>
                <input type="password" id="password" name="password" placeholder="Create a password" required>
            </div>

            <div class="form-group">
                <label for="address"><i class="fas fa-map-marker-alt"></i> Delivery Address</label>
                <textarea id="address" name="address" placeholder="Enter your delivery address" rows="3" required></textarea>
            </div>

            <button type="submit" class="btn-primary">
                Create Account <i class="fas fa-arrow-right"></i>
            </button>
        </form>

        <div class="auth-footer">
            <p>Already have an account? <a href="signin.jsp?redirect=<%= redirect != null ? redirect : "home" %>">Sign In</a></p>
        </div>

        <a href="home" class="back-link">
            <i class="fas fa-arrow-left"></i> Back to Home
        </a>
    </div>
</div>

</body>
</html>