package fi.laaperi.netcontroller.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.RelayDao;
import fi.laaperi.netcontroller.repository.Sensor;
import fi.laaperi.netcontroller.repository.SensorDao;

@Service
public class ControllerSync {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerSync.class);
	
	private static final long SYNC_INTERVAL = 2000;
	
	@Autowired
	BroadcastService broadcaster;
	
	@Autowired
	ControllerService controller;
	
	@Autowired
	RelayDao relayDao;
	
	@Autowired
	SensorDao sensorDao;
	
	@Scheduled(fixedDelay = SYNC_INTERVAL)
	public void performSync(){
		logger.debug("Sync controller");
		
		//Get current data
		List<Sensor> newSensors = controller.getSensors();
		if(newSensors != null){
			updateSensors(newSensors);
		}
		List<Relay> newRelays = controller.getRelays();
		if(newRelays != null){
			updateRelays(newRelays);
		}
		
		Gson gson = new Gson();
		List<Sensor> sensors = sensorDao.getAll();
		broadcaster.broadcast(gson.toJson(sensors));
		
		List<Relay> relays = relayDao.getAll();
		broadcaster.broadcast(gson.toJson(relays));
	}
	
	private void updateSensors(List<Sensor> newSensors){
		for(Sensor newSensor : newSensors){
			updateSensor(newSensor);
		}
	}
	
	private void updateSensor(Sensor newSensor){
		Sensor sensor = sensorDao.findById(newSensor.getId());
		if(sensor == null){//Create new
			newSensor.setName("Sensor " + newSensor.getId());
			newSensor.resetMinMax();
			sensorDao.persist(newSensor);
			return;
		}
		sensor.setValue(newSensor.getValue());
		updateMinMax(sensor);
		sensorDao.persist(sensor);
	}
	
	private void updateMinMax(Sensor sensor){
		float value = sensor.getValue();
		if(value > sensor.getMax()){
			sensor.setMax(value);
		}
		if(value < sensor.getMin()){
			sensor.setMin(value);
		}
	}

	private void updateRelays(List<Relay> newRelays){
		for(Relay newRelay : newRelays){
			updateRelay(newRelay);
		}
	}
	
	private void updateRelay(Relay newRelay){
		Relay relay = relayDao.findById(newRelay.getId());
		if(relay == null){//Create new
			newRelay.setName("Relay " + newRelay.getId());
			relayDao.persist(newRelay);
			return;
		}
		relay.setState(newRelay.getState());
		relayDao.persist(relay);
	}
	
}
