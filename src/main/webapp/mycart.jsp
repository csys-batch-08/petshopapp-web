<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="headerall.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>My Cart</title>
<link rel="stylesheet" href="css/mycart.css"></link>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://kit.fontawesome.com/aeca6704b2.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<table>
	<caption>My Cart</caption>
		<c:set var="totalAmount" value="0" scope="session"></c:set>
		<c:set var="count" value="0"></c:set>
		<c:forEach items="${cartList}" var="cart">
			<c:set var="totalAmount"
				value="${totalAmount + cart.getTotalPrice()}" scope="session"></c:set>
			<c:set var="count" value="${count + 1}"></c:set>
			<tr>
				<td><img src="./Pets/${cart.getPet().getPetImage()}"
					alt="petimage"></td>

				<td>
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
						<button type="button" onclick="removeCart('${cart.getItemId()}')"
							id="remove">
							<i class="fas fa-trash-alt"></i> Remove
						</button>
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
					<p>: ${cart.getUnitPrice()}</p>
					<p>: ${cart.getTotalPrice()}</p>
					<p style="visibility: hidden;">empty</p></td>

			</tr>

		</c:forEach>
		<c:if test="${count > 1 }">
			<tr>
				<td colspan="3">
					<button id="buyall" onclick="buyAll('${customer.getAddress()}')">Buyall</button>
					<button id="removeall" onclick="removeAll()">
						<i class="fas fa-trash-alt"></i> Remove All
					</button>
				</td>
			</tr>
		</c:if>
		<c:if test="${count == 0 }">
			<h1 id="empty" style="margin-top: 140px">Your cart is empty</h1>
		</c:if>
	</table>
	<script type="text/javascript" src="js/mycart.js"></script>
</body>
</html>