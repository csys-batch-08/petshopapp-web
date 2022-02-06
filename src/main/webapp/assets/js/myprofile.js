//Update Wallet to ajax
function UpdateWallet() {
	var wallet = document.getElementById("updatewallet1").value;
	var url = "UpdateWallet?wallet=" + wallet;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	try {
		request.onreadystatechange = getInfo;
		request.open("GET", url, true);
		request.send();
	}
	catch (e) {
		alert("Unable to connect to server");
	}
}

// Update image to ajax
function UpdateImage() {
	var image = document.getElementById("imagepath").value;
	console.log(image);
	const name = image.substring(12, image.length);
	var url = "updateProfileImage?image=" + name;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	try {
		request.onreadystatechange = getInfo;
		request.open("GET", url, true);
		request.send();
	}
	catch (e) {
		alert("Unable to connect to server");
	}
}

// Show Password
function getInfo() {
	if (request.readyState == 4) {
		var val = request.responseText;
		alert(val.trim());
		location.reload();
	}
}

function showPassword() {
	var show = document.getElementById("password");
	if (show.type === "password") {
		show.type = "text";
	} else {
		show.type = "password";
	}
}

//Validation Of Username using Ajax -->	
/*var Email = document.getElementById("email").value;
var Username = document.getElementById("username").value;

function validateUsername() {
	let userName = document.getElementById("username").value;
	if (!emuserName.equals(UserName)) {

		var url = "ValidateUsername?userName=" + userName;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfoUsername;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
}


//Validation of email Ajax -->			

function validateEmail() {
	var email = document.getElementById("email").value;
	console.log(email);
	console.log(Email);
	if (!Email.equals(email)) {
		var url = "ValidateEmail?email=" + email;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfoEmail;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}

	}
}

//Ajax Respose For Username validation--> 
function getInfoUsername() {
	if (request.readyState == 4) {
		var val = request.responseText;
		if (!val.includes("Available")) {
			alert(val);
		}
	}
}


//Ajax Respose For Email Validation-->

function getInfoEmail() {
	if (request.readyState == 4) {
		var val = request.responseText;
		if (!val.includes("Available")) {
			alert(val);
		}
	}
}*/
