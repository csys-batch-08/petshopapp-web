<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pet register</title>
<link rel="stylesheet" href="./assets/css/additem.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js" crossorigin="anonymous"></script>

</head>
<body>
	<!-- Add pet from -->
	<form class="animalform" action="AddPet">
		<table>
		<caption></caption>
			<tr>
				<th id="register" colspan="4">Register Animal</th>
				<th></th>
			</tr>

			<tbody>
				<tr>
					<td><label for="animaltype">Animal Type </label></td>
					<td><input type="text" name="animaltype" id="animaltype"
						placeholder="Type" pattern="[a-zA-Z]{3,20}" list="typelist"
						required></td>
					<td><label for="animalname">Name</label></td>
					<td><input type="text" name="animalname" id="animalname"
						placeholder="Name" pattern="[a-zA-Z ]{3,20}" list="namelist"
						required></td>
				</tr>
				<tr>
					<td><label for="animalgender">Gender</label></td>
					<td><select id="genderlist" name="animalgender">
							<option value="Male">male</option>
							<option value="Female">female</option>
							<option value="others">others</option>
					</select>
					<td><label for="dob" name="dob">Date of birth</label></td>
					<td><input type="date" name="dob" id="dob"></td>
				</tr>
				<tr>
					<td><label for="color">Color</label></td>
					<td><input type="text" name="color" id="color"
						placeholder="Color" pattern="[a-zA-Z]{3,20}" list="colors"></td>
					<td><label for="price">price</label></td>
					<td><input type="number" 5 name="price" id="price" min="0"
						placeholder="Price" required></td>
				</tr>
				<tr>
					<td><label for="imagelink">Image Link</label></td>
					<td><input type="file" 5 name="imagelink" id="imagelink"
						placeholder="Image file" required></td>
					<td><label for="qty">Quantity</label></td>
					<td><input type="number" name="quantity" id="quantityt"
						min="0" placeholder="Quantity" required></td>

				</tr>
				<tr>
					<td><label for="description">Description</label></td>
					<td><textarea name="description" id="description"
							placeholder="Description about pet" cols="30"
							style="margin-top: 10px;"></textarea></td>
					<td><button>Register</button></td>
				</tr>
			</tbody>
		</table>

		<!-- Add pet from data list -->
		<datalist id="typelist">
			<option value="Dog"></option>
			<option value="Cat"></option>
			<option value="Birds"></option>
			<option value="Fish"></option>
		</datalist>
		<datalist id="namelist">
			<option value="french bulldog"></option>
			<option value="German Shepherd"></option>
			<option value="Maine Coon"></option>
			<option value="Ragdoll"></option>
			<option value="Parakeets"></option>
			<option value="Cockatiels"></option>
			<option value="Angelfish"></option>
			<option value="Rainbow"></option>
		</datalist>

		<datalist id="colors">
			<option value="Black"></option>
			<option value="White"></option>
			<option value="Gray"></option>
			<option value="Gold"></option>
			<option value="Sliver"></option>
			<option value="Green"></option>
			<option value="Rainbow"></option>
			<option value="Blue"></option>
		</datalist>
	</form>
	<script type="text/javascript" src="./assets/js/additem.js"></script>
</body>

</html>