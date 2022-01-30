<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>

<link rel="stylesheet" href="css/adminhome.css"></link>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>

</head>
<body>
	<header>
		<h1 style="font-size: 25px; margin-left: 10px;">
			<i class="fas fa-paw" style="color: white;"></i> PET SHOP
		</h1>
		<nav>
			<ul>
				<li><a href="adminprofile.jsp">My Profile</a></li>
				<li><a href="userlist.jsp">User List</a></li>
				<li><a href="adminhome.jsp">Home</a></li>
			</ul>
		</nav>

	</header>

	<h2>Not Approved Pet List</h2>
	<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO" />
	<c:set var="count" value="1" />
	<c:forEach items="${PetDao.showNotApprovedPetList()}" var="pet">
		<div id="data">
			<div id="image">
				<img src="./Pets/${pet.getPetImage()}" alt="pet image">
			</div>
			<div id="divcontent">
				<p>Pet Type</p>
				<p>Pet Name</p>
				<p>Pet Color</p>
				<p>Unit price</p>
				<p>Quantity</p>
				<p>
					<button type="button"
						onclick="UpdateStatus('${pet.getPetId()}','approved')">Accept</button>
				</p>
			</div>
			<div id="divdata">
				<p>: ${pet.petType}</p>
				<p>: ${pet.petName}</p>
				<p>: ${pet.petColor}</p>
				<p>: Rs. ${pet.petprice}</p>
				<p>: ${pet.avilableQty} Quantity</p>
				<p>
					<button type="button"
						onclick="UpdateStatus('${pet.getPetId()}','declined')">Decline</button>
				</p>
			</div>
		</div>
	</c:forEach>
	<script type="text/javascript" src="js/adminhome.js"></script>
</body>
</html>
</html>