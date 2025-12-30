package com.fooddelivery.daoimplemets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fooddelivery.dao.OrderItemDAO;
import com.fooddelivery.model.OrderItem;
import com.fooddelivery.dao.OrderItemDAO;
import com.fooddelivery.utility.DBConnection;

public class OrderItemDAOImplementation implements OrderItemDAO{
	private static final String INSERT_ORDER_ITEM_QUERY =
		    "INSERT INTO OrderItem (orderId, menuId, price, quantity, totalPrice) VALUES (?, ?, ?, ?, ?)";

	private static final String GET_ALL_ORDERITEMS_QUERY="SELECT * FROM `OrderItem`";
	private static final String DELETE_ORDERITEM_QUERY="DELETE FROM `OrderItem` WHERE `orderItemId=?";
	private static final String UPDATE_ORDERITEM_QUERY="UPDATE `OrderItem` SET `quality`=?  `totalPrice` =?  from `OrderItem` where orderItemId=?" ;
	private static final String GET_ORDERITEM_QUERY="SELECT * FROM `OrderItem` WHERE `orderItemId`=?";

	@Override

	public void addOrderItem(OrderItem item) {
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(INSERT_ORDER_ITEM_QUERY)) {

	        stmt.setInt(1, item.getOrderId());
	        stmt.setInt(2, item.getMenuId());
	        stmt.setDouble(3, item.getPrice());
	        stmt.setInt(4, item.getQuantity());
	        stmt.setDouble(5, item.getTotalPrice());

	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public OrderItem getOrderItem(int orderItemId) {
		OrderItem orderItem=null;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(GET_ORDERITEM_QUERY);
			prepareStmt.setInt(1, orderItemId);
			ResultSet resultSet=prepareStmt.executeQuery();
			orderItem=extractOrderItem(resultSet);

		}catch (SQLException e) {
			e.printStackTrace();
		}

		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(UPDATE_ORDERITEM_QUERY);
			prepareStmt.setInt(1, orderItem.getQuantity());
			prepareStmt.setDouble(2, orderItem.getTotalPrice());

			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItemId(int orderItemId) {

		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(DELETE_ORDERITEM_QUERY);
			prepareStmt.setInt(1, orderItemId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderItem> getAllOrderItems() {
		List<OrderItem> OrderItemList=new ArrayList<OrderItem>();
		try {
			Connection connection=DBConnection.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet resultSet=stmt.executeQuery(GET_ALL_ORDERITEMS_QUERY);

			while(resultSet.next()) {
				OrderItem orderItem=extractOrderItem(resultSet);
				OrderItemList.add(orderItem);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return OrderItemList;
	}

	private OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
		int quality=resultSet.getInt("quality");
		float totalPrice=resultSet.getFloat("totalPrice");
		OrderItem orderItem=new OrderItem(0, 0, 0, quality, totalPrice);
		return orderItem;
	}

}
