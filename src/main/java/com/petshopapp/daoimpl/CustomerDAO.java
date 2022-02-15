package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.dao.CustomerInterface;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.util.ConnectionUtil;

public class CustomerDAO implements CustomerInterface {

	// Instance object and variables for operation
	String query = "";
	String alldetails="select customer_id,customer_firstname,customer_lastname,"
			+ "customer_gender,customer_username,customer_password,"
			+ "customer_email,customer_mobilenumber,customer_wallet,"
			+ "customer_reg_date,customer_address,customer_pincode,"
			+ "customer_image,customer_city,status from customers";
	Connection connection;
	ResultSet resultSet = null;
	Customers customer = null;
	PreparedStatement preparedStatement = null;
	List<Customers> customerList = new ArrayList<>();

	/**
	 * load customer data
	 */
	public void getCustomer() {
		try {
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
		}
	}

	public void loadCustomerData(Customers customer){
		try {
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getUserName());
			preparedStatement.setString(4, customer.getPassword());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.setLong(6, customer.getNumber());
			preparedStatement.setString(7, customer.getGender());
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		}
		
	}
	
	/**
	 * this method is used to register new customer
	 */
	public boolean insertNewCustomer(Customers customer) {
		boolean register = true;
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "insert into customers(customer_firstname,customer_lastname,"
					+ "customer_username,customer_password,customer_email,customer_mobilenumber,"
					+ "customer_gender) values (?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			loadCustomerData(customer);
			int i = preparedStatement.executeUpdate();
			ConnectionUtil.commit(preparedStatement, connection);
			if (i == 1) {
				register = true;
			} else {
				register = false;
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
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
			loadCustomerData(customer);
			preparedStatement.setInt(8, customer.getCustomerId());
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
			ConnectionUtil.commit(preparedStatement, connection);
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
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
			ConnectionUtil.commit(preparedStatement, connection);
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
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
			ConnectionUtil.close(resultSet, preparedStatement, connection);
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
			ConnectionUtil.close(resultSet, preparedStatement, connection);
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
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return flag;
	}

	/**
	 * this method is used to get customer list
	 */
	public List<Customers> customersList() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query =  alldetails;
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			getCustomer();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return customerList;

	}

	/**
	 * this method is used to get customer details based on user name
	 */
	public Customers customerDetails(String userName) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = alldetails + " where customer_username=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			getCustomer();
			
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return customer;
	}

	/**
	 * this method is used to get customer details using customer id
	 */
	public Customers customerDetails(int customerId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = alldetails + "where Customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			getCustomer();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return customer;
	}

	/**
	 * this method is used to update customer image
	 */
	public void updateCustomerProfileImage(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set customer_image=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getImage());
			preparedStatement.setInt(2, customer.getCustomerId());
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
	 * this method is used to update customer wallet
	 */
	public void updateCustomerWallet(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Customers set customer_wallet=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, customer.getWallet());
			preparedStatement.setInt(2, customer.getCustomerId());
			preparedStatement.executeUpdate();
			ConnectionUtil.commit(preparedStatement, connection);
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}
}
