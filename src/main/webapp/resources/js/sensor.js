var rainbow = new Rainbow(); 
rainbow.setNumberRange(-20, 30);
rainbow.setSpectrum('blue', 'red');

function updateSensor(sensor){
	//console.log("Update sensor " + sensor.id);
	$("#sensor"+sensor.id).html(sensor.value.toFixed(1));
	$("#sensor"+sensor.id).css("color", "#"+rainbow.colourAt(sensor.value));
}

function openSensorModal(id){
	
	$.ajax({ 
        type: 'GET', 
        url: 'sensor', 
        data: { id:id }, 
        success: function (sensor) { 
        	initSensorModal(sensor);
        	$('#sensorModal').modal('show');
        }
    });
}

function initSensorModal(sensor){
	$('#sensorModalTitle').val(sensor.name);
	
	$('#sensorModalSave').unbind('click');
	$('#sensorModalSave').click(function(){
		console.log("Save sensor ", sensor.id);
		var name = $('#sensorModalTitle').val();
		$.ajax({ 
	        type: 'POST', 
	        url: 'sensor/rename', 
	        data: { id:sensor.id, name:name }, 
	        success: function () {
	        	$('#sensor'+sensor.id+'name').html(name);
	        	$('#sensorModal').modal('hide');
	        }
	    });
	});
}
