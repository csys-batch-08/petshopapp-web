package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.petshopapp.model.Customers;
import com.petshopapp.util.ConnectionUtil;

public class CustomerDAO {
	
	String query = "";
	Connection connection;
	ResultSet resultSet=null;
	Customers customer = null;
	PreparedStatement preparedStatement = null;
	ConnectionUtil connectionUtil = new ConnectionUtil();
	List<Customers> customerList = new ArrayList<Customers>();

	// Commit for every DML operation
	public void commit() {
		try {
			connection = connectionUtil.getDbConnect();
			query = "commit";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	// Register new Customer
	public boolean insertNewCustomer(Customers cus) {
		Connection con;
		boolean register=true;
		try {
			con = connectionUtil.getDbConnect();
			query = "insert into customers(customer_firstname,customer_lastname,"
					+ "customer_username,customer_password,customer_email,customer_mobilenumber,customer_gender)\r\n"
					+ "values (?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, cus.getFirstName());
			preparedStatement.setString(2, cus.getLastName());
			preparedStatement.setString(3, cus.getUserName());
			preparedStatement.setString(4, cus.getPassword());
			preparedStatement.setString(5, cus.getEmail());
			preparedStatement.setLong(6, cus.getNumber());
			preparedStatement.setString(7, cus.getGender());
			int i=preparedStatement.executeUpdate();
			commit();
			if(i==1) {
				register=true;
			}
			else {
				register=false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
     return register;
	}

	
	// update Customer profile
	public void updateCustomerDetails(Customers customer) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "update Customers set customer_firstname=?,customer_lastname=?,"
					+ "customer_username=?,customer_password=?,customer_email=?,customer_mobilenumber=?,customer_gender=? where customer_id=?";
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Update Customer Address Details
	public void updateAddressDetails(Customers customer) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "update Customers set customer_address=?,customer_pincode=?,customer_city=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getAddress());
			preparedStatement.setInt(2, customer.getPincode());
			preparedStatement.setString(3, customer.getCity());
			preparedStatement.setInt(4, customer.getCustomerId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Update customer status
	public void updateCustomerStatus(Customers customer, String status) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "update Customers set status=? where customer_id=?";
            preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, customer.getCustomerId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	
	// Customer login validation 
	public String customerLoginValidation(Customers customer) {
		try {
			connection = connectionUtil.getDbConnect();
		    query = "select customer_firstname from customers where customer_username=? and customer_password=?";
            preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, customer.getUserName());
			preparedStatement.setString(2, customer.getPassword());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				customer.setFirstName(resultSet.getString(1));
				return "1" + resultSet.getString(1);
			} else if (true) {
				query = "select Admin_firstname from admin_details where admin_username=? and admin_password=?";
			    preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, customer.getUserName());
				preparedStatement.setString(2, customer.getPassword());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					customer.setFirstName(resultSet.getString(1));
					return "2" + resultSet.getString(1);
				}
			}		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// User name validation during register and update
	public boolean validateUsername(Customers customer) {
		boolean flag = true;
		try {
			connection = connectionUtil.getDbConnect();
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Email validation during register and update
	public boolean validateEmail(Customers customer) {
		boolean flag = true;
		try {
			connection = connectionUtil.getDbConnect();
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
			    resultSet= preparedStatement.executeQuery();
				if (resultSet.next()) {
					flag = false;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	// Show all customers
	public List<Customers> customersList() {
		try {
			connection = connectionUtil.getDbConnect();
			query = "select customer_id,customer_firstname,customer_lastname,"
					+ "customer_gender,customer_username,customer_password,"
					+ "customer_email,customer_mobilenumber,customer_wallet,"
					+ "customer_reg_date,customer_address,customer_pincode,"
					+ "customer_image,customer_city,status from customers";
			preparedStatement = connection.prepareStatement(query);
		    resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customers(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getLong(8), resultSet.getDouble(9),
						resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getString(14),
						resultSet.getString(15));
				customerList.add(customer);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return customerList;

	}

	// Customer details using user name
	public Customers customerDetails(String userName) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "select customer_id,customer_firstname,customer_lastname,"
					+ "customer_gender,customer_username,customer_password,"
					+ "customer_email,customer_mobilenumber,customer_wallet,"
					+ "customer_reg_date,customer_address,customer_pincode,"
					+ "customer_image,customer_city,status from customers "
					+ "where customer_username=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customers(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getLong(8), resultSet.getDouble(9),
						resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getString(14),
						resultSet.getString(15));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
 
	// Customers details using id
	public Customers customerDetails(int customerId) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "select customer_id,customer_firstname,customer_lastname,"
					+ "customer_gender,customer_username,customer_password,"
					+ "customer_email,customer_mobilenumber,customer_wallet,"
					+ "customer_reg_date,customer_address,customer_pincode,"
					+ "customer_image,customer_city,status from customers "
					+ "where Customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customer = new Customers(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
						resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getLong(8), resultSet.getDouble(9),
						resultSet.getDate(10), resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getString(14),
						resultSet.getString(15));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	// Update Customer Image
	public void updateCustomerProfileImage(Customers cus) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "update Customers set customer_image=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cus.getImage());
			preparedStatement.setInt(2, cus.getCustomerId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	//Update Wallet
	public void updateCustomerWallet(Customers cus) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "update Customers set customer_wallet=? where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, cus.getWallet());
			preparedStatement.setInt(2, cus.getCustomerId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
