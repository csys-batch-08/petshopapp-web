<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit pet details</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
<style type="text/css">

/* Page marging sttings*/
* {
	margin: 0;
	padding: 0;
	color: white;
}

/* Background style */
body {
	background-image: linear-gradient(rgba(0, 0, 0, .5) 50%,
		rgb(0, 0, 0, .5) 50%), url("./Images/background.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
	text-transform: capitalize;
}

/* Navigation style */
.navigation {
	padding-top: 15px;
	font-family: sans-serif;
}

h1 {
	display: inline;
	width: 300px;
	position: absolute;
	top: 10px;
	left: 10px;
	font-size: 25px;
}

#menu li {
	display: inline;
}

#menu a {
	float: right;
	padding-right: 20px;
	margin-top: 2px;
	font-size: 20px;
	font-weight: bold;
	text-decoration: none;
	transition: 0.5s;
	font-weight: bold;
}

#menu a:hover {
	color: black;
}


/* Edit pet  style */
.animalform {
	position: absolute;
	left: 20%;
	top: 21%;
	font-family: 'Times New Roman', Times, serif;
	font-size: 22px;
}

table {
	margin-top: -30px;
	border-radius: 5px;
	padding-left: 20px;
	background-color: rgba(194, 204, 198, 0.959);
	font-size:20px;
	width: 850px;
	height: 450px;
}

table th, td {
	padding-left: 10px;
	line-height: 2;
	margin-top: 10px;
	color: black;
}

table input:focus {
	outline: none;
}

table label {
	color: black;
}

table input {
	width: 200px;
	height: 30px;
	border-radius: 5px;
	border: none;
	color: black;
	padding-left: 10px;
}

table select {
	width: 210px;
	height: 30px;
	border-radius: 5px;
	border: none;
	color: black;
	padding-left: 10px;
}

table select option {
	color: black;
	padding-left: 10px;
}

table button {
	width: 130px;
	height: 30px;
	color: white;
	background-color: rgb(61, 160, 36);
	border: none;
	border-radius: 5px;
	font-size: 18px;
	font-weight: bold;
	box-shadow: 0 0 5px black;
}

table button:hover {
	color: black;
	background-color: white;
}

textarea {
	width: 200px;
	height: 50px;
	border-radius: 5px;
	border: none;
	resize: none;
	padding-left: 20px;
	padding-top: 20px;
	color: black;
}
</style>
</head>

<body>
<!-- Header -->
<header>
	<!-- Navigation bar -->

	<div class="navigation">

		<!-- Web site name and logo -->
		<h1>
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop
		</h1>
   <nav>
		<!-- Menu bar -->
		<ul id="menu">
			<li><a href="MyProfile.jsp">My Profile</a></li>
			<li><a href="MyCart.jsp">My cart</a></li>
			<li><a href="MyOrders.jsp">My orders</a></li>
			<li><a href="AddItem.jsp">Add item</a></li>
			<li><a href="MyPets.jsp">My pets</a></li>
			<li><a href="Home.jsp">Home</a></li>
		</ul>
	</nav>
	</div>
</header>
<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO"/> 

	 <c:set var="pet" value="${PetDao.showCurrentPet(param.petid)}" ></c:set>
		
		<form class="animalform" action="AnimalUpdateForm.jsp?">
			<table>
				<tr>
					<th id="register" colspan="4">Update Pet details  ${param.petid}</th>
					<th></th>
				</tr>

				<tbody>
					<tr>
						<td><label for="animaltype">Animal Type </label></td>
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
						<td><label for="dob" name="dob">Date of birth</label></td>
						<td><input type="date" name="dob" id="dob" value="${pet.getPetDob()}"></td>
					</tr>
					<tr>
						<td><label for="color">Color</label></td>
						<td><input type="text" name="color" id="color"
							placeholder="Color" value="${pet.getPetColor()}"
							pattern="[a-zA-Z]{3,20}" list="colors"></td>
						<td><label for="price">price</label></td>
						<td><input type="number" 5 name="price" id="price" min="0"
							placeholder="Price" value="${pet.getPetprice()}" required></td>
					</tr>
					<tr>
						<td><label for="imagelink">Image Link</label></td>
						<td><input type="file" 5 name="imagelink" id="imagelink"
							placeholder="Image file" value="${pet.getPetImage()}" required></td>
						<td><label for="qty">Quantity</label></td>
						<td><input type="number" name="quantity" id="quantityt"
							min="0" placeholder="Quantity" value="${pet.getPetQty()}"
							required></td>

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
	</div>
	<script>
		today();
		function today() {
			var today = new Date();
			var dd = String(today.getDate()).padStart(2, '0');
			var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
			var yyyy = today.getFullYear();
			var yyyy1 = today.getFullYear() - 10;

			maxdate = yyyy + '-' + mm + '-' + dd;
			mindate = yyyy1 + '-' + mm + '-' + dd;
			document.getElementById("dob").setAttribute("max", maxdate);
			document.getElementById("dob").setAttribute("min", mindate);
		}
		function status() {
			var confirmAction = confirm("Are you sure you want delete this item");
			if (confirmAction) {
				return true;
			} else {
				return false;
			}

		}
	</script>
</body>

</html>