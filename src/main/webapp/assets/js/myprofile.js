//Update Wallet to ajax
function UpdateWallet() {
	var wallet = document.getElementById("updatewallet1").value;
	$.ajax({
		url: "UpdateWallet?wallet=" + wallet, success: function(result) {
			alert(result);
			location.reload();
		}
	});
}


// Update image to ajax
function UpdateImage(){
	var image = document.getElementById("imagepath").value;
	const name = image.substring(12, image.length);
	$.ajax({
		url: "updateProfileImage?image=" + name, success: function(result) {
			alert(result);
			location.reload();
		}
	});
}

function showPassword() {
	var show = document.getElementById("password");
	if (show.type === "password") {
		show.type = "text";
	} else {
		show.type = "password";
	}
}

