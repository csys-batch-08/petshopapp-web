$("#removeAllCart").click(function() {
	$.ajax({
		url: "RemoveCartItems", success: function(result) {
			alert(result);
			location.reload();
		}
	});
});

// remove cart
function removeCart(itemId) {	
		$.ajax({
			url:  "RemoveCartItem?itemId=" + itemId, success: function(result) {
				alert(result.trim());
				location.reload();
			}
		});
}


//buy all
function buyAll(address) {
	if (address != 'none') {
		var confirmAction = confirm("Are you sure you want buy  all this item");
		if (confirmAction) {
			$.ajax({
				url: "BuyAll", success: function(result) {
					alert(result.trim());
					if (result.includes('Low wallet balance')) {
						window.location = 'myprofile.jsp';
					}
						location.reload();
				}
			});
		}
	}
	else {
		var confirms = confirm("Please add address before buy");
		if (confirms) {
			window.location = 'myprofile.jsp';
		}
	}
}

//buy
function buy(itemId, address) {
	if (address != 'none') {
		var confirmAction = confirm("Are you sure you want buy this item");
		if (confirmAction) {
			$.ajax({
				url: "BuyCart?itemId=" + itemId, success: function(result) {
					alert(result.trim());
					if (result.includes('Low wallet balance')) {
						window.location = 'myprofile.jsp';
					}
						location.reload();
				   }
			});
		}
	}
	else {
		var confirms = confirm("Please add address before buy");
		if (confirms) {
			window.location = 'myprofile.jsp';
		}
	}	
}



