package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.util.ConnectionUtil;

public class CustomerDAO {

	// Instance object and variables for operation
	String query = "";
	Connection connection;
	ResultSet resultSet = null;
	Customers customer = null;
	PreparedStatement preparedStatement = null;
	List<Customers> customerList = new ArrayList<Customers>();

	/**
	 * this method is used to commit every DML operation
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

	/**
	 * this method is used to register new customer
	 */
	public boolean insertNewCustomer(Customers cus) {
		Connection con;
		boolean register = true;
		try {
			con = ConnectionUtil.getDbConnect();
			query = "insert into customers(customer_firstname,customer_lastname,"
					+ "customer_username,customer_password,customer_email,customer_mobilenumber,"
					+ "customer_gender) values (?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, cus.getFirstName());
			preparedStatement.setString(2, cus.getLastName());
			preparedStatement.setString(3, cus.getUserName());
			preparedStatement.setString(4, cus.getPassword());
			preparedStatement.setString(5, cus.getEmail());
			preparedStatement.setLong(6, cus.getNumber());
			preparedStatement.setString(7, cus.getGender());
			int i = preparedStatement.executeUpdate();
			commit();
			if (i == 1) {
				register = true;
			} else {
				register = false;
			}
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
		return register;
	}

	/**
	 * this method is used to update customer profile
	 */
	public void updateCustomerDetails(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set customer_firstname=?,customer_lastname=?,"
					+ "customer_username=?,customer_password=?,customer_email=?,"
					+ "customer_mobilenumber=?,customer_gender=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getUserName());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setLong(6, customer.getNumber());
			preparedStatement.setString(7, customer.getGender());
			preparedStatement.setInt(8, customer.getCustomerId());
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
	 * this method is used to update customer address
	 */
	public void updateAddressDetails(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set customer_address=?,"
					+ "customer_pincode=?,customer_city=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getAddress());
			preparedStatement.setInt(2, customer.getPincode());
			preparedStatement.setString(3, customer.getCity());
			preparedStatement.setInt(4, customer.getCustomerId());
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
	 * this method is used to update customer status
	 */
	public void updateCustomerStatus(Customers customer, String status) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set status=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, customer.getCustomerId());
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
	 * this method is used to validation during login
	 */
	public String customerLoginValidation(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select customer_firstname from customers where customer_username=? and customer_password=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getUserName());
			preparedStatement.setString(2, customer.getPassword());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return "C" + resultSet.getString("customer_firstname");
			} else if (true) {
				query = "select Admin_firstname from admin_details where admin_username=? and admin_password=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, customer.getUserName());
				preparedStatement.setString(2, customer.getPassword());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					return "A" + resultSet.getString("Admin_firstname");
				}
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
		return null;
	}

	/**
	 * this method is used to ensure user name available or not
	 */
	public boolean validateUsername(Customers customer) {
		boolean flag = true;
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select customer_firstname from customers where customer_username=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getUserName());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flag = false;
			} else if (true) {
				query = "select admin_firstname from admin_details where admin_username=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, customer.getUserName());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					flag = false;

				}
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
		return flag;
	}

	/**
	 * this method is used to ensure email is available or not during registration
	 */
	public boolean validateEmail(Customers customer) {
		boolean flag = true;
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select customer_email from customers where customer_email=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getEmail());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flag = false;
			} else if (true) {
				query = "select admin_email from admin_details where admin_email=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, customer.getEmail());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					flag = false;
				}
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
		return flag;
	}

	/**
	 * this method is used to get customer list
	 */
	public List<Customers> customersList() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select customer_id,customer_firstname,customer_lastname,"
					+ "customer_gender,customer_username,customer_password,"
					+ "customer_email,customer_mobilenumber,customer_wallet,"
					+ "customer_reg_date,customer_address,customer_pincode,"
					+ "customer_image,customer_city,status from customers";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customers(resultSet.getInt("customer_id"), resultSet.getString("customer_firstname"),
						resultSet.getString("customer_lastname"), resultSet.getString("customer_gender"),
						resultSet.getString("customer_username"), resultSet.getString("customer_password"),
						resultSet.getString("customer_email"), resultSet.getLong("customer_mobilenumber"),
						resultSet.getDouble("customer_wallet"), resultSet.getDate("customer_reg_date"),
						resultSet.getString("customer_address"), resultSet.getInt("customer_pincode"),
						resultSet.getString("customer_image"), resultSet.getString("customer_city"),
						resultSet.getString("status"));
				customerList.add(customer);
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
		return customerList;

	}

	/**
	 * this method is used to get customer details based on user name
	 */
	public Customers customerDetails(String userName) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select customer_id,customer_firstname,customer_lastname,"
					+ "customer_gender,customer_username,customer_password,"
					+ "customer_email,customer_mobilenumber,customer_wallet,"
					+ "customer_reg_date,customer_address,customer_pincode,"
					+ "customer_image,customer_city,status from customers " + "where customer_username=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customers(resultSet.getInt("customer_id"), resultSet.getString("customer_firstname"),
						resultSet.getString("customer_lastname"), resultSet.getString("customer_gender"),
						resultSet.getString("customer_username"), resultSet.getString("customer_password"),
						resultSet.getString("customer_email"), resultSet.getLong("customer_mobilenumber"),
						resultSet.getDouble("customer_wallet"), resultSet.getDate("customer_reg_date"),
						resultSet.getString("customer_address"), resultSet.getInt("customer_pincode"),
						resultSet.getString("customer_image"), resultSet.getString("customer_city"),
						resultSet.getString("status"));
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
		return customer;
	}

	/**
	 * this method is used to get customer details using customer id
	 */
	public Customers customerDetails(int customerId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select customer_id,customer_firstname,customer_lastname,"
					+ "customer_gender,customer_username,customer_password,"
					+ "customer_email,customer_mobilenumber,customer_wallet,"
					+ "customer_reg_date,customer_address,customer_pincode,"
					+ "customer_image,customer_city,status from customers " + "where Customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customers(resultSet.getInt("customer_id"), resultSet.getString("customer_firstname"),
						resultSet.getString("customer_lastname"), resultSet.getString("customer_gender"),
						resultSet.getString("customer_username"), resultSet.getString("customer_password"),
						resultSet.getString("customer_email"), resultSet.getLong("customer_mobilenumber"),
						resultSet.getDouble("customer_wallet"), resultSet.getDate("customer_reg_date"),
						resultSet.getString("customer_address"), resultSet.getInt("customer_pincode"),
						resultSet.getString("customer_image"), resultSet.getString("customer_city"),
						resultSet.getString("status"));
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
		return customer;
	}

	/**
	 * this method is used to update customer image
	 */
	public void updateCustomerProfileImage(Customers cus) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set customer_image=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cus.getImage());
			preparedStatement.setInt(2, cus.getCustomerId());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}

	/**
	 * this method is used to update customer wallet
	 */
	public void updateCustomerWallet(Customers cus) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set customer_wallet=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, cus.getWallet());
			preparedStatement.setInt(2, cus.getCustomerId());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
	}

}
