//Password and Confirm password Matching-->	
		function validatePassword() {
			let password = document.getElementById("passwordinput").value;
			let repassword = document.getElementById("repasswordinput").value;
			let comment = document.getElementById("repasswordcomment");
			document.getElementById("register").disabled = false;
			if (password == repassword) {
				comment.style.display = "none";
			} else {
				comment.style.display = "block";
				comment.innerHTML = "password does not match";
				comment.style.color = "red";
				comment.style.fontSize = "17px";
				console.log("called else");
				document.getElementById("register").disabled = true;
			}
		}
		
//Validation Of Username using Ajax -->	

		function validateUsername() {
			let userName = document.getElementById("usernameinput").value;
			if (userName.length > 7) {
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
			} else {
				alert("Username must have 8 character");
			}
		}


//Validation of email Ajax -->			

  		function validateEmail() {
			let email = document.getElementById("emailinput").value;
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
  		
 //Ajax Respose For Username validation-->
 
		function getInfoUsername() {
			if (request.readyState == 4) {
				var val = request.responseText;
				if (!val.includes("Available")) {
					alert(val);
					document.getElementById("usernameinput").value = "";

				}
			}
		}
		

//Ajax Respose For Email Validation-->

		function getInfoEmail() {
			if (request.readyState == 4) {
				var val = request.responseText;
				if (!val.includes("Available")) {
					alert(val);
					document.getElementById("emailinput").value = "";

				}
			}
		}
