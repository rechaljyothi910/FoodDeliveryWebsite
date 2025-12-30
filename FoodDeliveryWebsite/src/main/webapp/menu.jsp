<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.fooddelivery.model.Menu" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="css/menu.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<%
    // Get restaurant info from session
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
    String restaurantName = (String) session.getAttribute("currentRestaurantName");
    String restaurantAddress = (String) session.getAttribute("currentRestaurantAddress");
    
    // Get menu list
    List<Menu> menuList = (List<Menu>) request.getAttribute("menu");
%>

    <!-- Menu Section -->
    <section id="menu">
        <div class="container">
            <!-- Restaurant Header (Optional - Add this if you want) -->
            <div class="restaurant-header" style="text-align:center; margin-bottom:30px;">
                <h1 style="color: var(--primary-color); font-size: 2.5rem;"><%= restaurantName != null ? restaurantName : "Menu" %></h1>
                <p style="color: var(--text-muted);"><i class="fas fa-map-marker-alt"></i> <%= restaurantAddress != null ? restaurantAddress : "" %></p>
            </div>

            <div class="menu-grid">

                <!-- Static Menu Items (1-11) remain the same -->
                <!-- ... your existing static menu items ... -->

                <!-- Dynamic Menu Items from Database -->
                <% 
                if (menuList != null && !menuList.isEmpty()) {
                    for(Menu m : menuList) {
                %>
                <div class="menu-card" data-category="drinks">
                    <div class="menu-card-img">
                        <img src="<%= request.getContextPath() + m.getImagePath() %>" alt="<%= m.getItemName() %>">
                        <span class="menu-badge">Popular</span>
                        <button class="wishlist-btn"><i class="far fa-heart"></i></button>
                    </div>
                    <div class="menu-card-body">
                        <div class="menu-card-header">
                            <h3 class="item-name"><%= m.getItemName() %></h3>
                            <span class="item-price">$<%= String.format("%.2f", m.getPrice()) %></span>
                        </div>
                        <p class="item-description"><%= m.getDescription() %></p>
                        <div class="menu-card-footer">
                            <div class="item-meta">
                                <span class="item-rating"><i class="fas fa-star"></i> 4.7</span>
                                <span class="item-time"><i class="far fa-clock"></i> 5 min</span>
                            </div>
                            <form action="cart" method="post">
                                <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
                                <input type="hidden" name="quantity" value="1">
                                <input type="hidden" name="restaurantId" value="<%= restaurantId != null ? restaurantId : m.getRestaurantId() %>">
                                <input type="hidden" name="restaurantName" value="<%= restaurantName != null ? restaurantName : "" %>">
                                <input type="hidden" name="restaurantAddress" value="<%= restaurantAddress != null ? restaurantAddress : "" %>">
                                <input type="hidden" name="action" value="add">
                                
                                <button type="submit" class="btn-add-cart">
                                    <i class="fas fa-plus"></i> Add to Cart
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
                <% 
                    } 
                } else {
                %>
                <div style="grid-column: 1/-1; text-align:center; padding:50px;">
                    <p style="color: var(--text-muted); font-size: 1.2rem;">No menu items available for this restaurant.</p>
                </div>
                <% } %>

            </div>
        </div>
    </section>

   <!-- Footer -->
    <footer id="footer">
        <div class="footer-top">
            <div class="container">
                <div class="footer-grid">
                    <div class="footer-col">
                        <h4>Contact Us</h4>
                        <p><i class="fas fa-map-marker-alt"></i> BTM Layout, Bengaluru, 560076</p>
                        <p><i class="fas fa-phone"></i> +91 6300 0499 10</p>
                        <p><i class="fas fa-envelope"></i> info@spicyspoon.com</p>
                    </div>
                    <div class="footer-col">
                        <h4>Opening Hours</h4>
                        <p><strong>Mon-Sat:</strong> 11AM - 23PM</p>
                        <p><strong>Sunday:</strong> Closed</p>
                    </div>
                    <div class="footer-col">
                        <h4>Follow Us</h4>
                        <div class="social-links">
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-facebook-f"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                            <a href="#"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="copyright">
                    &copy; Copyright <strong><span>Spicy Spoon</span></strong>. All Rights Reserved
                </div>
                <div class="credits">
                    Designed by <a href="#">RechalJyothi</a>
                </div>
            </div>
        </div>
    </footer>
  <!-- Back to Top Button -->
    <a href="#" class="back-to-top"><i class="fas fa-arrow-up"></i></a>
</body>
</html>