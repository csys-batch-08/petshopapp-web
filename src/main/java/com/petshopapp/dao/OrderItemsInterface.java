package com.petshopapp.dao;

import java.util.List;

import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;

public interface OrderItemsInterface {
	
	public void insertOrderItems(OrderItems orditm);
	
	public void cancelOrderitem(OrderItems ord);
	
	public List<OrderItems> showMyOrdersItemsList(Customers cus);
	
	public List<OrderItems> getCurrentOrderItemDetails(int orderId);
	
	
	
}
