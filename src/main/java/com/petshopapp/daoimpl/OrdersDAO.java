package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.petshopapp.logger.Logger;
import com.petshopapp.model.Orders;
import com.petshopapp.util.ConnectionUtil;

public class OrdersDAO {
	// Instance object and variables for operation
	String query = "";
	ResultSet resultSet = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	/**
	 * this method is used to commit during DML operation
	 */
	public void commit() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "commit";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
		}
	}

	// insert order
	/**
	 * this method is used to insert order data into order table
	 */
	public void insertOrder(Orders order) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "insert into orders(Customer_id,total_price) values(?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, order.getCustomer().getCustomerId());
			preparedStatement.setDouble(2, order.getTotalprice());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
		}
	}

	/**
	 * this method is used to update order status
	 */
	public void updateOrderStatus(Orders order) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update orders set order_status='cancelled' where order_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, order.getOrderId());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
		}
	}

	/**
	 * this method is used to fetch last order id
	 */
	public int getCurrentOrderId() {
		int orderid = 0;
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select max(order_id) from orders";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				orderid = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				Logger.printStackTrace(e);
				Logger.runTimeException(e.getMessage());
			}
		}
		return orderid;
	}
}
