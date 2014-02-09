function sync(){
	console.log('Start sync');
	var socket = $.atmosphere;
	var request = { url: '/netcontroller/stream',
			contentType : "application/json",
			logLevel : 'debug',
			transport : 'websocket' ,
			fallbackTransport: 'long-polling'};

	
	
	request.onOpen = function(response) {
		$.atmosphere.log('info', ["Connected: " + response.transport]);
		console.log("Connected to server");
	};	
	
	request.onMessage = function (response) {
		var data = "<empty>";
		try {
			data = $.parseJSON(response.responseBody);
		} catch (err) {
			$.atmosphere.log('error', ["JSON parse error: " + err]);
		}
		
		//console.log("Received: " + data);
		
		//Refresh elements
		jQuery.each(data, function(){
			if('value' in this){
				//console.log("Sensor");
				updateSensor(this);
				return;
			}
			if('state' in this){
				//console.log("Relay");
				updateRelay(this);
				return;
			}
		});
	};
	
	var subSocket = socket.subscribe(request);
}

