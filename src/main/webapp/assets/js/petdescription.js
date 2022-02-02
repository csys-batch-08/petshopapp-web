  // Add to cart to ajax

    function addToCart(petId){     
        let qty=document.getElementById("quantity").value;
        if(qty>0){
    	var url="AddToCart?quantity="+ qty +"&petid=" + petId;  
    	
        if(window.XMLHttpRequest){  
    	request=new XMLHttpRequest();  
    		}  
    	else if(window.ActiveXObject){  
    	request=new ActiveXObject("Microsoft.XMLHTTP");  
    		}  
    	try  
    	{  
    	request.onreadystatechange=getInfo;  
    	request.open("GET",url,true);  
    	request.send();  
    	}  
    	catch(e)  
    	{  
    	alert("Unable to connect to server");  
    	}
        }
        else{
        	alert("invalid qty");
        }
    } 
     
    // buy Now to ajax
    function buyNow(address,petid){  
					if (address == 'none') {
						var confirmAction = confirm("Please add address before buy");
						if (confirmAction) {
							  window.location = 'myprofile.jsp';
						}
					} 
					else {
						var confirmAction1 = confirm("Are you sure you want buy this item");
						if (confirmAction1) {
							let qty = document.getElementById("quantity").value;
							if (qty > 0) {								
								var url = "BuyNow?quantity=" + qty+ "&petid=" + petid;
								if (window.XMLHttpRequest) {
									request = new XMLHttpRequest();
								} else if (window.ActiveXObject) {
									request = new ActiveXObject(
											"Microsoft.XMLHTTP");
								}
								try {
									request.onreadystatechange = getInfo;
									request.open("GET", url, true);
									request.send();
								} catch (e) {
									alert("Unable to connect to server");
								}
							
						} 
							else {
							alert("Invalid quantity");
						}
						}
						else {
							  alert("Action canceled");
					}
				}
    }
    
  
    // Response ajax
    function getInfo(){  
    	if(request.readyState==4){  
    	var val=request.response;
    	  alert(val.trim()) ;        
    	   if(val.includes("Low wallet balance")){
				  window.location = 'myprofile.jsp';
			}
			else{	
				location.reload();
			}
    	}  
    	}	
			
    // Increase quantity
	function increase(availableQuantity){
	    var value= document.getElementById("quantity").value;
	    if(value < availableQuantity){
	    value++;
		document.getElementById("quantity").value=value;
		}
	}
    
    // Decrease quantity
    function  decrease(){
       var value= document.getElementById("quantity").value;
       	   if(value>0){
				value--;
				document.getElementById("quantity").value=value;
    		}
	}