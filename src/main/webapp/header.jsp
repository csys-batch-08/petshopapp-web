<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./assets/css/header.css"></link>
</head>
<body>
	<header>
		<!-- Web site name and logo -->
		<h1 style="font-size: 30px; margin-left: 10px;">
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
				<li><a href="MyProfile">My Profile</a></li>
				<li><a href="MyCart">My cart</a></li>
				<li><a href="MyOrders">My orders</a></li>
				<li><a href="AddItem">Add item</a></li>
				<li><a href="MyPets">My pets</a></li>
				<li><a href="Home">Home</a></li>
			</ul>
		</nav>
	</header>
</body>
</html>