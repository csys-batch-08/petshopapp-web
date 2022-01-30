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

<link rel="stylesheet" href="css/home.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>

</head>
<body>
	<header>
		<!-- Web site name and logo -->
		<h1 style="font-size: 30px;">
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop
		</h1>
		<!-- Search bar -->
		<form action="Search">
			<input type="search" name="search" id="searchinput"
				placeholder="Enter pet category or name">
			<button type="submit" id="search">search</button>
		</form>
		<nav>
			<!-- Menu bar -->
			<ul>
				<li><a href="myprofile.jsp">My Profile</a></li>
				<li><a href="mycart.jsp">My cart</a></li>
				<li><a href="myorders.jsp">My orders</a></li>
				<li><a href="additem.jsp">Add item</a></li>
				<li><a href="mypets.jsp">My pets</a></li>
				<li><a href="home.jsp">Home</a></li>
			</ul>
		</nav>
	</header>
	<!-- Pet list -->

	<h2>Pet Lists</h2>

	<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO" />
	<c:forEach items="${PetDao.showAllpetsDetails(customer)}" var="pet">

		<div id="data">
			<div id="image">
				<img src="./Pets/${pet.petImage}" alt="petimage">
			</div>
			<div id="divcontent">
				<p>Pet Type</p>
				<p>Pet Name</p>
				<p>Pet Color</p>
				<p>Unit price</p>
				<p>Available</p>
				<p>
					<a href="petdescription.jsp?petid=${pet.petId}"><button>view</button></a>
				</p>
			</div>
			<div id="divdata">
				<p>: ${pet.petType}</p>
				<p>: ${pet.petName}</p>
				<p>: ${pet.petColor}</p>
				<p>: Rs. ${pet.petprice}</p>
				<p>: ${pet.avilableQty} Quantity</p>
				<p style="visibility: hidden;">empty</p>
			</div>
		</div>
	</c:forEach>
</body>
</html>
</html>