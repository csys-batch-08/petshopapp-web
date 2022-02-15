$("#removeAllCart").click(function() {
	$.ajax({
		url: "RemoveCartItems", success: function(result) {
			alert(result);
			location.reload();
		}
	});
});



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
					else {
						location.reload();
					}
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
function buy(itemId, availableQuantity, cartQuantity, address) {

	if (availableQuantity >= cartQuantity) {
		if (address != 'none') {
			var confirmAction = confirm("Are you sure you want buy this item");
			if (confirmAction) {
				var url = "BuyCart?itemId=" + itemId;
				if (window.XMLHttpRequest) {
					request = new XMLHttpRequest();
				} else if (window.ActiveXObject) {
					request = new ActiveXObject("Microsoft.XMLHTTP");
				}
				try {
					request.onreadystatechange = getInfo;
					request.open("GET", url, true);
					request.send();
				} catch (e) {
					alert("Unable to connect to server");
				}
			} else {
				alert("Action canceled");
			}

		} else {
			var confirms = confirm("Please add address before buy");
			if (confirms) {
				window.location = 'myprofile.jsp';
			}
		}
	}
	else {
		alert("Sorry pet item not available now");
	}
}

// remove cart
function removeCart(itemId) {

	var url = "RemoveCartItem?itemId=" + itemId;

	if (window.XMLHttpRequest) {
		var request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		var request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	try {
		request.onreadystatechange = getInfo;
		request.open("GET", url, true);
		request.send();
	} catch (e) {
		alert("Unable to connect to server");
	}
}


//remove all
function removeAll() {
	var confirmAction = confirm("Are you sure you want remove all this item");
	if (confirmAction) {
		var url = "RemoveCartItems";
		console.log(url);
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	} else {
		alert("Action canceled");
	}
}

function getInfo() {
	if (request.readyState == 4) {
		var val = request.responseText;
		alert(val.trim());
		if (val.includes('Low wallet balance')) {
			window.location = 'myprofile.jsp';
		}
		else {
			location.reload();
		}
	}
}