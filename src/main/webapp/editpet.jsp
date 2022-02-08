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
<link rel="stylesheet" href="./assets/css/header.css"></link>
<link rel="stylesheet" href="./assets/css/additem.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
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
					<td><label for="animaltype">Pet Type </label>
					    <input type="text" name="animaltype" id="animaltype"
						placeholder="Type" pattern="[a-zA-Z]{3,20}"
						value="${pet.getPetType()}" list="typelist" required></td>
					<td><label for="animalname">Name</label>
					    <input type="text" name="animalname" id="animalname"
						placeholder="Name" pattern="[a-zA-Z ]{3,20}"
						value="${pet.getPetName()}" list="namelist" required></td>
				</tr>
				<tr>
					<td><label for="animalgender">Gender</label>
					    <select name="animalgender" id="animalgender">
							<option value="Male">male</option>
							<option value="Female">female</option>
							<option value="Female">others</option>
					</select>
					<td><label for="dob" >Date of birth</label>
					    <input type="date" name="dob" id="dob"
						value="${pet.getPetDob()}"></td>
				</tr>
				<tr>
					<td><label for="color">Color</label>
					    <input type="text" name="color" id="color"
						placeholder="Color" value="${pet.getPetColor()}"
						pattern="[a-zA-Z]{3,20}" list="colors"></td>
					<td><label for="price">price</label>
					    <input type="number" name="price" id="price" min="0"
						placeholder="Price" value="${pet.getPetprice()}" required></td>
				</tr>
				<tr>
					<td><label for="imagelink">Select image</label>
					   <input type="file"  name="imagelink" id="imagelink"
						placeholder="Image file" value="${pet.getPetImage()}" required></td>
					<td><label for="qty">Quantity</label>
					    <input type="number" name="quantity" id="quantityt"
						min="1" placeholder="Quantity" value="${pet.getPetQty()}" required></td>

				</tr>
				<tr>
					<td colspan="2"><label for="description" id="descriptionlabel">Description</label>
					  <textarea name="description" id="description"
							placeholder="Description about pet" >${pet.getDescription()}</textarea>
					   <button type="submit">update</button></td>
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