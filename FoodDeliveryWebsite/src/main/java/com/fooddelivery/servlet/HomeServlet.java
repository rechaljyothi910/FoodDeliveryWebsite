package com.fooddelivery.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.fooddelivery.daoimplemets.RestaurentDAOImplementation;
import com.fooddelivery.model.Restaurant;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RestaurentDAOImplementation restaurantDao=new RestaurentDAOImplementation();
		List<Restaurant> allRestaurants= restaurantDao.getAllRestaurants();
		request.setAttribute("restaurants", allRestaurants);
		RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
		rd.forward(request, response);
	}

	

}
