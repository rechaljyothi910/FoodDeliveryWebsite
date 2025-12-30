package com.fooddelivery.model;

public class CartItem {
	private int menuId;
	private int restaurantId;
	private String itemName;
	private double itemPrice;
	private int quantity;
	private String restaurantName;
	public CartItem(){
		
	}
	public CartItem(int menuId, int restaurantId,String itemName, int quantity, double itemPrice) {
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
	}
	public String getRestaurantName() {
	    return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
	    this.restaurantName = restaurantName;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
