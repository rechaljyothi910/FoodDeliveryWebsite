package com.fooddelivery.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.fooddelivery.daoimplemets.MenuDAOImplementation;
import com.fooddelivery.model.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    Cart cart = (Cart) session.getAttribute("cart");
	    
	    String action = request.getParameter("action");
	    
	    // Only initialize/check restaurant when ADDING items
	    if ("add".equals(action)) {
	        String restaurantIdParam = request.getParameter("restaurantId");
	        
	        if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required");
	            return;
	        }
	        
	        int restaurantIdMenu = Integer.parseInt(restaurantIdParam);
	        Integer currentRestaurantIdSession = (Integer) session.getAttribute("restaurantId");
	        
	        String restaurantName = request.getParameter("restaurantName");
	        String restaurantAddress = request.getParameter("restaurantAddress");
	        
	        // Reset cart if switching restaurants
	        if (cart == null || !Integer.valueOf(restaurantIdMenu).equals(currentRestaurantIdSession)) {
	            cart = new Cart();
	            session.setAttribute("cart", cart);
	            session.setAttribute("restaurantId", restaurantIdMenu);
	            
	            if (restaurantName != null && !restaurantName.isEmpty()) {
	                session.setAttribute("currentRestaurantName", restaurantName);
	            }
	            if (restaurantAddress != null && !restaurantAddress.isEmpty()) {
	                session.setAttribute("currentRestaurantAddress", restaurantAddress);
	            }
	        }
	        
	        addItemToCart(request, cart);
	        
	    } else if ("update".equals(action)) {
	        if (cart != null) {
	            updateItemInCart(request, cart);
	        }
	        
	    } else if ("remove".equals(action)) {
	        if (cart != null) {
	            removeItemFromCart(request, cart);
	        }
	    }
	    
	    response.sendRedirect("cart.jsp");
	}

	
	private void removeItemFromCart(HttpServletRequest request, Cart cart) {
		int menuId=Integer.parseInt(request.getParameter("menuId"));
		cart.removeCartItem(menuId);
	}


	private void updateItemInCart(HttpServletRequest request, Cart cart) {
	    try {
	        String menuIdParam = request.getParameter("menuId");
	        String quantityParam = request.getParameter("quantity");
	        
	        // Check if parameters are null or empty
	        if(menuIdParam == null || menuIdParam.isEmpty() || 
	           quantityParam == null || quantityParam.isEmpty()) {
	            System.out.println("Error: menuId or quantity parameter is missing");
	            return;
	        }
	        
	        int menuId = Integer.parseInt(menuIdParam);
	        int quantity = Integer.parseInt(quantityParam);
	        
	        // Don't allow quantity less than 1
	        if(quantity < 1) {
	            cart.removeCartItem(menuId);
	        } else {
	            cart.updateCartItem(menuId, quantity);
	        }
	    } catch(NumberFormatException e) {
	        System.out.println("Error parsing quantity: " + e.getMessage());
	    }
	}


	private void addItemToCart(HttpServletRequest request, Cart cart) {
	    try {
	        String menuIdParam = request.getParameter("menuId");
	        String quantityParam = request.getParameter("quantity");
	        
	        if(menuIdParam == null || menuIdParam.isEmpty() || 
	           quantityParam == null || quantityParam.isEmpty()) {
	            System.out.println("Error: menuId or quantity parameter is missing");
	            return;
	        }
	        
	        int menuId = Integer.parseInt(menuIdParam);
	        int quantity = Integer.parseInt(quantityParam);
	        
	        MenuDAOImplementation menuDAO = new MenuDAOImplementation();
	        Menu menuItem = menuDAO.getMenu(menuId);
	        System.out.println("The menu cart in servlet" + menuItem);
	        
	        if(menuItem != null) {
	            CartItem item = new CartItem(
	                menuItem.getMenuId(), menuItem.getRestaurantId(), 
	                menuItem.getItemName(), quantity, menuItem.getPrice());
	            String restaurantName = (String) request.getSession().getAttribute("currentRestaurantName");
	            item.setRestaurantName(restaurantName);
	            cart.addCartItem(item);
	        }
	    } catch(NumberFormatException e) {
	        System.out.println("Error parsing parameters: " + e.getMessage());
	    }
	}
}