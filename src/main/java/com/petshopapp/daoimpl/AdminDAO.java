package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.petshopapp.model.Admin;
import com.petshopapp.util.ConnectionUtil;

public class AdminDAO {

     // Admin profile Details
	    public Admin show(String userName) throws SQLException, ClassNotFoundException {
	    ConnectionUtil connectionUtil = new ConnectionUtil();
		Connection con = connectionUtil.getDbConnect();
		Admin admin = null;
		String query = "select admin_id,admin_firstname,admin_lastname,admin_username,"
				+ "admin_password,admin_email,admin_number,admin_registerdate "
				+ "from admin_details where admin_username=?";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		preparedStatement.setString(1, userName);
		ResultSet re = preparedStatement.executeQuery();
		while (re.next()) {
			admin = new Admin(re.getInt(1), re.getString(2), re.getString(3), re.getString(4), re.getString(4),
					re.getString(6), re.getLong(7), re.getDate(8));
		}
		return admin;
	}

}
