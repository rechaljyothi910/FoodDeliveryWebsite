package com.fooddelivery.servlet;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.fooddelivery.dao.OrderDAO;
import com.fooddelivery.daoimplemets.OrderDAOImplementation;
import com.fooddelivery.daoimplemets.OrderItemDAOImplementation;
import com.fooddelivery.model.Cart;
import com.fooddelivery.model.CartItem;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.OrderItem;
import com.fooddelivery.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	
	private OrderDAO orderdao;
	private OrderItemDAOImplementation orderItemdao;
	@Override
	public void init() throws ServletException {
		orderdao =new OrderDAOImplementation();
		orderItemdao = new OrderItemDAOImplementation();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Cart cart=(Cart)session.getAttribute("cart");
		User user=(User)session.getAttribute("user");
		
		if(cart != null && user != null && !cart.getItems().isEmpty()) {
			String paymentMethod=request.getParameter("paymentMethod");
			String address=request.getParameter("address");
			
			//inserting item into orderitem table
			Order order=new Order();
			
			order.setUserId(user.getUserId());
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setOrderDate(new Date());
			order.setPaymentMode(paymentMethod);
			order.setStatus("Pending");
			
			double totalAmount=0;
			for(CartItem item : cart.getItems().values()) {
				totalAmount += item.getItemPrice()*item.getQuantity();
			}
			order.setTotalAmount(totalAmount);
			int orderId= orderdao.addOrder(order);
			//inserting item into orderitem table
			
			OrderItem orderItem = new OrderItem();
			for (CartItem cartItem : cart.getItems().values()) {

			    
			    orderItem.setOrderId(orderId);
			    orderItem.setMenuId(cartItem.getMenuId());
			    orderItem.setQuantity(cartItem.getQuantity());
			    orderItem.setPrice(cartItem.getItemPrice());
			    orderItem.setTotalPrice(cartItem.getItemPrice() * cartItem.getQuantity());

			    orderItemdao.addOrderItem(orderItem);  
			}
			session.removeAttribute("cart");
			session.setAttribute("order", order);
			response.sendRedirect("order_confirmed.jsp");
		}else {
			response.sendRedirect("cart.jsp");
		}
		
	}

}
