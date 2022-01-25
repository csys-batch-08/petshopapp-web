package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.util.ConnectionUtil;

public class CartItemsDAO {
	
	String query = "";
	Connection connection;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	CartItems cartItem = new CartItems();
	ConnectionUtil connectionUtil = new ConnectionUtil();
	List<CartItems> cartList = new ArrayList<CartItems>();
	
	
	// Commit during DML operation
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

	// Insert Cart Items
	public void insertCartItem(CartItems cartit) {
		Connection con;

		try {
			con = connectionUtil.getDbConnect();
			query = "insert into cart_items(pet_id,customer_id,quantity,unit_price,total_price) values(?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, cartit.getPet().getPetId());
			preparedStatement.setInt(2, cartit.getCustomer().getCustomerId());
			preparedStatement.setInt(3, cartit.getQuantity());
			preparedStatement.setDouble(4, cartit.getUnitPrice());
			preparedStatement.setDouble(5, cartit.getTotalPrice());
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// To update cartItems Quantity
	public void updateCartItemQuantity(int itemId, int qty) {
		query = "update cart_items set quantity=? where item_id=?";
		try {
			connection = connectionUtil.getDbConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, qty);
			preparedStatement.setInt(2, itemId);
			preparedStatement.executeUpdate();
			commit();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Delete Cart item
	public void deleteCartItem(int itemId) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "delete from cart_items where item_id=" + itemId + "";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();
			commit();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	// Show My Cart Items
	public List<CartItems> showAllCartItems(Customers customer) {

		
		try {
			connection = connectionUtil.getDbConnect();
			query = "select ci.item_id,ci.pet_id,ci.customer_id,ci.quantity,ci.unit_price,ci.total_price,p.pet_type,p.pet_name,pet_image from cart_items ci inner join pet_details p on p.pet_id=ci.pet_id where ci.customer_id="
					+ customer.getCustomerId() + " order by ci.item_id";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cartItem = new CartItems(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6));
				cartItem.getPet().setPetType(resultSet.getString(7));
				cartItem.getPet().setPetName(resultSet.getString(8));
				cartItem.getPet().setPetImage(resultSet.getString(9));
				cartList.add(cartItem);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

	// Show Particular Cart Item
	public CartItems showCartItem(int itemId) {
		try {
			connection = connectionUtil.getDbConnect();
			query = "select item_id,pet_id,customer_id,quantity,unit_price,total_price from cart_items where item_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cartItem = new CartItems(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cartItem;
	}

}
