//Delete my pet ajax -->
function deletePet(petId) {
			var confirmAction = confirm("Are you sure you want delete this item");
			if (confirmAction) {			
				var url = "DeletePet?petId=" + petId;
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
				
//Delete my pet ajax response -->
		function getInfo() {
			if (request.readyState == 4) {
				var val = request.responseText;
				alert(val.trim());
				location.reload();
			}
		}
