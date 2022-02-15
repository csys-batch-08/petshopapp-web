function deletePet(petId) {
	var confirmAction = confirm("Are you sure you want delete this item");
	if (confirmAction) {
		$.ajax({
			url: "DeletePet?petId=" + petId, success: function(result) {
				alert(result.trim());	
				location.reload();	 
           }
		});
	}
}
