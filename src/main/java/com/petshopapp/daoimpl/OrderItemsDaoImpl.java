package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.dao.OrderItemsDAO;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.OrderItems;
import com.petshopapp.util.ConnectionUtil;

public class OrderItemsDaoImpl implements OrderItemsDAO {
	// Instance object and variables for operation
	String query = "";
	ResultSet resultSet = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	OrderItems orderitems = new OrderItems();
	List<OrderItems> orderItemList = new ArrayList<>();

	/**
	 * this method is used to insert values in order items
	 */
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
			ConnectionUtil.commit(preparedStatement, connection);
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to update status to cancel
	 */
	public void cancelOrderitem(OrderItems ord) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "delete from order_items where item_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, ord.getOrders().getOrderId());
			preparedStatement.executeUpdate();
			ConnectionUtil.commit(preparedStatement, connection);
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to get order item list
	 */
	public List<OrderItems> showMyOrdersItemsList(Customers cus) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select oi.order_id,oi.pet_id,p.pet_name,oi.quantity,oi.unit_price,oi.total_price,"
					+ "o.order_status,o.order_date,p.pet_image from order_items oi inner join orders o "
					+ "on oi.order_id=o.order_id inner join pet_details p on oi.pet_id=p.pet_id "
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
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return orderItemList;
	}

	/**
	 * this method is used to get particular order item details
	 */
	public List<OrderItems> getCurrentOrderItemDetails(int orderId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select item_id,order_id,pet_id,quantity,unit_price,"
					+ "total_price from order_items where order_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, orderId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderitems = new OrderItems(resultSet.getInt("item_id"), resultSet.getInt("order_id"),
						resultSet.getInt("pet_id"), resultSet.getInt("quantity"), resultSet.getDouble("unit_price"),
						resultSet.getDouble("total_price"));
				orderItemList.add(orderitems);
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);		}
		return orderItemList;
	}
}
