package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.petshopapp.model.Orders;
import com.petshopapp.util.ConnectionUtil;




public class OrdersDAO{
	
	// To get connection from connection util
	String query="";
	ResultSet resultSet=null;
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	
	// commit every DML operation
	public void commit() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "commit";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			preparedStatement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
}
	//insert order 
	public void insertOrder(Orders ord) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "insert into orders(Customer_id,total_price) \r\n"
					+ "values(?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, ord.getCustomer().getCustomerId());
			preparedStatement.setDouble(2, ord.getTotalprice());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
        
	// Cancel order
	public void updateOrderStatus(Orders order)  {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update orders set order_status='cancelled' where order_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, order.getOrderId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
       
	// To get last orderId value to insert
	public int getCurrentOrderId() {
		int orderid=0;
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select max(order_id) from orders";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderid=resultSet.getInt(1);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
       return orderid;
	}	
}
