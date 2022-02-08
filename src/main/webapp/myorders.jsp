<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
<title>My Orders</title>
<link rel="stylesheet" href="./assets/css/header.css"></link>
<link rel="stylesheet" href="./assets/css/myorders.css"></link>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
</head>
<body>
	<h2>My Orders</h2>
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
		<c:set var="length" value="${orderItemsList.size()}"></c:set>
		<c:forEach var="i" begin="0" end="${length-1}">
			<fmt:parseDate pattern="yyyy-MM-dd"
				value="${orderItemsList.get(i).getOrders().getOrderDate()}"
				var="parsedStatusDate" />
			<tr>
				<td>${orderItemsList.get(i).getOrders().getOrderId()}</td>
				<td>${orderItemsList.get(i).getPet().getPetId()}</td>
				<td>${orderItemsList.get(i).getPet().getPetName()}</td>
				<td>Rs.${orderItemsList.get(i).getUnitPrice()}0</td>
				<td>${orderItemsList.get(i).getQuantity()}</td>
				<td>Rs.${orderItemsList.get(i).getTotalPrice()}0</td>
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
	<script type="text/javascript" src="./assets/js/myorders.js"></script>
</body>
</html>
