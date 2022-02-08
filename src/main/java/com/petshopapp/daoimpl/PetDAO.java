package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.dao.PetInterface;
import com.petshopapp.logger.Logger;
import com.petshopapp.model.Customers;
import com.petshopapp.model.PetDetails;
import com.petshopapp.util.ConnectionUtil;

public class PetDAO implements PetInterface{
	// Instance fields for methods
	String query = "";
	ResultSet resultSet = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PetDetails pet = new PetDetails();
	List<PetDetails> petList = new ArrayList<>();
	SimpleDateFormat formeter = new SimpleDateFormat("dd-mm-yyyy");

	/**
	 * this method is used to commit during DML operation
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
		}finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to insert new pet into database
	 */
	public void insertPetDetails(PetDetails pet) {

		try {
			connection = ConnectionUtil.getDbConnect();
			query = "INSERT into pet_details(pet_type,pet_name,pet_gender,pet_dob,pet_Qty," + "pet_description,\r\n"
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
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to insert update pet details in database
	 */
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
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to update pet status by admin
	 */
	public void updatePetStatus(int petId, String status, int adminId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update pet_details set status=?,admin_id=? where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, adminId);
			preparedStatement.setInt(3, petId);
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to get all pet details where approved pets and available
	 * pet quantity higher then 0 and the pet does't add by current login customer.
	 */
	public List<PetDetails> showAllpetsDetails(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_color,pet_price,pet_image,"
					+ "available_qty from pet_details where status='approved' and "
					+ "available_qty > 0 and customer_id not in(?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customer.getCustomerId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new PetDetails();
				pet.setPetId(resultSet.getInt("pet_id"));
				pet.setPetType(resultSet.getString("pet_type"));
				pet.setPetName(resultSet.getString("pet_name"));
				pet.setPetColor(resultSet.getString("pet_color"));
				pet.setPetprice(resultSet.getDouble("pet_price"));
				pet.setPetImage(resultSet.getString("pet_image"));
				pet.setAvilableQty(resultSet.getInt("available_qty"));
				petList.add(pet);
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return petList;
	}

	/**
	 * this method is used to get Particular pet details
	 */
	public PetDetails showCurrentPet(int petId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_gender,pet_dob,"
					+ "pet_qty,pet_description,pet_color,pet_price,pet_image,"
					+ "available_qty,customer_id from pet_details where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, petId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new PetDetails(resultSet.getInt("pet_id"), resultSet.getString("pet_type"),
						resultSet.getString("pet_name"), resultSet.getString("pet_gender"),
						resultSet.getDate("pet_dob"), resultSet.getInt("pet_qty"),
						resultSet.getString("pet_description"), resultSet.getString("pet_color"),
						resultSet.getDouble("pet_price"), resultSet.getString("pet_image"),
						resultSet.getInt("available_qty"),resultSet.getInt("customer_id"));
				query = "select customer_firstname from customers where customer_id=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, resultSet.getInt("customer_id"));
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					pet.getCustomer().setFirstName(resultSet.getString("customer_firstname"));
				}
			}

		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return pet;
	}

	/**
	 * this method is used to fetch all the not approved pet list
	 */
	public List<PetDetails> showNotApprovedPetList() {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_color,pet_price,pet_image,"
					+ "available_qty from pet_details where status='Not approved'";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new PetDetails();
				pet.setPetId(resultSet.getInt("pet_id"));
				pet.setPetType(resultSet.getString("pet_type"));
				pet.setPetName(resultSet.getString("pet_name"));
				pet.setPetColor(resultSet.getString("pet_color"));
				pet.setPetprice(resultSet.getDouble("pet_price"));
				pet.setPetImage(resultSet.getString("pet_image"));
				pet.setAvilableQty(resultSet.getInt("available_qty"));
				petList.add(pet);
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return petList;
	}

	/**
	 * this method is used to fetch current login customer pet details
	 */
	public List<PetDetails> showMypetdetails(int cusId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_name,pet_color,pet_price,pet_qty,pet_image,available_qty,status "
					+ "from pet_details where customer_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, cusId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new PetDetails();
				pet.setPetId(resultSet.getInt("pet_id"));
				pet.setPetName(resultSet.getString("pet_name"));
				pet.setPetColor(resultSet.getString("pet_color"));
				pet.setPetprice(resultSet.getDouble("pet_price"));
				pet.setPetImage(resultSet.getString("pet_image"));
				pet.setPetQty(resultSet.getInt("pet_qty"));
				pet.setAvilableQty(resultSet.getInt("available_qty"));
				pet.setStatus(resultSet.getString("status"));
				petList.add(pet);
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return petList;
	}

	/**
	 * this method is used to search pets in data base were available quantity
	 * higher then 0 and status equals to approved and pet should not posted by
	 * current login customer.
	 */
	public List<PetDetails> searchPetDetails(String search, int customerId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select pet_id,pet_type,pet_name,pet_color,pet_price,pet_image,available_qty from pet_details where (pet_type like ? or pet_name like ?) "
					+ "and status='approved' and available_qty > 0 and customer_id not in(?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,"%"+ search+"%");
			preparedStatement.setString(2, "%"+ search+"%");
			preparedStatement.setInt(3, customerId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				pet = new PetDetails();
				pet.setPetId(resultSet.getInt("pet_id"));
				pet.setPetType(resultSet.getString("pet_type"));
				pet.setPetName(resultSet.getString("pet_name"));
				pet.setPetColor(resultSet.getString("pet_color"));
				pet.setPetprice(resultSet.getDouble("pet_price"));
				pet.setPetImage(resultSet.getString("pet_image"));
				pet.setAvilableQty(resultSet.getInt("available_qty"));
				petList.add(pet);
			}
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return petList;
	}

	/**
	 * this method is used to update pet status to deleted
	 */
	public void delete(PetDetails pet) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update pet_details set status='deleted' where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pet.getPetId());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to update pet available quantity
	 */
	public void updatePetAvailableQuantity(PetDetails pet) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "update Pet_details set available_qty=? where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pet.getAvilableQty());
			preparedStatement.setInt(2, pet.getPetId());
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * this method is used to get particular pet status
	 */
	public String getPetStatus(PetDetails pet) {
		String status = "";
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select status from Pet_details  where pet_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, pet.getPetId());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			status = resultSet.getString("status");
		} catch (SQLException e) {
			Logger.printStackTrace(e);
			Logger.runTimeException(e.getMessage());
		} finally {
			ConnectionUtil.close(resultSet, preparedStatement, connection);
		}
		return status;
	}
}
