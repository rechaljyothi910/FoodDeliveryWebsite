package com.fooddelivery.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.fooddelivery.daoimplemets.UserDAOImplemention;
import com.fooddelivery.model.User;
import com.fooddelivery.dao.UserDAO;


@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String redirect = request.getParameter("redirect");
        
        UserDAOImplemention userDAO = new UserDAOImplemention();
        
        // Check if email already exists
        if (userDAO.emailExists(email)) {
            request.setAttribute("error", "Email already registered. Please login.");
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        // Create new user
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setPassword(password);
        newUser.setAddress(address);
        
        int userId = userDAO.createUser(newUser);
        
        if (userId > 0) {
            // Signup successful - auto login
            newUser.setUserId(userId);
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);
            session.setAttribute("userId", userId);
            session.setAttribute("userName", name);
            
            // Redirect to checkout or home
            if ("checkout".equals(redirect)) {
                response.sendRedirect("checkout.jsp");
            } else {
                response.sendRedirect("home");
            }
            
        } else {
            // Signup failed
            request.setAttribute("error", "Registration failed. Please try again.");
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
        }
    }
}