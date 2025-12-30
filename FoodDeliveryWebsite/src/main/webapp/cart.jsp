<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fooddelivery.model.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart - Food Delivery</title>
    <link rel="stylesheet" href="css/cart.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
    String restaurantName = (String) session.getAttribute("currentRestaurantName");
    String restaurantAddress = (String) session.getAttribute("currentRestaurantAddress");
%>

<% if (cart != null && !cart.getItems().isEmpty()) { %>

<div class="cart-container">

    <!-- Restaurant Info -->
    <div class="restaurant-header">
        <a href="menu?restaurantId=<%= restaurantId %>" class="back-btn">
            <i class="fas fa-arrow-left"></i>
        </a>
        <div class="restaurant-info">
            <h2><%= restaurantName != null ? restaurantName : "Restaurant" %></h2>
            <p><i class="fas fa-map-marker-alt"></i> <%= restaurantAddress != null ? restaurantAddress : "Address" %></p>
        </div>
    </div>

    <!-- Cart Items List -->
    <div class="cart-items-wrapper">
        <h3 class="cart-title"><i class="fas fa-shopping-bag"></i> Your Cart</h3>

        <% for (CartItem item : cart.getItems().values()) { 
            double itemTotal = item.getItemPrice() * item.getQuantity();
        %>

        <div class="cart-item" data-menu-id="<%= item.getMenuId() %>">
            <div class="item-info">
                <span class="item-type veg"><i class="fas fa-circle"></i></span>
                <div class="item-details">
                    <h4><%= item.getItemName() %></h4>
                    <p class="item-price">$<%= String.format("%.2f", item.getItemPrice()) %></p>
                </div>
            </div>

            <div class="item-actions">
                <!-- Quantity Control -->
                <div class="quantity-control">
                    <!-- Decrease Button -->
                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                        <button type="submit" class="qty-btn" <%= item.getQuantity() <= 1 ? "disabled" : "" %>>−</button>
                    </form>

                    <span class="qty"><%= item.getQuantity() %></span>

                    <!-- Increase Button -->
                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                        <button type="submit" class="qty-btn">+</button>
                    </form>
                </div>

                <!-- Remove Button -->
                <form action="cart" method="post" style="display:inline;">
                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                    <input type="hidden" name="action" value="remove">
                    <button type="submit" class="remove-btn">
                        <i class="fas fa-trash-alt"></i> Remove
                    </button>
                </form>

                <!-- Item Total -->
                <p class="item-total">$<%= String.format("%.2f", itemTotal) %></p>
            </div>
        </div>

        <% } // END for loop %>

        <!-- Add More Items -->
        <a href="menu?restaurantId=<%= restaurantId %>" class="add-more-btn">
            <i class="fas fa-plus-circle"></i> Add more items
        </a>
    </div>

    <!-- Suggestions -->
    <div class="suggestions">
        <h4>🍰 Add dessert?</h4>
        <p>Complete your meal with something sweet</p>
    </div>

   <!-- Payment Section -->
<div class="payment-section">
    <div class="payment-card">
        <h3>Total to Pay</h3>
        <div class="total-amount">$<%= String.format("%.2f", cart.getTotalPrice()) %></div>
        
        <%
            User user = (User) session.getAttribute("user");
            if (user != null) {
                // User is logged in - go to checkout
        %>
            <form action="checkout.jsp" method="post">
                <button type="submit" class="pay-btn">
                    Proceed to Pay <i class="fas fa-arrow-right"></i>
                </button>
            </form>
        <% } else { 
            // User NOT logged in - redirect to sign-in
        %>
            <form action="signin.jsp" method="get">
                <input type="hidden" name="redirect" value="checkout">
                <button type="submit" class="pay-btn">
                    Sign In to Continue <i class="fas fa-arrow-right"></i>
                </button>
            </form>
        <% } %>
    </div>

</div>

<% } else { %>

<!-- Empty Cart -->
<div class="empty-cart">
    <i class="fas fa-shopping-cart"></i>
    <h3>Your cart is empty</h3>
    <p>Add some delicious items to get started!</p>
    <a href="home" class="btn-browse">Browse Restaurants</a>
</div>

<% } %>

<script>
    function proceedToPayment() {
        // You can add custom validation here if needed
        alert('Proceeding to payment gateway...');
    }
</script>

</body>
</html>