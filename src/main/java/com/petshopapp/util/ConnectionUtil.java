package com.petshopapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
			
		public static Connection getDbConnect() throws SQLException, ClassNotFoundException {
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			 return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
				  
	}

		private ConnectionUtil() {
			super();
		}
}
