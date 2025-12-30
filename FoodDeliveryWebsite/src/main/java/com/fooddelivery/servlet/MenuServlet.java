package com.fooddelivery.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.fooddelivery.daoimplemets.MenuDAOImplementation;
import com.fooddelivery.model.Menu;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String restaurantIdStr = request.getParameter("restaurantId");
        String restaurantName = request.getParameter("name");
        String restaurantAddress = request.getParameter("address");
        
        Integer restaurantId = null;
        
        if (restaurantIdStr != null && !restaurantIdStr.isEmpty()) {
            restaurantId = Integer.parseInt(restaurantIdStr);
            session.setAttribute("restaurantId", restaurantId);
        } else {
            restaurantId = (Integer) session.getAttribute("restaurantId");
        }
        
        if (restaurantId == null) {
            response.sendRedirect("home");
            return;
        }
        
        if (restaurantName != null && !restaurantName.isEmpty()) {
            session.setAttribute("currentRestaurantName", restaurantName);
        }
        
        if (restaurantAddress != null && !restaurantAddress.isEmpty()) {
            session.setAttribute("currentRestaurantAddress", restaurantAddress);
        }
        
        MenuDAOImplementation mdi = new MenuDAOImplementation();
        List<Menu> menuList = mdi.getRestaurantItem(restaurantId);
        
        request.setAttribute("menu", menuList);
        
        RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
        rd.forward(request, response);
    }
}