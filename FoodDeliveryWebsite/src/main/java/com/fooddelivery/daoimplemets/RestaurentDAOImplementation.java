package com.fooddelivery.daoimplemets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fooddelivery.dao.RestaurentDAO;
import com.fooddelivery.model.Restaurant;
import com.fooddelivery.model.User;
import com.fooddelivery.utility.DBConnection;

public class RestaurentDAOImplementation implements RestaurentDAO{
	private static final String INSERT_RESTAURENT_QUERY="INSERT INTO restaurant (name, phone, address, cusineType, isActive, eta, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_RESTAURENT_QUERY="SELECT * FROM `restaurant`";
	private static final String DELETE_RESTAURENT_QUERY="DELETE FROM `restaurant` WHERE `restaurantId=?";
	private static final String UPDATE_RESTAURENT_QUERY="UPDATE restaurant SET name = ?, phone = ?, address = ?, cusineType = ?, isActive = ?, eta = ?, imagePath = ? WHERE restaurantId = ?";
	private static final String GET_RESTAURENT_QUERY="SELECT * FROM `restaurant` WHERE `restaurantId`=?";
	
	@Override
	public void addRestaurant(Restaurant restaurant) {
		Connection connection=DBConnection.getConnection();
		try {
			PreparedStatement prepStmt=connection.prepareStatement(INSERT_RESTAURENT_QUERY);
			prepStmt.setString(1, restaurant.getName());
			prepStmt.setString(2, restaurant.getPhone());
			prepStmt.setString(3, restaurant.getAddress());
			prepStmt.setString(4, restaurant.getCusineType());
			prepStmt.setInt(5,restaurant.getActive());
			prepStmt.setString(6, restaurant.getEta());
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateRestaurant(Restaurant restaurant) {
		Connection connection=DBConnection.getConnection();
		try {
			PreparedStatement prepStmt=connection.prepareStatement(UPDATE_RESTAURENT_QUERY);
			prepStmt.setString(1, restaurant.getName());
			prepStmt.setString(2, restaurant.getPhone());
			prepStmt.setString(3, restaurant.getAddress());
			prepStmt.setString(4, restaurant.getCusineType());
			prepStmt.setInt(5,restaurant.getActive());
			prepStmt.setString(6, restaurant.getEta());
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void deleteRestaurant(int restaurantId) {
		Connection connection=DBConnection.getConnection();
		try {
			PreparedStatement prepStmt=connection.prepareStatement(DELETE_RESTAURENT_QUERY);
			prepStmt.setInt(1, restaurantId);
			prepStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Connection connection=DBConnection.getConnection();
		Restaurant restaurant=null;
		try {
			PreparedStatement prepStmt=connection.prepareStatement(GET_RESTAURENT_QUERY);
			prepStmt.setInt(1, restaurantId);
			ResultSet resultSet=prepStmt.executeQuery();
			restaurant=extractRestaurant(resultSet);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return restaurant;
	}
	private Restaurant extractRestaurant(ResultSet resultSet) throws SQLException {
	    int restaurantId = resultSet.getInt("restaurantId");
	    String name = resultSet.getString("name");
	    String phone = resultSet.getString("phone");
	    String address = resultSet.getString("address");
	    String cusineType = resultSet.getString("cusineType");
	    int isActive = resultSet.getInt("isActive");
	    String eta = resultSet.getString("eta");
	    String imagePath = resultSet.getString("imagePath");   // ✅ FIX

	    Restaurant restaurant = new Restaurant(
	        restaurantId, name, phone, address, 
	        resultSet.getFloat("rating"),  // also missing earlier
	        cusineType, isActive, eta, 
	        resultSet.getInt("adminUserId"),
	        imagePath                       // ✅ FIX
	    );

	    return restaurant;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurentlist=new ArrayList<Restaurant>();
		try {
			Connection connection=DBConnection.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet resultSet=stmt.executeQuery(GET_ALL_RESTAURENT_QUERY);

			while(resultSet.next()) {
				Restaurant restaurant=extractRestaurant(resultSet);
				restaurentlist.add(restaurant);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurentlist;
		
	}
	
}
