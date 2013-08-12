function createRequest() {
  try {
    request = new XMLHttpRequest();
  } catch (tryMS) {
    try {
      request = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (otherMS) {
      try {
        request = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (failed) {
        request = null;
      }
    }
  }	
  return request;
}

function test() {	  
	  request = createRequest();
	  if (request == null)
	    alert("Unable to create request");
	  else {	    
	    var url= "result.jsp";	    
	    request.onreadystatechange = showTable;	   
	    request.open("GET", url, true);
	    request.send(null);	   
	  }
	}

	function showTable() {
	  if (request.readyState == 4) {
	    if (request.status == 200) {
	    	alert("�߳�?");
	    	var contentPane = document.getElementById("content");
	    	  contentPane.innerHTML = request.responseText;	    
	    }
	  }  
	}