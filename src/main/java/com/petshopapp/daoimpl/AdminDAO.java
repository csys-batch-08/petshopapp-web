package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.petshopapp.logger.Logger;
import com.petshopapp.model.Admin;
import com.petshopapp.util.ConnectionUtil;

public class AdminDAO {
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
					+ "admin_password,admin_email,admin_number,admin_registerdate "
					+ "from admin_details where admin_username=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				admin = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(4), resultSet.getString(6), resultSet.getLong(7),
						resultSet.getDate(8));
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
		return admin;
	}
}
