package com.petshopapp.dao;

import java.util.List;

import com.petshopapp.model.Customers;

public interface CustomerInterface {

	public void commit();
	
	public boolean insertNewCustomer(Customers cus);
	
	public void updateCustomerDetails(Customers customer);
	
	public void updateAddressDetails(Customers customer);
	
	public void updateCustomerStatus(Customers customer, String status);
	
	public String customerLoginValidation(Customers customer);
	
	public boolean validateUsername(Customers customer);
	
	public boolean validateEmail(Customers customer);
	
	public List<Customers> customersList();
	
	public Customers customerDetails(String userName);
	
	public Customers customerDetails(int customerId);
	
	public void updateCustomerProfileImage(Customers cus);
	
	public void updateCustomerWallet(Customers cus);
	
}
