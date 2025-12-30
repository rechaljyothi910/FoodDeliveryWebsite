package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.Restaurant;

public interface RestaurentDAO {

	void addRestaurant(Restaurant restaurant);
	Restaurant getRestaurant(int restaurantId);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
	List<Restaurant> getAllRestaurants();

}
