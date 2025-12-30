package com.fooddelivery.model;

import java.util.*;

public class Cart {
	private Map<Integer,CartItem> cart;
	public Cart(){
		this.cart=new LinkedHashMap();
	}
	public void addCartItem(CartItem cartitem) {
		int menuId=cartitem.getMenuId();
		if(cart.containsKey(menuId)) {
			CartItem existArtItem=cart.get(menuId);
			existArtItem.setQuantity(existArtItem.getQuantity()+cartitem.getQuantity());
		}else {
			cart.put(menuId, cartitem);
		}
	}
	public void updateCartItem(int menuId ,int quantity) {
		if(cart.containsKey(menuId)) {
			if(quantity <= 0) {
				cart.remove(menuId);
			}else {
				cart.get(menuId).setQuantity(quantity);
			}
		}
		
	}
	public void removeCartItem(int menuId) {
		cart.remove(menuId);
	}
	public Map<Integer , CartItem> getItems(){
		return cart;
	}
	public double getTotalPrice(){
		return cart.values().stream().mapToDouble(item -> item.getItemPrice()*item.getQuantity()).sum();
	}
}
