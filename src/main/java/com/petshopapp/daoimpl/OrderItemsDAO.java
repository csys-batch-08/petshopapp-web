package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.util.ConnectionUtil;

public class OrderItemsDAO {

	String query = "";
	ResultSet resultSet = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	OrderItems orderitems = new OrderItems();
	List<OrderItems> orderItemList = new ArrayList<OrderItems>();

	// Commit for every DML operation
	public void commit() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "commit";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// insert order item
	public void insertOrderItems(OrderItems orditm) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "insert into order_items (order_id,pet_id,quantity,unit_price,total_price) values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orditm.getOrders().getOrderId());
			preparedStatement.setInt(2, orditm.getPet().getPetId());
			preparedStatement.setInt(3, orditm.getQuantity());
			preparedStatement.setDouble(4, orditm.getUnitPrice());
			preparedStatement.setDouble(5, orditm.getTotalPrice());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	// Cancel Order item
	public void cancelOrderitem(OrderItems ord) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "delete from order_items where item_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, ord.getOrders().getOrderId());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	// Show My order items List
	public List<OrderItems> showMyOrdersItemsList(Customers cus) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select oi.order_id,oi.pet_id,p.pet_name,oi.quantity,oi.unit_price,oi.total_price,o.order_status,o.order_date,p.pet_image "
					+ "from order_items oi inner join orders o on oi.order_id=o.order_id inner join pet_details p on oi.pet_id=p.pet_id "
					+ "where o.customer_id=? order by o.order_id desc";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, cus.getCustomerId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderitems = new OrderItems();
				orderitems.getOrders().setOrderId(resultSet.getInt(1));
				orderitems.getPet().setPetId(resultSet.getInt(2));
				orderitems.getPet().setPetName(resultSet.getString(3));
				orderitems.setQuantity(resultSet.getInt(4));
				orderitems.setUnitPrice(resultSet.getDouble(5));
				orderitems.setTotalPrice(resultSet.getDouble(6));
				orderitems.getOrders().setOrderStatus(resultSet.getString(7));
				orderitems.getOrders().setOrderDate(resultSet.getDate(8));
				orderitems.getPet().setPetImage(resultSet.getString(9));
				orderItemList.add(orderitems);
			}

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return orderItemList;
	}

	// Get Current Order Item details
	public List<OrderItems> getCurrentOrderItemDetails(int orderId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			String query = "select item_id,order_id,pet_id,quantity,unit_price,total_price from order_items where order_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderitems = new OrderItems(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6));
				orderItemList.add(orderitems);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return orderItemList;
	}
}
