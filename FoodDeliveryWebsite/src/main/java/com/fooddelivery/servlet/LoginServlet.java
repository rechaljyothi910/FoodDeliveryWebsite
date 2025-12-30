package com.fooddelivery.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fooddelivery.daoimplemets.UserDAOImplemention;
import com.fooddelivery.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String redirect = request.getParameter("redirect");
        
        UserDAOImplemention userDAO = new UserDAOImplemention();
        User user = userDAO.validateUser(email, password);
        
        if (user != null) {
            // Login successful
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getName());
            
            // Redirect based on where user came from
            if ("checkout".equals(redirect)) {
                response.sendRedirect("checkout.jsp");
            } else {
                response.sendRedirect("home");
            }
            
        } else {
            // Login failed
            request.setAttribute("error", "Invalid email or password");
            request.setAttribute("email", email); // Keep email in form
            RequestDispatcher dispatcher = request.getRequestDispatcher("signin.jsp");
            dispatcher.forward(request, response);
        }
    }
}