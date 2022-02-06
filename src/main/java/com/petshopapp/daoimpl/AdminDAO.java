package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.petshopapp.model.Admin;
import com.petshopapp.util.ConnectionUtil;

public class AdminDAO {

	// Admin profile Details
	public Admin show(String userName) {
		Connection connection;
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
			e.printStackTrace();
		} finally {

			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return admin;
	}

}
