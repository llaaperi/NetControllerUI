function updateRelay(relay){
	//console.log("Update relay " + relay.id);
}

function openRelayModal(id){
	$('#relayModal').modal('show');	
}

var last_call = 0;
function relayToggle(id){
	  //Remove possible duplicate events
	  var time = new Date().getTime();
	  if((time - last_call) > 100){
		  last_call = time;
		  
		  console.log("Toggle relay " + id);
		  $.ajax({
  		  type: "POST",
  		  url: "relay/toggle",
  		  data: {id:id},
  		  success: function(){
  			  console.log("OK");
  			  if($("#"+id).is(":checked")){
  				  $("#state"+id+"text").text("Current state: true").css("color","green");
  			  }else{
  				  $("#state"+id+"text").text("Current state: false").css("color","");
  			  }
  			  
  		  }
		  });
	  }
}
