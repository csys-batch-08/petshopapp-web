<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
 <meta name="keywords" content="Petshop,pets,animals">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Cart</title>
<link rel="stylesheet" href="./assets/css/mycart.css"></link>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h2>My Cart</h2>
	<table>
		<caption></caption>
		<c:set var="totalAmount" value="0" scope="session"></c:set>
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${cartList}" var="cart">
			<c:set var="totalAmount"
				value="${totalAmount + cart.getTotalPrice()}" scope="session"></c:set>
			<c:set var="count" value="${count + 1}"></c:set>
			<tr>
				<td><img
					src="./assets/images/pets/${cart.getPet().getPetImage()}"
					alt="petimage"></td>
				<td id="content">
					<p>Pet id</p>
					<p>Pet Type</p>
					<p>Pet Name</p>
					<p>Available Quantity</p>
					<p>Cart Quantity</p>
					<p>Unit price</p>
					<p>Total price</p>
					<p>
						<a href="PetDescription?petid=${cart.getPet().getPetId()}"><button
								id="view">View</button></a>
						<button type="button" id="buynow"
							onclick="buy('${cart.getItemId()}','${cart.getPet().getAvilableQty()}','${cart.getQuantity()}','${customer.getAddress()}')">Buy
							Now</button>
						<button type="button" data-bs-toggle="modal"
							data-bs-target="#myModal" id="remove">
							<i class="fas fa-trash-alt"></i> Remove
						</button>
						<!-- The Modal -->
					<div class="modal" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Do you want remove this cart item</h4>
								</div>
								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-success"
										data-bs-dismiss="modal"
										onclick="removeCart('${cart.getItemId()}')">Ok</button>
									<button type="button" class="btn btn-danger"
										data-bs-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				<td id="move"><p>
						<span id="itemid" style="display: none">${cart.getItemId()}</span>
					</p>
					<p>: ${cart.getPet().getPetId()}</p>
					<p>: ${cart.getPet().getPetType()}</p>
					<p>: ${cart.getPet().getPetName()}</p>
					<p>: ${cart.getPet().getAvilableQty()}</p>
					<p>
						: <span id="quantity">${cart.getQuantity()}</span>
					</p>
					<p>: Rs.${cart.getUnitPrice()}0</p>
					<p>: Rs.${cart.getTotalPrice()}0</p>
					<p style="visibility: hidden;">empty</p></td>
			</tr>
		</c:forEach>
		<c:if test="${count > 1 }">
			<tr>
				<td colspan="3">
					<!-- onclick="removeAll()" -->
					<button id="buyall" onclick="buyAll('${customer.getAddress()}')">Buyall</button>
					<button id="removeall" data-bs-toggle="modal"
						data-bs-target="#myModalRemove">
						<i class="fas fa-trash-alt"></i> Remove All
					</button> 
					<!-- The Modal -->
					<div class="modal" id="myModalRemove">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Do you want remove
										all this cart item</h4>
								</div>
								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-success"
										data-bs-dismiss="modal" id="removeAllCart">Ok</button>
									<button type="button" class="btn btn-danger"
										data-bs-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:if>
		<c:if test="${count == 0 }">
			<h1 id="empty">Your cart is empty</h1>
		</c:if>
	</table>
	<script type="text/javascript" src="./assets/js/mycart.js"></script>
</body>
</html>