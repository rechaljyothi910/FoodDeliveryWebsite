package com.fooddelivery.daoimplemets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fooddelivery.dao.OrderDAO;
import com.fooddelivery.model.Order;
import com.fooddelivery.model.User;
import com.fooddelivery.utility.DBConnection;

public class OrderDAOImplementation implements OrderDAO {
	private static final String INSERT_ORDER_QUERY="Insert into `Order` (`userId`,`restaurantId`,`OrderDate`,`totalAmount`,`status`,`paymentMode`) values(?,?,?,?,?,?)";
	private static final String GET_ALL_ORDERS_QUERY="SELECT * FROM `Order`";
	private static final String DELETE_ORDER_QUERY="DELETE FROM `Order` WHERE `orderId=?";
	private static final String UPDATE_ORDER_QUERY="UPDATE `Order` SET `totalAmount`=?  `status` =?  `paymentMode`=?  from `Order` where orderId=?" ;
	private static final String GET_ORDER_QUERY="SELECT * FROM `Order` WHERE `orderId`=?";

	@Override
	
	public int addOrder(Order order) {
	    int orderId = 0;

	    Connection connection = DBConnection.getConnection();

	    try (PreparedStatement prepareStmt = connection.prepareStatement(
	            INSERT_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS)) {

	        prepareStmt.setInt(1, order.getUserId());
	        prepareStmt.setInt(2, order.getRestaurantId());
	        prepareStmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
	        prepareStmt.setDouble(4, order.getTotalAmount());
	        prepareStmt.setString(5, order.getStatus());
	        prepareStmt.setString(6, order.getPaymentMode());

	        prepareStmt.executeUpdate();

	        // 🔥 Get Auto Increment OrderId
	        ResultSet rs = prepareStmt.getGeneratedKeys();
	        if (rs.next()) {
	            orderId = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return orderId;
	}

	@Override
	public Order getOrder(int orderId) {
		Order order=null;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(GET_ORDER_QUERY);
			prepareStmt.setInt(1, orderId);
			ResultSet resultSet=prepareStmt.executeQuery();
			order=extractOrder(resultSet);

		}catch (SQLException e) {
			e.printStackTrace();
		}
		return  order;
	}

	private Order extractOrder(ResultSet resultSet) throws SQLException {
		int totalAmount=resultSet.getInt("totalAmount");
		String status=resultSet.getString("status");
		String paymentMode=resultSet.getString("paymentMode");
		
		Order order=new Order(0, 0, 0, null, totalAmount, status, paymentMode);
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(UPDATE_ORDER_QUERY);
			prepareStmt.setDouble(1, order.getTotalAmount());
			prepareStmt.setString(2, order.getStatus());
			prepareStmt.setString(3, order.getPaymentMode());

			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrder(int orderId) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(DELETE_ORDER_QUERY);
			prepareStmt.setInt(1, orderId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> userslist=new ArrayList<Order>();
		try {
			Connection connection=DBConnection.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet resultSet=stmt.executeQuery(GET_ALL_ORDERS_QUERY);

			while(resultSet.next()) {
				Order order=extractOrder(resultSet);
				userslist.add(order);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
