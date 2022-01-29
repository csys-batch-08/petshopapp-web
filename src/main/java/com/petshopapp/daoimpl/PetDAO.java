package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.petshopapp.model.*;
import com.petshopapp.util.*;


public class PetDAO {
	
	String query = "";
	ResultSet resultSet = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PetDetails pet = new PetDetails();
	ConnectionUtil connectionUtil = new ConnectionUtil();
	List<PetDetails> petList = new ArrayList<PetDetails>();
	SimpleDateFormat formeter = new SimpleDateFormat("dd-mm-yyyy");

	public void commit() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "commit";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());			
		}
	}
	


	public List<PetDetails> getPetList() {
		try {
			if(!(resultSet == null)) {
			while (resultSet.next()) {

				pet = new PetDetails(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getDouble(9), resultSet.getString(10),
						resultSet.getString(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getDate(14),
						resultSet.getInt(15));
				petList.add(pet);
			}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());	
		}
		
		return petList;
	}

	// insert pet_details
	public void insertPetDetails(PetDetails pet) {

		try {
			connection = ConnectionUtil.getDbConnect();
			query = "INSERT into pet_details(pet_type,pet_name,pet_gender,pet_dob,pet_Qty,pet_description,\r\n"
					+ "pet_color,pet_price,pet_image,customer_id,available_qty) values(?,?,?,?,?,?,?,?,?,?,?)";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pet.getPetType());
			preparedStatement.setString(2, pet.getPetName());
			preparedStatement.setString(3, pet.getPetGender());
			String dob = formeter.format(pet.getPetDob());
			preparedStatement.setString(4, dob);
			preparedStatement.setInt(5, pet.getPetQty());
			preparedStatement.setString(6, pet.getDescription());
			preparedStatement.setString(7, pet.getPetColor());
			preparedStatement.setDouble(8, pet.getPetprice());
			preparedStatement.setString(9, pet.getPetImage());
			preparedStatement.setInt(10, pet.getCustomer().getCustomerId());
			preparedStatement.setInt(11, pet.getAvilableQty());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
	}

	// Update pet_details
	public void updatePetDetails(PetDetails pet) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update pet_details set pet_type=?,pet_name=?,"
					+ "pet_gender=?,pet_dob=?,pet_Qty=?,pet_description=?,"
					+ "pet_color=?,pet_price=?,pet_image=?,customer_id=?," + "available_qty=? where pet_id=?";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pet.getPetType());
			preparedStatement.setString(2, pet.getPetName());
			preparedStatement.setString(3, pet.getPetGender());
			String dob = formeter.format(pet.getPetDob());
			preparedStatement.setString(4, dob);
			preparedStatement.setInt(5, pet.getPetQty());
			preparedStatement.setString(6, pet.getDescription());
			preparedStatement.setString(7, pet.getPetColor());
			preparedStatement.setDouble(8, pet.getPetprice());
			preparedStatement.setString(9, pet.getPetImage());
			preparedStatement.setInt(10, pet.getCustomer().getCustomerId());
			preparedStatement.setInt(11, pet.getPetQty());
			preparedStatement.setInt(12, pet.getPetId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
	}

	// To update pet Status admin approval
	public void updatePetStatus(int petId, String status, int adminId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update pet_details set status=?,admin_id=? where pet_id=?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, status);
			pstmt.setInt(2, adminId);
			pstmt.setInt(3, petId);
			pstmt.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
	}

	// to show all the approved pet details
	public List<PetDetails> showAllpetsDetails(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_gender,pet_dob,pet_qty,pet_description,pet_color,pet_price,pet_image,status,customer_id,admin_id,pet_registerdate,available_qty from pet_details where status='approved' and available_qty > 0 and customer_id not in(?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customer.getCustomerId());
			resultSet = preparedStatement.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
		return getPetList();
	}

	// to get particular pet data
	public PetDetails showCurrentPet(int petId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_gender,pet_dob,"
					+ "pet_qty,pet_description,pet_color,pet_price,pet_image,"
					+ "status,customer_id,admin_id,pet_registerdate,"
					+ "available_qty from pet_details where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, petId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new PetDetails(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getString(7),
						resultSet.getString(8), resultSet.getDouble(9), resultSet.getString(10),
						resultSet.getString(11), resultSet.getInt(12), resultSet.getInt(13), resultSet.getDate(14),
						resultSet.getInt(15));

				query = "select customer_firstname from customers where customer_id=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, resultSet.getInt(12));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					pet.getCustomer().setFirstName(resultSet.getString(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
		return pet;
	}

	// pet list to show admin
	public List<PetDetails> showNotApprovedPetList() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select PET_ID,PET_TYPE,PET_NAME,PET_GENDER,PET_DOB,PET_QTY,PET_DESCRIPTION,PET_COLOR,PET_PRICE,PET_IMAGE,\r\n"
					+ "STATUS,CUSTOMER_ID,ADMIN_ID,PET_REGISTERDATE,AVAILABLE_QTY from pet_details where status='Not approved'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
		return getPetList();
	}

	// My pet details for customer
	public List<PetDetails> showMypetdetails(int cusId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_gender,pet_dob,"
					+ "pet_qty,pet_description,pet_color,pet_price,pet_image,"
					+ "status,customer_id,admin_id,pet_registerdate,"
					+ "available_qty from pet_details where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, cusId);
			resultSet = preparedStatement.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
		return getPetList();
	}

	// search pet
	public List<PetDetails> searchPetDetails(String search, int customerId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_gender,"
					+ "pet_dob,pet_qty,pet_description,pet_color,pet_price,"
					+ "pet_image,status,customer_id,admin_id,pet_registerdate,"
					+ "available_qty from pet_details where (pet_type like '%"+search+"%' or pet_name like '%"+search+"%') "
					+ "and status='approved' and available_qty > 0 and customer_id not in("+customerId+")";
			preparedStatement = connection.prepareStatement(query);
		//	preparedStatement.setString(1, search);
		//	preparedStatement.setString(2, search);
		//	preparedStatement.setInt(3, customerId);
			resultSet = preparedStatement.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
		return getPetList();
	}

	// delete status to update
	public void delete(PetDetails pet) {
		try {
			connection = ConnectionUtil.getDbConnect();
			String query = "update pet_details set status='deleted' where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pet.getPetId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
	}

	// update available qty
	public void updatePetAvailableQuantity(PetDetails pet) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Pet_details set available_qty=? where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pet.getAvilableQty());
			preparedStatement.setInt(2, pet.getPetId());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
	}

	// get status
	public String getPetStatus(PetDetails pet) throws SQLException {
		String status = "";
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select status from Pet_details  where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pet.getPetId());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			status = resultSet.getString(1);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());	
		}
		
		return status;

	}
}
