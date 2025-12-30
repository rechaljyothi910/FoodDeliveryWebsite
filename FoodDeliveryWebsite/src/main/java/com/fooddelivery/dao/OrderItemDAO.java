package com.fooddelivery.dao;

import java.util.List;

import com.fooddelivery.model.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderItemId);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItemId);
	void deleteOrderItemId(int orderItemId);
	List<OrderItem> getAllOrderItems();
}
