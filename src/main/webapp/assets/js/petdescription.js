// Add to cart to ajax
function addToCart(petId) {
	let qty = document.getElementById("quantity").value;
	if (qty > 0  ){
		$.ajax({
			url: "AddToCart?quantity=" + qty + "&petid=" + petId, success: function(result) {
				alert(result.trim());
				if (result.includes("Low wallet balance")) {
					window.location = 'myprofile.jsp';
				}
				else {
					location.reload();
				}
			}
		});
	}
	else {
		alert("Invalid quantity");
	}
}

// buy Now to ajax
function buyNow(address, petid) {
	if (address == 'none') {
		var confirmAction = confirm("Please add address before buy");
		if (confirmAction) {
			window.location = 'myprofile.jsp';
		}
	}
	else {
		var confirmAction1 = confirm("Are you sure you want buy this item");
		if (confirmAction1) {
			let quantity = document.getElementById("quantity").value;
			if (qty > 0) {
				$.ajax({
					url: "BuyNow?quantity=" + quantity + "&petid=" + petid, success: function(result) {
						alert(result.trim());
						if (result.includes("Low wallet balance")) {
							window.location = 'myprofile.jsp';
						}
							location.reload();
					}
				});
			}
			else{
				alert("Invalid quantity");
			}
		}
	}
}


// Increase quantity
function increase(availableQuantity) {
	var value = document.getElementById("quantity").value;
	if (value < availableQuantity) {
		value++;
		document.getElementById("quantity").value = value;
	}
}

// Decrease quantity
function decrease() {
	var value = document.getElementById("quantity").value;
	if (value > 0) {
		value--;
		document.getElementById("quantity").value=value;
	}
}