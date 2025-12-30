<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List ,com.fooddelivery.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spicy Spoon - Restaurants</title>
    <link rel="stylesheet" href="css/home.css">
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700&family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

    <!-- Top Bar -->
    <div id="topbar">
        <div class="container">
            <div class="contact-info">
                <i class="fas fa-envelope"></i>
                <a href="mailto:contact@restaurantly.com">contact@spicyspoon.com</a>
                <i class="fas fa-phone"></i>
                <span>+91 6300 0499 10</span>
            </div>
            <div class="languages">
                <span>En</span> / <span>De</span>
            </div>
        </div>
    </div>

    <!-- Header -->
    <header id="header">
        <div class="container">
            <div class="header-content">
                <h1 class="logo">Spicy Spoon</h1>
                <nav class="navbar">
                    <ul>
                        <li><a href="#hero" class="active">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#restaurants">Restaurants</a></li>
                        <li><a href="#events">Events</a></li>
                        <li><a href="#gallery">Gallery</a></li>
                        <li><a href="#contact">Contact</a></li>
                    </ul>
                </nav>
                 <a href="signin.jsp" class="btn-book">signup/login</a>

                <div class="mobile-nav-toggle">
                    <i class="fas fa-bars"></i>
                </div>
            </div>
        </div>
    </header>

    <!-- Hero Section -->
    <section id="hero">
        <div class="hero-overlay"></div>
        <div class="hero-container">
            <h2>Welcome to <span>Spicy Spoon</span></h2>
            <p>Delivering great food for more than 18 years!</p>
            <div class="hero-btns">
                <a href="#menu" class="btn-menu">Our Menu</a>
                <a href="#" class="btn-book-hero" onclick="openBookingModal()">Book a Table</a>
            </div>
        </div>
    </section>

    <!-- About Section -->
    <section id="about">
        <div class="container">
            <div class="about-wrapper">
                <div class="about-img">
                    <img src="images/home/about.jpg" alt="About Us">
                </div>
                <div class="about-content">
                    <div class="section-title-left">
                        <h2>About <span>Spicy Spoon</span></h2>
                    </div>
                    <p class="fst-italic">
                        We bring you the finest dining experience with a blend of traditional and contemporary cuisines.
                    </p>
                    <ul class="about-list">
                        <li><i class="fas fa-check-circle"></i> Fresh ingredients sourced locally</li>
                        <li><i class="fas fa-check-circle"></i> Expert chefs with years of experience</li>
                        <li><i class="fas fa-check-circle"></i> Warm and welcoming atmosphere</li>
                    </ul>
                    <p>
                        Since our establishment, we've been committed to providing exceptional culinary experiences. Our passion for food and dedication to quality has made us one of the most loved restaurant chains in the region.
                    </p>
                </div>
            </div>
        </div>
    </section>

    <!-- Restaurants Section -->
    <section id="restaurants">
        <div class="container">
            <div class="section-title">
                <h2>Our <span>Restaurants</span></h2>
                <p>Choose from our wide variety of dining options</p>
            </div>

            <div class="restaurants-grid">
               <%
List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");
if(restaurants != null) {
    for(Restaurant r : restaurants) {
%>
    <div class="restaurant-card">
        <a href="menu?restaurantId=<%= r.getRestaurantId() %>&name=<%= java.net.URLEncoder.encode(r.getName(), "UTF-8") %>&address=<%= java.net.URLEncoder.encode(r.getAddress(), "UTF-8") %>">
            <div class="card-img">
                <img src="<%= request.getContextPath() + r.getImagePath() %>" 
                     alt="<%= r.getName() %>"
                     onerror="this.src='<%= request.getContextPath() %>/images/restaurants/default.jpg'">
            </div>
            <div class="card-body">
                <div class="card-header">
                    <h3 class="restaurant-name"><%= r.getName() %></h3>
                    <span class="rating">
                        <%
                        double rating = r.getRating();
                        int fullStars = (int) rating;
                        boolean hasHalfStar = (rating - fullStars) >= 0.5;
                        
                        for(int i = 0; i < fullStars; i++) {
                        %>
                            <i class="fas fa-star"></i>
                        <%
                        }
                        
                        if(hasHalfStar) {
                        %>
                            <i class="fas fa-star-half-alt"></i>
                        <%
                        }
                        
                        int totalStars = hasHalfStar ? fullStars + 1 : fullStars;
                        for(int i = totalStars; i < 5; i++) {
                        %>
                            <i class="far fa-star"></i>
                        <%
                        }
                        %>
                        <span class="rating-num"><%= r.getRating() %></span>
                    </span>
                </div>
                <p class="cuisine-type">
                    <i class="fas fa-utensils"></i> <%= r.getCusineType() %>
                </p>
                <div class="restaurant-info">
                    <span class="delivery-time">
                        <i class="far fa-clock"></i> <%= r.getEta() %>
                    </span>
                    <span class="cost">
                        <i class="fas fa-wallet"></i> ₹350 for two
                    </span>
                </div>
            </div>
        </a>
    </div>
<%
    }
}
%>
            </div>
        </div>
    </section>

   
    <!-- Events Section -->
    <section id="events">
        <div class="container">
            <div class="section-title">
                <h2>Upcoming <span>Events</span></h2>
                <p>Organize your events in our restaurant</p>
            </div>

            <div class="events-slider">
                <div class="event-item">
                    <div class="event-img">
                        <img src="images/home/bdayevents.jpeg" alt="Birthday Party">
                    </div>
                    <div class="event-content">
                        <h3>Birthday Parties</h3>
                        <div class="event-price">₹2999</div>
                        <p class="fst-italic">
                            Celebrate your special day with us in style
                        </p>
                        <ul class="event-list">
                            <li><i class="fas fa-check-circle"></i> Customized decoration</li>
                            <li><i class="fas fa-check-circle"></i> Special birthday cake</li>
                            <li><i class="fas fa-check-circle"></i> Music and entertainment</li>
                        </ul>
                        <p>
                            Make your birthday unforgettable with our exclusive party packages. We handle everything from decorations to catering.
                        </p>
                    </div>
                </div>

                <div class="event-item">
                    <div class="event-img">
                        <img src="images/home/privatepartyevent.jpeg" alt="Private Parties">
                    </div>
                    <div class="event-content">
                        <h3>Private Parties</h3>
                        <div class="event-price">₹4999</div>
                        <p class="fst-italic">
                            Host exclusive gatherings in our private dining areas
                        </p>
                        <ul class="event-list">
                            <li><i class="fas fa-check-circle"></i> Private dining room</li>
                            <li><i class="fas fa-check-circle"></i> Customized menu</li>
                            <li><i class="fas fa-check-circle"></i> Dedicated service staff</li>
                        </ul>
                        <p>
                            Perfect for corporate events, family gatherings, or intimate celebrations with your loved ones.
                        </p>
                    </div>
                </div>

                <div class="event-item">
                    <div class="event-img">
                        <img src="images/home/customepartyevent.jpeg" alt="Custom Parties">
                    </div>
                    <div class="event-content">
                        <h3>Custom Parties</h3>
                        <div class="event-price">₹6999</div>
                        <p class="fst-italic">
                            Create your dream event with our bespoke services
                        </p>
                        <ul class="event-list">
                            <li><i class="fas fa-check-circle"></i> Full venue booking</li>
                            <li><i class="fas fa-check-circle"></i> Chef's special menu</li>
                            <li><i class="fas fa-check-circle"></i> Live entertainment</li>
                        </ul>
                        <p>
                            Whether it's a wedding reception or anniversary celebration, we'll make it truly special and memorable.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Gallery Section -->
    <section id="gallery">
        <div class="container">
            <div class="section-title">
                <h2>Our <span>Gallery</span></h2>
                <p>Some photos from our restaurant</p>
            </div>

            <div class="gallery-grid">
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary1.jpeg" alt="Gallery 1">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary2.jpeg" alt="Gallery 2">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary3.jpeg" alt="Gallery 3">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary4.jpeg" alt="Gallery 4">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary5.jpeg" alt="Gallery 5">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary6.jpeg" alt="Gallery 6">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary7.jpeg" alt="Gallery 7">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
                <div class="gallery-item">
                    <img src="images/home/gallary/gallary7.jpeg" alt="Gallery 8">
                    <div class="gallery-overlay">
                        <i class="fas fa-search-plus"></i>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Contact Section -->
    <section id="contact">
        <div class="container">
            <div class="section-title">
                <h2>Contact <span>Us</span></h2>
                <p>Get in touch with us</p>
            </div>

            <div class="contact-wrapper">
                <div class="contact-info-wrap">
                    <div class="contact-info-item">
                        <div class="contact-icon">
                            <i class="fas fa-map-marker-alt"></i>
                        </div>
                        <div>
                            <h4>Location:</h4>
                            <p>BTM Layout, Bengaluru, 560076</p>
                        </div>
                    </div>

                    <div class="contact-info-item">
                        <div class="contact-icon">
                            <i class="fas fa-envelope"></i>
                        </div>
                        <div>
                            <h4>Email:</h4>
                            <p>info@spicyspoon.com</p>
                        </div>
                    </div>

                    <div class="contact-info-item">
                        <div class="contact-icon">
                            <i class="fas fa-phone"></i>
                        </div>
                        <div>
                            <h4>Call:</h4>
                            <p>+91 6300 0499 10</p>
                        </div>
                    </div>

                    <div class="contact-info-item">
                        <div class="contact-icon">
                            <i class="far fa-clock"></i>
                        </div>
                        <div>
                            <h4>Open Hours:</h4>
                            <p>Mon-Sat: 11AM - 11PM</p>
                        </div>
                    </div>
                </div>

                <div class="contact-form-wrap">
                    <form action="contact" method="post" class="contact-form">
                        <div class="form-row">
                            <div class="form-group">
                                <input type="text" name="name" class="form-control" placeholder="Your Name" required>
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" placeholder="Your Email" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="subject" class="form-control" placeholder="Subject" required>
                        </div>
                        <div class="form-group">
                            <textarea name="message" class="form-control" rows="5" placeholder="Message" required></textarea>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn-submit">Send Message</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <!-- Booking Modal -->
    <div id="bookingModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeBookingModal()">&times;</span>
            <div class="modal-header">
                <h2>Book a Table</h2>
                <p>Reserve your table for an unforgettable dining experience</p>
            </div>
            <form action="checkout.jsp" method="post" class="booking-form">
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Full Name *</label>
                        <input type="text" id="fullName" name="fullName" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email *</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="phone">Phone Number *</label>
                        <input type="tel" id="phone" name="phone" class="form-control" pattern="[0-9]{10}" required>
                    </div>
                    <div class="form-group">
                        <label for="date">Date *</label>
                        <input type="date" id="date" name="date" class="form-control" required>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="time">Time *</label>
                        <input type="time" id="time" name="time" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="guests">Number of Guests *</label>
                        <select id="guests" name="guests" class="form-control" required>
                            <option value="">Select</option>
                            <option value="1">1 Person</option>
                            <option value="2">2 People</option>
                            <option value="3">3 People</option>
                            <option value="4">4 People</option>
                            <option value="5">5 People</option>
                            <option value="6">6 People</option>
                            <option value="7+">7+ People</option>
                        </select>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="message">Special Requests (Optional)</label>
                    <textarea id="message" name="message" class="form-control" rows="3" placeholder="Any special requirements..."></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="button" class="btn-cancel" onclick="closeBookingModal()">Cancel</button>
                    <button type="submit" class="btn-confirm">Proceed to Checkout</button>
                </div>
            </form>
        </div>
    </div>

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

    <script>
        // Smooth scrolling
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                const target = document.querySelector(this.getAttribute('href'));
                if (target) {
                    target.scrollIntoView({
                        behavior: 'smooth',
                        block: 'start'
                    });
                }
            });
        });

        // Active nav on scroll
        window.addEventListener('scroll', () => {
            let current = '';
            const sections = document.querySelectorAll('section');
            
            sections.forEach(section => {
                const sectionTop = section.offsetTop;
                const sectionHeight = section.clientHeight;
                if (pageYOffset >= (sectionTop - 200)) {
                    current = section.getAttribute('id');
                }
            });

            document.querySelectorAll('.navbar ul li a').forEach(li => {
                li.classList.remove('active');
                if (li.getAttribute('href').includes(current)) {
                    li.classList.add('active');
                }
            });
        });

        // Booking Modal Functions
        function openBookingModal() {
            document.getElementById('bookingModal').style.display = 'block';
            document.body.style.overflow = 'hidden';
        }

        function closeBookingModal() {
            document.getElementById('bookingModal').style.display = 'none';
            document.body.style.overflow = 'auto';
        }

        window.onclick = function(event) {
            const modal = document.getElementById('bookingModal');
            if (event.target == modal) {
                closeBookingModal();
            }
        }

        // Specials Tabs
        document.querySelectorAll('.specials-tabs li').forEach(tab => {
            tab.addEventListener('click', function() {
                const targetTab = this.getAttribute('data-tab');
                
                document.querySelectorAll('.specials-tabs li').forEach(t => t.classList.remove('active'));
                document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));
                
                this.classList.add('active');
                document.getElementById(targetTab).classList.add('active');
            });
        });

        // Set minimum date for booking
        const dateInput = document.getElementById('date');
        if (dateInput) {
            const today = new Date().toISOString().split('T')[0];
            dateInput.setAttribute('min', today);
        }

        // Back to top button
        const backToTop = document.querySelector('.back-to-top');
        window.addEventListener('scroll', () => {
            if (window.pageYOffset > 100) {
                backToTop.style.display = 'flex';
            } else {
                backToTop.style.display = 'none';
            }
        });
    </script>

</body>
</html>