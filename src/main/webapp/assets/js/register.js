//Password and Confirm password Matching-->	
	var register=document.getElementById("register");
		function validatePassword() {
			let password = document.getElementById("passwordinput").value;
			let repassword = document.getElementById("repasswordinput").value;
			let comment = document.getElementById("repasswordcomment");
			register.disabled = false;
			if (password == repassword) {
				comment.style.display = "none";
			} else {
				comment.style.display = "block";
				comment.innerHTML = "Password does not match";
				comment.style.color = "red";
				comment.style.fontSize = "17px";
				register.disabled=true;
			}
		}
		
		
function validateUsername(){
	var username = $('#usernameinput').val();
	
	$.ajax({
		url: "ValidateUsername?userName="+username, success: function(result) {
			if(!result.includes("Available")){
			var x = document.getElementById("snackbar");
			x.innerHTML=result.trim();
              $('#usernameinput').val("");
			x.className = "show";
			setTimeout(function() { x.className = x.className.replace("show", ""); }, 3000);
		}
		}
	});
}

function validateEmail(){
	var email= $('#emailinput').val();
	
	$.ajax({
		url: "ValidateEmail?email="+email, success: function(result) {
			if(!result.includes("Available")){
			var x = document.getElementById("snackbar");
			x.innerHTML=result.trim();	
			$('#emailinput').val("");
			x.className = "show";
			setTimeout(function() { x.className = x.className.replace("show", ""); }, 3000);
		}
		}
	});
}
