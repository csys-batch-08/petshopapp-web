package com.petshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.petshopapp.logger.Logger;
import com.petshopapp.model.CartItems;
import com.petshopapp.model.Customers;
import com.petshopapp.util.ConnectionUtil;

public class CartItemsDAO {

	// Instance fields for methods
	String query = "";
	Connection connection;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	CartItems cartItem = new CartItems();
	List<CartItems> cartList = new ArrayList<CartItems>();

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
	 * this method is used to insert data into cart items table
	 */
	public void insertCartItem(CartItems cartit) {
		Connection con;
		try {
			con = ConnectionUtil.getDbConnect();
			query = "insert into cart_items(pet_id,customer_id,quantity,unit_price,total_price) values(?,?,?,?,?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, cartit.getPet().getPetId());
			preparedStatement.setInt(2, cartit.getCustomer().getCustomerId());
			preparedStatement.setInt(3, cartit.getQuantity());
			preparedStatement.setDouble(4, cartit.getUnitPrice());
			preparedStatement.setDouble(5, cartit.getTotalPrice());
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
	 * this method is used to update cart item quantity
	 */
	public void updateCartItemQuantity(int itemId, int qty) {
		query = "update cart_items set quantity=? where item_id=?";
		try {
			connection = ConnectionUtil.getDbConnect();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, qty);
			preparedStatement.setInt(2, itemId);
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
	 * this method is used to delete data in the cart items table
	 */
	public void deleteCartItem(int itemId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "delete from cart_items where item_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
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
	 * this method is used to get cart items for login customer.
	 */
	public List<CartItems> showAllCartItems(Customers customer) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select cart_items.item_id,cart_items.pet_id,cart_items.customer_id,cart_items.quantity,"
					+ "cart_items.unit_price,cart_items.total_price,pet_details.pet_type,"
					+ "pet_details.pet_name,pet_details.pet_image,pet_details.available_qty from cart_items inner join pet_details "
					+ "on pet_details.pet_id=cart_items.pet_id where cart_items.customer_id=? order by "
					+ "cart_items.item_id";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, customer.getCustomerId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cartItem = new CartItems(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getDouble(5), resultSet.getDouble(6));
				cartItem.getPet().setPetType(resultSet.getString(7));
				cartItem.getPet().setPetName(resultSet.getString(8));
				cartItem.getPet().setPetImage(resultSet.getString(9));
				cartItem.getPet().setAvilableQty(resultSet.getInt(10));
				cartList.add(cartItem);
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
		return cartList;
	}

	/**
	 * this method is used to get particular cart item details
	 */
	public CartItems showCartItem(int itemId) {
		try {
			connection = ConnectionUtil.getDbConnect();
			query = "select item_id,pet_id,customer_id,quantity,unit_price,total_price from cart_items where item_id=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, itemId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cartItem = new CartItems(resultSet.getInt("item_id"), resultSet.getInt("pet_id"),
						resultSet.getInt("customer_id"), resultSet.getInt("quantity"),
						resultSet.getDouble("unit_price"), resultSet.getDouble("total_price"));
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
		return cartItem;
	}
}
