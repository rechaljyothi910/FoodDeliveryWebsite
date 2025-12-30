<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.fooddelivery.model.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Checkout - Food Delivery</title>
<link rel="stylesheet" type="text/css" href="css/checkout.css">
</head>
<body>
<%
//Check if user is logged in
User user = (User) session.getAttribute("user");
if (user == null) {
    response.sendRedirect("signin.jsp?redirect=checkout");
    return;
}

// Check if cart exists
Cart cart = (Cart) session.getAttribute("cart");
if (cart == null || cart.getItems().isEmpty()) {
    response.sendRedirect("cart.jsp");
    return;
}

String restaurantName = (String) session.getAttribute("currentRestaurantName");
%>
    <header>
        <h1>🛒 Checkout</h1>
    </header>

    <div class="container">
        <a href="cart.jsp" class="back-link">← Back to Cart</a>

        <!-- Checkout Form -->
        <form action="checkout" method="post">
            <div class="checkout-card">
                <h2>📍 Delivery Address</h2>
                
                <div class="form-group">
                    <label for="fullName">Full Name *</label>
                    <input type="text" id="fullName" name="fullName" required placeholder="Enter your full name">
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number *</label>
                    <input type="text" id="phone" name="phone" required placeholder="Enter your phone number">
                </div>

                <div class="form-group">
                    <label for="address">Street Address *</label>
                    <textarea id="address" name="address" required placeholder="House number, building name, street"></textarea>
                </div>

                <div class="form-group">
                    <label for="city">City *</label>
                    <input type="text" id="city" name="city" required placeholder="Enter your city">
                </div>

                <div class="form-group">
                    <label for="pincode">Pincode *</label>
                    <input type="text" id="pincode" name="pincode" required placeholder="Enter pincode">
                </div>
            </div>

            <div class="checkout-card">
                <h2>💳 Payment Method</h2>
                
                <div class="payment-options">
                    <div class="payment-option">
                        <input type="radio" id="cod" name="paymentMethod" value="Cash on Delivery" checked>
                        <label for="cod">💵 Cash on Delivery</label>
                    </div>

                    <div class="payment-option">
                        <input type="radio" id="card" name="paymentMethod" value="Credit/Debit Card">
                        <label for="card">💳 Credit/Debit Card</label>
                    </div>

                    <div class="payment-option">
                        <input type="radio" id="upi" name="paymentMethod" value="UPI">
                        <label for="upi">📱 UPI Payment</label>
                    </div>

                    <div class="payment-option">
                        <input type="radio" id="wallet" name="paymentMethod" value="Wallet">
                        <label for="wallet">👛 Digital Wallet</label>
                    </div>
                </div>
            </div>

            <!-- Order Summary Section -->
           <div class="checkout-card">
    <h2>📋 Order Summary</h2>

    <!-- Restaurant Name -->
    <% if (restaurantName != null) { %>
        <div class="restaurant-name">
            <i class="fas fa-utensils"></i>
            <strong><%= restaurantName %></strong>
        </div>
    <% } else { %>
    <div class="restaurant-name">
            <i class="fas fa-utensils"></i>
            <strong>Restaurant</strong>
        </div>
    <% } %>

    <!-- Item List -->
    <div class="order-items">
        <% for (CartItem item : cart.getItems().values()) { %>
            <div class="summary-item">
                <span><%= item.getItemName() %> × <%= item.getQuantity() %></span>
                <span>$<%= String.format("%.2f", item.getItemPrice() * item.getQuantity()) %></span>
            </div>
        <% } %>
    </div>

    <!-- Total Price -->
    <div class="total-price">
        <strong>Total Amount:</strong>
        <strong class="price">$<%= String.format("%.2f", cart.getTotalPrice()) %></strong>
    </div>
</div>

			<!-- Submit Button -->
            <button type="submit" class="submit-btn">
                Place Order <i class="fas fa-check-circle"></i>
            </button>
        </form>
    </div>
</body>
</html>