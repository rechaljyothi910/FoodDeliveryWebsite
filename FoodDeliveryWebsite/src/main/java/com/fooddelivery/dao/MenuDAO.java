package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.Menu;

public interface MenuDAO {
	void addItem(Menu menu);
	List<Menu> getRestaurantItem(int restaurantId);
	void updateItem(Menu menu);
	void deleteItem(int menuId);
	Menu getMenu(int menuId);
}
