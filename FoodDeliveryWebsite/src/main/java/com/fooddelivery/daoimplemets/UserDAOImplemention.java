package com.fooddelivery.daoimplemets;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fooddelivery.dao.UserDAO;
import com.fooddelivery.model.User;
import com.fooddelivery.utility.DBConnection;

public class UserDAOImplemention implements UserDAO {

	private static final String INSERT_USER_QUERY="Insert into `user` (`name`,`password`,`email`,`phone`,`address`) values(?,?,?,?,?)";
	private static final String GET_ALL_USERS_QUERY="SELECT * FROM `user`";
	private static final String DELETE_USER_QUERY="DELETE FROM user WHERE userId=?";
	private static final String UPDATE_USER_QUERY="UPDATE user SET name=?, password=?, email=?, phone=?, address=? WHERE userId=?";
	private static final String GET_USER_QUERY="SELECT * FROM `user` WHERE `email`=? AND `password`=?";

	@Override
	public int addUser(User user) {
		int res=0;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(INSERT_USER_QUERY); 
			prepareStmt.setString(1, user.getName());
			prepareStmt.setString(2, user.getPassword());
			prepareStmt.setString(3, user.getEmail());
			prepareStmt.setString(4, user.getPhone());
			prepareStmt.setString(5, user.getAddress());

			res=prepareStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> userslist=new ArrayList<User>();
		try {
			Connection connection=DBConnection.getConnection();
			Statement stmt=connection.createStatement();
			ResultSet resultSet=stmt.executeQuery(GET_ALL_USERS_QUERY);

			while(resultSet.next()) {
				User user=extractUser(resultSet);
				userslist.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userslist;
	}
	
	User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
	    user.setUserId(rs.getInt("userId"));
	    user.setName(rs.getString("name"));
	    user.setPassword(rs.getString("password"));
	    user.setEmail(rs.getString("email"));
	    user.setPhone(rs.getString("phone"));
	    user.setAddress(rs.getString("address"));
	    return user;
	}
	
	@Override
	public void deleteUser(int userId) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(DELETE_USER_QUERY);
			prepareStmt.setInt(1, userId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String email, String password) {
		User user=null;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(GET_USER_QUERY);
			prepareStmt.setString(1, email);
			prepareStmt.setString(2, password);
			ResultSet resultSet=prepareStmt.executeQuery();
			if (resultSet.next()) {   
	            user = extractUser(resultSet);
	        }
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public void updateUser(User user) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(UPDATE_USER_QUERY);
			prepareStmt.setString(1, user.getName());
			prepareStmt.setString(2, user.getPassword());
			prepareStmt.setString(3, user.getEmail());
			prepareStmt.setString(4, user.getPhone());
			prepareStmt.setString(5, user.getAddress());
			prepareStmt.setInt(6, user.getUserId()); // Fixed: was setString(6, user.getRole())
			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ========== NEW METHODS FOR AUTHENTICATION ==========
	
	/**
	 * Validate user credentials (for login)
	 * This uses the existing getUser() method
	 */
	public User validateUser(String email, String password) {
		return getUser(email, password);
	}
	
	/**
	 * Create new user and return the generated userId
	 * Used for signup with auto-login
	 */
	public int createUser(User user) {
		int userId = 0;
		String query = "INSERT INTO `user` (`name`,`password`,`email`,`phone`,`address`) VALUES(?,?,?,?,?)";
		
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			prepareStmt.setString(1, user.getName());
			prepareStmt.setString(2, user.getPassword());
			prepareStmt.setString(3, user.getEmail());
			prepareStmt.setString(4, user.getPhone());
			prepareStmt.setString(5, user.getAddress());
			
			int rowsAffected = prepareStmt.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = prepareStmt.getGeneratedKeys();
				if (rs.next()) {
					userId = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	/**
	 * Check if email already exists (optional - for better UX)
	 */
	public boolean emailExists(String email) {
		boolean exists = false;
		String query = "SELECT COUNT(*) FROM `user` WHERE `email`=?";
		
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStmt = connection.prepareStatement(query);
			prepareStmt.setString(1, email);
			ResultSet rs = prepareStmt.executeQuery();
			
			if (rs.next()) {
				exists = rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
}