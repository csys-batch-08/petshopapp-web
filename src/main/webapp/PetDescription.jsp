<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Pet Details</title>
<link rel="stylesheet" href="css/petdescription.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>

</head>
<body>
	<!-- Header -->
	<header>
		<!-- Navigation bar -->

		<div class="navigation">

			<!-- Web site name and logo -->
			<h1>
			    <!-- icon for pet shop -->
				<i class="fas fa-paw" style="color: white;"></i> Pet Shop
			</h1>
			<nav>
				<!-- Menu bar -->
				<ul id="menu">
					<li><a href="myprofile.jsp">My Profile</a></li>
					<li><a href="mycart.jsp">My cart</a></li>
					<li><a href="myorders.jsp">My orders</a></li>
					<li><a href="additem.jsp">Add item</a></li>
					<li><a href="mypets.jsp">My pets</a></li>
					<li><a href="home.jsp">Home</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<h2>Pet Details</h2>
	<jsp:useBean id="PetDao" class="com.petshopapp.daoimpl.PetDAO" />
	<c:set var="petDescription"
		value="${PetDao.showCurrentPet(param.petid)}" scope="session"></c:set>

	<table>
		<caption></caption>
		<thead>
			<tr>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><img src="./Pets/${petDescription.petImage}" alt=""></td>
				<td>
					<p>
						<strong>Id</strong>
					</p>
					<p>
						<strong>Type</strong>

					</p>
					<p>
						<strong>Name</strong>

					</p>
					<p>
						<strong>Gender</strong>

					</p>
					<p>
						<strong>Date Of Birth</strong>

					</p>
					<p>
						<strong>Color</strong>

					</p>
					<p>
						<strong>Unit Price</strong>

					</p>
					<p>
						<strong>Available Qty</strong>

					</p>
					<p>
						<strong>Supplier Name</strong>

					</p>
				</td>
				<fmt:parseDate pattern="yyyy-MM-dd" value="${petDescription.petDob}"
					var="parsedStatusDate" />
				<td><p>: ${petDescription.petId}</p>
					<p>: ${petDescription.petType}</p>
					<p>: ${petDescription.petName}</p>
					<p>: ${petDescription.petGender}</p>
					<p>
						:
						<fmt:formatDate pattern="dd-MM-yyyy" value="${parsedStatusDate}" />
					</p>
					<p>: ${petDescription.petColor}</p>
					<p>: ${petDescription.petprice}</p>
					<p>: ${petDescription.avilableQty}</p>
					<p>: ${petDescription.getCustomer().getFirstName()}</p></td>

			</tr>
		</tbody>
	</table>

	<div id="description">
		<p style="text-transform: none;">
			<strong>Description: </strong><br>${petDescription.description}
		<p>
		<p style="font-size: 27px;">
			<label>Quantity :</label> <span><i class="fas fa-minus-square"
				onclick="decrease()"></i><input type="number" id="quantity"
				max="${petDescription.avilableQty}" min="0" value="0"
				name="quantity"> <i class="fas fa-plus-square"
				onclick="increase(${petDescription.avilableQty})"></i></span>
		</p>
		<p>
			<button type="button" onclick="addToCart()">
				<i class="fas fa-cart-plus"></i> Cart
			</button>
			<button type="button" onclick="buyNow('${customer.address}')">Buy
				Now</button>
		</p>
		<p name="message" id="message">
		<p>
	</div>

	<script type="text/javascript" src="js/petdescription.js"></script>
</body>
</html>