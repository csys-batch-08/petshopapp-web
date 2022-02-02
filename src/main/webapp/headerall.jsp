<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/headerall.css"></link>

<style type="text/css">
header{
padding-top: 15px;
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
	color: white;
}

nav a:hover {
	color: black;
}</style>
</head>
<body>
	<header>
		<!-- Web site name and logo -->
		<h1 style="font-size: 30px; margin-left: 10px;">
			<i class="fas fa-paw" style="color: white;"></i> Pet Shop
		</h1>
			<!-- navigation -->
		<nav>
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