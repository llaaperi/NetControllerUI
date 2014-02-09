var rainbow = new Rainbow(); 
rainbow.setNumberRange(-20, 30);
rainbow.setSpectrum('blue', 'red');

function updateSensor(sensor){
	//console.log("Update sensor " + sensor.id);
	$("#sensor"+sensor.id).html(sensor.value.toFixed(1));
	$("#sensor"+sensor.id).css("color", "#"+rainbow.colourAt(sensor.value));
}
