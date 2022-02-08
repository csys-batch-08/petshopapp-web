<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet"><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
 <meta name="keywords" content="Petshop,pets,animals">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pet Details</title>
<link rel="stylesheet" href="./assets/css/header.css"></link>
<link rel="stylesheet" href="./assets/css/petdescription.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
</head>
<body>
	<h2>Pet Details</h2>
	<div id="data">
		<div id="image">
			<img src="./assets/images/pets/${petDescription.petImage}" alt="petimage">
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
				<label> Quantity : </label> <i class="fas fa-minus-square"
					onclick="decrease()" onkeydown="" aria-hidden="true"></i>
					<input type="number" id="quantity"
					max="${petDescription.avilableQty}" min="0" value="0" 
					name="quantity" /> <i class="fas fa-plus-square"
					onclick="increase(${petDescription.avilableQty})" onkeydown="" aria-hidden="true"></i></p>
					
				<p>
				<button type="button" onclick="addToCart('${petDescription.getPetId()}')">
					<i class="fas fa-cart-plus" aria-hidden="true"></i> Cart
				</button>
				<button type="button" onclick="buyNow('${customer.address}','${petDescription.getPetId()}')">Buy
					Now</button>					
			</p>
		</div>
	</div>
	<script type="text/javascript" src="./assets/js/petdescription.js"></script> 
</body>
</html>