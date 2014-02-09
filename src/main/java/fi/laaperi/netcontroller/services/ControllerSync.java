package fi.laaperi.netcontroller.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.RelayDao;
import fi.laaperi.netcontroller.repository.Sensor;
import fi.laaperi.netcontroller.repository.SensorDao;

@Service
public class ControllerSync {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerSync.class);
	
	private static final long SYNC_INTERVAL = 2000;
	
	@Autowired
	ControllerService controller;
	
	@Autowired
	RelayDao relayDao;
	
	@Autowired
	SensorDao sensorDao;
	
	@Scheduled(fixedDelay = SYNC_INTERVAL)
	public void performSync(){
		logger.info("Sync controller");
		List<Sensor> sensors = controller.getSensors();
		if(sensors != null){
			sensorDao.persistAll(sensors);
		}
		List<Relay> relays = controller.getRelays();
		if(relays != null){
			relayDao.persistAll(relays);
		}
	}

}
