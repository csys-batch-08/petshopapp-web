<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>My Orders</title>
<link rel="stylesheet" href="css/myorders.css"></link>
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
	<h2>My orders</h2>
	<table>
		<caption></caption>
		<tr>
			<th id="th">Order id</th>
			<th id="th">Pet id</th>
			<th id="th">Pet name</th>
			<th id="th">Unit_price</th>
			<th id="th">Quantity</th>
			<th id="th">Total price</th>
			<th id="th">Status</th>
			<th id="th">Order date</th>
		</tr>

		<jsp:useBean id="orderItemDao"
			class="com.petshopapp.daoimpl.OrderItemsDAO" />
		<c:set var="orderItemsList"
			value="${orderItemDao.showMyOrdersItemsList(customer)}"></c:set>
		<c:set var="length" value="${orderItemsList.size()}"></c:set>
		<c:forEach var="i" begin="0" end="${length-1}">
			<fmt:parseDate pattern="yyyy-MM-dd"
				value="${orderItemsList.get(i).getOrders().getOrderDate()}"
				var="parsedStatusDate" />
			<tr>
				<td>${orderItemsList.get(i).getOrders().getOrderId()}</td>
				<td>${orderItemsList.get(i).getPet().getPetId()}</td>
				<td>${orderItemsList.get(i).getPet().getPetName()}</td>
				<td>${orderItemsList.get(i).getUnitPrice()}</td>
				<td>${orderItemsList.get(i).getQuantity()}</td>
				<td>${orderItemsList.get(i).getTotalPrice()}</td>
				<td>${orderItemsList.get(i).getOrders().getOrderStatus()}</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy"
						value="${parsedStatusDate}" /></td>
			</tr>
			<c:if test="${i < length-1}">
				<c:if
					test="${orderItemsList.get(i).getOrders().getOrderId() != orderItemsList.get(i+1).getOrders().getOrderId()}">
					<c:if
						test="${orderItemsList.get(i).getOrders().getOrderStatus() == 'NotDelivered'}">
						<tr>
							<td colspan="8">
								<button type="button"
									onclick="cancelOrder('${orderItemsList.get(i).getOrders().getOrderId()}')">Cancel
									order</button>
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="8" style="padding-left: 0px; padding-right: 0px;"><hr></td>
					</tr>
					<tr>
						<th id="th">Order id</th>
						<th id="th">Pet id</th>
						<th id="th">Pet name</th>
						<th id="th">Unit_price</th>
						<th id="th">Quantity</th>
						<th id="th">Total price</th>
						<th id="th">Status</th>
						<th id="th">Order date</th>
				</c:if>
			</c:if>
			<c:if
				test="${orderItemsList.get(i).getOrders().getOrderStatus() == 'NotDelivered' and i == length-1}">
				<tr>
					<td colspan="8">
						<button type="button"
							onclick="cancelOrder('${orderItemsList.get(i).getOrders().getOrderId()}')">Cancel
							order</button>
					</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<script type="text/javascript" src="js/myorders.js"></script>
</body>
</html>
