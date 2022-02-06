package com.petshopapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.petshopapp.encrypt.EncryptPassword;
import com.petshopapp.logger.Logger;

public class ConnectionUtil {

	public static Connection getDbConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String userName = "system";
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", userName,
					EncryptPassword.decrypt());
		} catch (ClassNotFoundException | SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} catch (Exception e1) {
			Logger.printStackTrace(e1);
			Logger.runTimeException(e1.getMessage());
		}
		return null;
	}

	private ConnectionUtil() {
		super();
	}
}
