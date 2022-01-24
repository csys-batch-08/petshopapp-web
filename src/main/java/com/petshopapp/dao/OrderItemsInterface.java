package com.petshopapp.dao;

import java.util.List;

import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;

public interface OrderItemsInterface {
  
	public void insert(OrderItems orditm);
	
	public void delete(OrderItems ord);
	
	public List<OrderItems> showMyOrders(Customers cus);
	
	public List<OrderItems> getCurrentOrder(int orderId);
	
	
}
