// Update Pet Status
function UpdateStatus(petId,status){        
    var url="UpdatePetStatus?petId="+petId+"&status="+status;   	
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

// response of ajax
function getInfo(){  
    if(request.readyState==4){  
    var val=request.responseText; 	    
        alert(val); 
     location.reload();	
    }  
    }  
