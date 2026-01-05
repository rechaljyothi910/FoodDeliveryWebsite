package com.fooddelivery.daoimplemets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fooddelivery.dao.MenuDAO;
import com.fooddelivery.model.Menu;
import com.fooddelivery.model.User;
import com.fooddelivery.utility.DBConnection;

public class MenuDAOImplementation implements MenuDAO{
	
	private static final String INSERT_ITEM_QUERY="Insert into `menu` (`itemName`,`description`,`price`,`isAvailable`,`imagePath`) values(?,?,?,?,?)";
	private static final String GET_MENU_QUERY="SELECT * FROM `menu` WHERE `menuId`=?";
	private static final String DELETE_ITEM_QUERY="DELETE FROM `menu` WHERE `menuId`=?";
	private static final String UPDATE_ITEM_QUERY="UPDATE `menu` SET `itemName`=?  `description` =?  `price`=? `isAvailable`=?  `imagePath`=?  from `menu` where `menuId`=?" ;
	private static final String GET_RESTAURANTITEM_QUERY="SELECT * FROM `menu` WHERE `restaurantId`=?";
	

	@Override
	public void addItem(Menu menu) {
Connection connection=DBConnection.getConnection();
		
		try(PreparedStatement prepareStmt=connection.prepareStatement(INSERT_ITEM_QUERY);) {

			prepareStmt.setString(1,menu.getItemName());
			prepareStmt.setString(2,menu.getDescription());
			prepareStmt.setDouble(3,menu.getPrice());
			prepareStmt.setBoolean(4,menu.getAvailable());
			prepareStmt.setString(5,menu.getImagePath());
			

			int res=prepareStmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public List<Menu> getRestaurantItem(int restaurantId) {
//		List<Menu> menu=new ArrayList<Menu>();
//		try {
//			Connection connection=DBConnection.getConnection();
//			PreparedStatement prepareStmt=connection.prepareStatement(GET_RESTAURANTITEM_QUERY);
//			prepareStmt.setInt(1, restaurantId);
//			ResultSet resultSet=prepareStmt.executeQuery();
//			while(resultSet.next()) {
//				Menu item=extractItem(resultSet);
//				menu.add(item);
//			}

//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return menu;
//	}
	@Override
	public List<Menu> getRestaurantItem(int restaurantId) {
		List<Menu> menu=new ArrayList<Menu>();
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(GET_RESTAURANTITEM_QUERY);
			prepareStmt.setInt(1, restaurantId);
			ResultSet resultSet=prepareStmt.executeQuery();
			while(resultSet.next()) {
				Menu item=extractItem(resultSet);
				menu.add(item);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}

		return menu;
	}

	private Menu extractItem(ResultSet rs) throws SQLException {

	    int menuId = rs.getInt("menuId");
	    int restaurantId = rs.getInt("restaurantId");
	    String itemName = rs.getString("itemName");
	    String description = rs.getString("description");
	    double price = rs.getDouble("price");
	    boolean isAvailable = rs.getBoolean("isAvailable");
	    String imagePath = rs.getString("imagePath");

	    return new Menu(menuId, restaurantId, itemName, description, price, isAvailable, imagePath);
	}


	@Override
	public void updateItem(Menu menu) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(UPDATE_ITEM_QUERY);
			prepareStmt.setString(1, menu.getItemName());
			prepareStmt.setString(2, menu.getDescription());
			prepareStmt.setDouble(3, menu.getPrice());
			prepareStmt.setBoolean(4, menu.getAvailable());
			prepareStmt.setString(5, menu.getImagePath());
			

			prepareStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteItem(int menuId) {
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(DELETE_ITEM_QUERY);
			prepareStmt.setInt(1, menuId);
			prepareStmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId){
		Menu menu=null;
		try {
			Connection connection=DBConnection.getConnection();
			PreparedStatement prepareStmt=connection.prepareStatement(GET_MENU_QUERY);
			prepareStmt.setInt(1, menuId);
			ResultSet resultSet=prepareStmt.executeQuery();
			if (resultSet.next()) {
	            
	            menu = extractItem(resultSet);
	        }
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}
	

}
