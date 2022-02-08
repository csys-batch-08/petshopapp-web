<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="keywords" content="Petshop,pets,animals">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My profile</title>
<link rel="stylesheet" href="./assets/css/header.css"></link>
<link rel="stylesheet" href="./assets/css/myprofile.css"></link>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet">
</head>
<body>
	<h2>My profile</h2>
	<jsp:useBean id="CustomerDao"
		class="com.petshopapp.daoimpl.CustomerDAO" />
	<c:set var="customer"
		value="${CustomerDao.customerDetails(customer.getCustomerId())}"></c:set>
	<div id="data">
		<div id="image">
			<img src="./assets/images/profile picture/${customer.getImage()}"
				alt="user picture">
		</div>
		<div id="divcontent">
			<p>Name</p>
			<p>User Name</p>
			<p>Gender</p>
			<p>Email</p>
			<p>Mobile</p>
			<p>Address</p>
			<p>Wallet</p>
		</div>
		<div id="divdata">
			<p>: ${customer.getFirstName()} ${customer.getLastName()}</p>
			<p>: ${customer.getUserName()}</p>
			<p>: ${customer.getGender()}</p>
			<p>: ${customer.getEmail()}</p>
			<p>: ${customer.getNumber()}</p>
			<p>: ${customer.getAddress()}, ${customer.getCity()} ,
				${customer.getPincode()}</p>
			<p>: Rs.${customer.getWallet()}0</p>
		</div>
		<div>
			<label for="image">Update Image :</label> <input type="file"
				id="imagepath" name="image">
			<button type="button" id="ok" onclick="UpdateImage()">Update
				Image</button>
		</div>
	</div>
	<table>
		<tr>
			<th id="headings" colspan="4" style="font-size: 30px;">Update
				Profile <i class="fas fa-user-edit"></i>
			</th>
		</tr>
		<tbody>
			<tr>
				<form action="UpdateProfile">
					<td><label for="firstname">Firstname</label> <input
						type="text" name="firstname" id="firstname"
						value="${customer.getFirstName()}" pattern="[a-zA-Z]{3,20}"
						required></td>
					<td><label for="lastname" id="lastname">Lastname</label> <input
						type="text" name="lastname" value="${customer.getLastName()}"
						pattern="[a-zA-Z]{3,20}" required></td>
			</tr>
			<tr>
				<td><label for="username">Username</label> <input type="text"
					name="username" id="username" onchange="validateUsername()"
					value="${customer.getUserName()}" pattern="[a-zA-Z0-9]{8,20}"
					required></td>
				<td><label for="gender">Gender</label> <select id="gender"
					name="gender" required>
						<option value="Male">male</option>
						<option value="Female">female</option>
						<option value="Others">others</option>
				</select></td>
			</tr>
			<tr>
				<td><label for="email">Email</label> <input type="text"
					name="email" id="email" onchange="validateEmail()"
					value="${customer.getEmail()}"
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required></td>
				<td><label for="number">Mobile</label> <input type="text"
					name="number" id="number" value="${customer.getNumber()}"
					pattern="[6-9]{1}[0-9]{9}" required></td>
			</tr>
			<tr>
				<td><label for="password">Password</label> <input
					type="password" name="password" id="password"
					value="${customer.getPassword()}"
					pattern="[a-zA-Z0-9!@#$%^&*()_+]{8,20}" required></td>
				<td><input type="checkbox" onclick="showPassword()"
					id="showpassword"><label for="showpassword">Show Password</label></td>
				
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
					<td><label for="address">Street</label> <input type="text"
						name="address" id="address" value="${customer.getAddress()}"
						pattern="[a-zA-Z,./0-9 ]{3,100}" required></td>

					<td><label for="city">City</label> <input type="text"
						name="city" id="city" value="${customer.getCity()}"
						pattern="[a-zA-Z]{3,30}" required></td>
			</tr>
			<tr>
				<td><label for="pincode">Pincode</label> <input type="text"
					name="pincode" id="pincode" pattern="[6]{1}[0-9]{5}"
					value="${customer.getPincode()}" required>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><button type="submit" id="update1">Update Address</button></td>
				</form>
			</tr>
			<tr>
				<td id="headings" colspan="4"
					style="font-size: 30px; padding-top: 70px;">Update Wallet <i
					class="fas fa-wallet"></i></td>
			</tr>
			<tr>
				<td><label for="wallet">Add Amount</label> <input type="number"
					name="wallet" id="updatewallet1" min="1000" value="1000" required></td>
				<td><button id="updatewallet" onclick="UpdateWallet()"
						type="button">Add</button></td>
			</tr>
			<tr>
				<td colspan="2">Minimum Amount : Rs.1000.00</td>
			</tr>
			<tr>
				<td><a href="Logout">
						<button type="button" id="logout">Logout</button>
				</a></td>
			</tr>
		</tbody>
	</table>
	<script type="text/javascript" src="./assets/js/myprofile.js"></script>
</body>
</html>