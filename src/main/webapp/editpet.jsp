<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="keywords" content="Petshop,pets,animals">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit pet details</title>
<link rel="stylesheet" href="./assets/css/additem.css"></link>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<form class="animalform" action="AnimalUpdateForm">
		<table>
			<tr>
				<th id="register" colspan="4">Update Pet details</th>
				<th><input type="text" name="petid" id="animaltype"
					value="${pet.getPetId()}" style="display: none;"></th>
			</tr>
			<tbody>
				<tr>
					<td><label for="animaltype">Pet Type </label></td>
					<td><input type="text" name="animaltype" id="animaltype"
						placeholder="Type" pattern="[a-zA-Z]{3,20}"
						value="${pet.getPetType()}" list="typelist" required></td>
					<td><label for="animalname">Name</label></td>
					<td><input type="text" name="animalname" id="animalname"
						placeholder="Name" pattern="[a-zA-Z ]{3,20}"
						value="${pet.getPetName()}" list="namelist" required></td>
				</tr>
				<tr>
					<td><label for="animalgender">Gender</label></td>
					<td><select id="genderlist" name="animalgender">
							<option value="Male">male</option>
							<option value="Female">female</option>
							<option value="Female">others</option>
					</select>
					<td><label for="dob" >Date of birth</label></td>
					<td><input type="date" name="dob" id="dob"
						value="${pet.getPetDob()}"></td>
				</tr>
				<tr>
					<td><label for="color">Color</label></td>
					<td><input type="text" name="color" id="color"
						placeholder="Color" value="${pet.getPetColor()}"
						pattern="[a-zA-Z]{3,20}" list="colors"></td>
					<td><label for="price">price</label></td>
					<td><input type="number" name="price" id="price" min="0"
						placeholder="Price" value="${pet.getPetprice()}" required></td>
				</tr>
				<tr>
					<td><label for="imagelink">Select image</label></td>
					<td><input type="file"  name="imagelink" id="imagelink"
						placeholder="Image file" value="${pet.getPetImage()}" required></td>
					<td><label for="qty">Quantity</label></td>
					<td><input type="number" name="quantity" id="quantityt"
						min="1" placeholder="Quantity" value="${pet.getPetQty()}" required></td>

				</tr>
				<tr>
					<td><label for="description">Description</label></td>
					<td><textarea name="description" id="description"
							placeholder="Description about pet" cols="30"
							style="margin-top: 10px;">${pet.getDescription()}</textarea></td>
					<td><button type="submit">update</button></td>
				</tr>
			</tbody>
		</table>
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
	<script type="text/javascript" src="./assets/js/editpet.js"></script>
</body>

</html>