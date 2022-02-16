package com.petshopapp.dao;

import com.petshopapp.model.Orders;

public interface OrdersDAO {
	
	public void insertOrder(Orders order);
	
	public void updateOrderStatus(Orders order);
	
	public int getCurrentOrderId();
	
	
}
