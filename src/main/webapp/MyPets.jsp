<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>My Pets</title>
<!-- <link rel="stylesheet" href="css/mypets.css"></link> -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"></script>

<style type="text/css">
/*Page margin setup */
* {
	margin: 0;
	padding: 0;
	color: white;
}
/*Background styles */
body {
	background-image: linear-gradient(rgba(0, 0, 0, .4) 50%,
		rgb(0, 0, 0, .4) 50%), url("./Images/background6.jpg");
    background-repeat: no-repeat;
	background-position:center;
	background-size: cover;
	background-attachment: fixed;
	text-transform: capitalize;
	font-weight:bold;
	color: white;
}

header{
padding-top: 15px;
}

header input {
	padding-left: 10px;
	font-size: 17px;
	position:absolute;
	top:20px;
	left: 230px;
	width: 300px;
	height: 30px;
	border: 1px solid;
	border-color: tomato;
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	background-color: transparent;
	border-right: none;
}

header input:focus {
	outline: none;
}

header input::placeholder {
	color: white;
}

header button {
	position: absolute;
	top: 20px;
	left:530px;
	height: 30px;
	width:100px;
	border: 1px solid;
	border-color: tomato;
	font-size: 17px;
	font-weight: bold;
	border-top-right-radius: 10px;
	border-left: none;
	background-color: tomato;
}

header button:hover {
	background-color: white;
	border-color: white;
	color: black;
}

nav {
	position: relative;
	top: -28px;
}

nav li {
	display: inline;
}

nav a {
	float: right;
	padding-right: 20px;
	font-size: 20px;
	font-weight: bold;
	text-decoration: none;
	transition: 0.5s;
}

nav a:hover {
	color: black;
}

h2{
margin-top: 20px;
margin-left: 20px;
font-size: 30px;
}

#data {
	width: 48%;
	margin-left: 0;
	padding-left: 20px;
	padding-top: 20px;
	font-size: 20px;
	font-weight: bold;
	display: inline;
	height: 320px;
	float: left;
}

#data p {
	line-height: 1.5;
	padding-left: 20px;
}

#data div {
	display: inline;
	float: left;
}

#image {
	height: 280px;
	width: 320px;
}

#image img {
	width: 320px;
	height: 250px;
	border-radius: 10px;
}

#divcontent {
	width: 140px;
}

#divdata {
	width: 180px;
	white-space: nowrap;
	overflow:hidden;
	text-overflow: ellipsis;
}

#data button {
	margin-left: 10px;
	margin-top: 5px;
	width: 120px;
	height: 30px;
	background-color: rgb(16, 177, 16);
	border-radius: 10px;
	border: none;
	color: white;
	font-size: 17px;
	font-weight: bold;
	box-shadow: 0 0 5px black;
}
</style>
</head>
<body>
	<!-- Header -->
	<header>
		<!-- Web site name and logo -->
		<h1 style="font-size: 25px;margin-left: 10px;">
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop
		</h1>
		<nav>
			<!-- Navigation bar -->
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
	<!-- My pet list -->
	<h2>My Pet List</h2>
	<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO" />
	<c:forEach items="${PetDao.showMypetdetails(customer.getCustomerId())}"
		var="pet">

		<div id="data">
			<div id="image">
				<img src="./Pets/${pet.petImage}" alt="petimage">
			</div>
			<div id="divcontent">
				<p>Name</p>
				<p>Color</p>
				<p>price</p>
				<p>Total Qty</p>
				<p>Sold Qty</p>
				<p>Aavilable Qty</p>
				<p>Status</p>
				<c:if test="${pet.status == 'Not approved'}">
					<p style="padding: 0;">
						<a href="editpet.jsp?petid=${pet.petId}"><button type="button">Edit</button></a>
					</p>
				</c:if>
			</div>
			<div id="divdata">
				<p>: ${pet.petName}</p>
				<p>: ${pet.petColor}</p>
				<p>: Rs. ${pet.petprice}</p>
				<p>: ${pet.petQty}</p>
				<p>: ${pet.petQty-pet.avilableQty}</p>
				<p>: ${pet.avilableQty}</p>
				<p>: ${pet.status}</p>
				<c:if test="${pet.status == 'Not approved'}">
					<p>
						<a href=""><button type="button"
								onclick="deletePet('${pet.petId}')">Delete</button></a>
					</p>
				</c:if>
			</div>
		</div>
	</c:forEach>
	<script type="text/javascript" src="js/mypets.js"></script>
</body>
</html>