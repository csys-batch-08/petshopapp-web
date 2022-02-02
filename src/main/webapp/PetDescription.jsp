<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="headerall.jsp"%>
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

	<h2>Pet Details</h2>
	<div id="data">
		<div id="image">
			<img src="./Pets/${petDescription.petImage}" alt="petimage">
		</div>
		<div id="divcontent">
			<p>Id</p>
			<p>Type</p>
			<p>Name</p>
			<p>Gender</p>
			<p>Date Of Birth</p>
			<p>Color</p>
			<p>Unit Price</p>
			<p>Available Qty</p>
			<p>Supplier Name</p>
		</div>
		<div id="divdata">
			<fmt:parseDate pattern="yyyy-MM-dd" value="${petDescription.petDob}"
				var="parsedStatusDate" />
			<p>: <span id="petid">${petDescription.petId}</span></p>
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
			<p>: ${petDescription.getCustomer().getFirstName()}</p>
		</div>
		<div id="description">
			<p style="font-weight: bold;">Description:</p>
			<p>${petDescription.description}</p>
			<p id="icon">
				<label>Quantity : </label><i class="fas fa-minus-square"
					onclick="decrease()"></i><input type="number" id="quantity"
					max="${petDescription.avilableQty}" min="0" value="0"
					name="quantity"> <i class="fas fa-plus-square"
					onclick="increase(${petDescription.avilableQty})"></i><br>
				<button type="button" onclick="addToCart('${petDescription.getPetId()}')">
					<i class="fas fa-cart-plus"></i> Cart
				</button>
				<button type="button" onclick="buyNow('${customer.address}','${petDescription.getPetId()}')">Buy
					Now</button>					
			</p>
		</div>
	</div>
	<script type="text/javascript" src="js/petdescription.js"></script> 
</body>
</html>