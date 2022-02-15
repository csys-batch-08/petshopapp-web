function cancelOrder(orderId) {
	var confirmAction = confirm("Are you sure you want cancel this item");
	if (confirmAction) {
		$.ajax({
			url: "CancelOrder?orderId=" + orderId, success: function(result) {
				alert(result.trim());
				location.reload();
			}
		});
	}
}

