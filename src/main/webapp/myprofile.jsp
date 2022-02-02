<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="headerall.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My profile</title>
<link rel="stylesheet" href="css/myprofile.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"></script>

</head>
<body>
	<h2>My profile</h2>
	<jsp:useBean id="CustomerDao"
		class="com.petshopapp.daoimpl.CustomerDAO" />
	<c:set var="customer"
		value="${CustomerDao.customerDetails(customer.getCustomerId())}"></c:set>

	<table>
		<tbody>
			<tr>
				<td><img src="./Profile Picture/${customer.getImage()}"
					alt="user picture"></td>
				<td style="width: 600px;">
					<p>
					<pre>First Name </pre>: ${customer.getFirstName()}
					${customer.getLastName()}
					</p>
					<p>
					<pre>User Name </pre>: ${customer.getUserName()}
					</p>
					<p>
					<pre>Gender       </pre>: ${customer.getGender()}
					</p>
					<p>
					<pre>Email          </pre>: ${customer.getEmail()}
					</p>
					<p>
					<pre>Mobile        </pre>: ${customer.getNumber()}
					</p>
					<p>
					<pre>Address     </pre>: ${customer.getAddress()},
					${customer.getCity()} , ${customer.getPincode()}
					</p>
					<p>
					<pre>wallet         </pre>: Rs.${customer.getWallet()}
					</p>

				</td>
			</tr>
			<tr>
				<td colspan="3">
					<p>
						Update Image : <input type="file" id="image" name="image">
						<button type="button" id="ok" onclick="UpdateImage()">OK</button>
					</p>
				</td>
			</tr>

		</tbody>
	</table>

	<table id="updatetable">
		<tbody>
			<tr>
				<td id="headings" colspan="4" style="font-size: 30px;">Update
					Profile <i class="fas fa-user-edit"></i>
				</td>
			</tr>
			<tr>
				<form action="UpdateProfile">
					<td><label for="firstname">Firstname</label></td>
					<td><input type="text" name="firstname"
						value="${customer.getFirstName()}" pattern="[a-zA-Z]{3,20}"
						required></td>
					<td><label for="lastname">Lastname</label></td>
					<td><input type="text" name="lastname"
						value="${customer.getLastName()}" pattern="[a-zA-Z]{3,20}"
						required></td>
			</tr>
			<tr>
				<td><label for="username" UserName>Username</label></td>
				<td><input type="text" name="username" id="username"
					value="${customer.getUserName()}" pattern="[a-zA-Z0-9]{8,20}"
					required></td>
				<td><label for="gender">Gender</label></td>
				<td><select id="gender" name="gender">
						<option value="Male">male</option>
						<option value="Female">female</option>
						<option value="Others">others</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td><input type="text" name="email" id="email"
					value="${customer.getEmail()}"
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required></td>
				<td><label for="number">Mobile</label></td>
				<td><input type="text" name="number"
					value="${customer.getNumber()}" pattern="[6-9]{1}[0-9]{9}" required></td>
			</tr>
			<tr>
				<td><label for="password">Password</label></td>
				<td><input type="password" name="password" id="password"
					value="${customer.getPassword()}"
					pattern="[a-zA-Z0-9!@#$%^&*()_+]{8,20}" required></td>
				<td colspan="2"><input type="checkbox" onclick="showPassword()"
					style="position: relative; left: -80px; top: 12px;"></td>
				<td style="position: relative; left: -390px; top: 5px;">Show
					Password</td>
			</tr>
			<tr>
				<td>
					<button type="submit" id="update">Update Profile</button>
				</td>
				</form>
			</tr>
			<tr>
				<td id="headings" colspan="4"
					style="font-size: 30px; padding-top: 70px;">Update Address <i
					class="fas fa-user-edit"></i></td>
			</tr>

			<tr>
				<form action="UpdateAddress" method="get">
					<td><label for="address">Street</label></td>

					<td><input type="text" name="address" id="address"
						value="${customer.getAddress()}" pattern="[a-zA-Z,./0-9 ]{3,100}"
						required></td>

					<td><label for="city">City</label></td>

					<td><input type="text" name="city" id="city"
						value="${customer.getCity()}" pattern="[a-zA-Z]{3,30}" required></td>
			</tr>
			<tr>
				<td><label for="pincode">Pincode</label></td>
				<td><input type="text" name="pincode" id="pincode"
					pattern="[6]{1}[0-9]{5}" value="${customer.getPincode()}" required>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><button type="submit" id="update1">Update Address</button></td>
				</from>
			</tr>

			<tr>
				<td id="headings" colspan="4"
					style="font-size: 30px; padding-top: 70px;">Update Wallet <i
					class="fas fa-wallet"></i></td>
			</tr>
			<tr>
				<td><label for="wallet">Add Amount</label></td>
				<td><input type="number" name="wallet" id="updatewallet1"
					min="0" value="0" required></td>
				<td><button id="updatewallet" onclick="UpdateWallet()"
						type="button">Add</button></td>
			</tr>
			<tr>
				<td><a href="Logout">
						<button type="button" id="logout">Logout</button>
				</a></td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript" src="js/myprofile.js"></script>

</body>
</html>