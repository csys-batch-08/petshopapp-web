package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.petshopapp.dao.AdminInterface;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Admin;
import com.petshopapp.util.ConnectionUtil;

public class AdminDAO implements AdminInterface{
	/**
	 * this method is used to get Admin details
	 */
	public Admin show(String userName) {
		Connection connection = null;
		Admin admin = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getDbConnect();
			String query = "select admin_id,admin_firstname,admin_lastname,admin_username,"
					+ "admin_email,admin_number,admin_registerdate "
					+ "from admin_details where admin_username=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				admin = new Admin(resultSet.getInt("admin_id"), resultSet.getString("admin_firstname"), 
						resultSet.getString("admin_lastname"),resultSet.getString("admin_username"), 
						resultSet.getString("admin_email"), resultSet.getLong("admin_number"),
						resultSet.getDate("admin_registerdate"));
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return admin;
	}
}
