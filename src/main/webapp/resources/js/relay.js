function updateRelay(relay){
	//console.log("Update relay " + relay.id);
}

function openRelayModal(id){
	$('#relayModal').modal('show');	
}

function openRelayModal(id){
	
	$.ajax({ 
        type: 'GET', 
        url: '/netcontroller/relay', 
        data: { id:id }, 
        success: function (relay) { 
        	initRelayModal(relay);
        	$('#relayModal').modal('show');
        }
    });
}

function initRelayModal(relay){
	$('#relayModalTitle').val(relay.name);
	
	$('#relayModalSave').unbind('click');
	$('#relayModalSave').click(function(){
		console.log("Save relay ", relay.id);
		var name = $('#relayModalTitle').val();
		$.ajax({ 
	        type: 'POST', 
	        url: '/netcontroller/relay/rename', 
	        data: { id:relay.id, name:name }, 
	        success: function () {
	        	$('#relay'+relay.id+'name').html(name);
	        	$('#relayModal').modal('hide');
	        }
	    });
	});
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
