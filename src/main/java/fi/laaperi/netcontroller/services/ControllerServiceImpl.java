package fi.laaperi.netcontroller.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.Sensor;

@Service
public class ControllerServiceImpl implements ControllerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerServiceImpl.class);
	
	//Sensor messages
	public static final String GET_SENSORS = "sensor:get:all";
	public static final String GET_SENSOR = "sensor:get:";	// + id
	
	//Relay messages
	public static final String GET_RELAYS = "relay:get:all";
	public static final String GET_RELAY = "relay:get:";	// + id
	public static final String SET_RELAY = "relay:set:";	// + id
	
	
	@Autowired
	UDPService connection;
	
	@Override
	public List<Sensor> getSensors(){
		logger.debug("getSensors()");
		Gson gson = new Gson();
		String response = connection.sendMessage(GET_SENSORS);
		List<Sensor> sensors = new ArrayList<Sensor>();
		try{
		sensors = gson.fromJson(response, new TypeToken<List<Sensor>>(){}.getType());
		} catch (JsonParseException e){
			logger.error("Unable to parse response: " + response);
		}
		return sensors;
	}
	
	@Override
	public float getValue(long id){
		logger.debug("getValue("+id+")");
		Gson gson = new Gson();
		String response = connection.sendMessage(GET_SENSOR + id);
		Sensor sensor = new Sensor();
		try{
		sensor = gson.fromJson(response, Sensor.class);
		} catch (JsonParseException e){
			logger.error("Unable to parse response: " + response);
		}
		return sensor.getValue();
	}
	
	
	@Override
	public List<Relay> getRelays(){
		logger.debug("getRelays()");
		Gson gson = new Gson();
		String response = connection.sendMessage(GET_RELAYS);
		List<Relay> relays = new ArrayList<Relay>();
		try{
		relays = gson.fromJson(response, new TypeToken<List<Relay>>(){}.getType());
		} catch (JsonParseException e){
			logger.error("Unable to parse response: " + response);
		}
		return relays;
	}
	
	@Override
	public boolean getState(long id){
		logger.debug("getState("+id+")");
		Gson gson = new Gson();
		String response = connection.sendMessage(GET_RELAY + id);
		Relay relay = new Relay();
		try{
		relay = gson.fromJson(response, Relay.class);
		} catch (JsonParseException e){
			logger.error("Unable to parse response: " + response);
		}
		return relay.getState();
	}
	
	@Override
	public void setState(long id, boolean state){
		logger.debug("setState("+id+","+state+")");
		connection.sendMessage(SET_RELAY + id + ":" + state);
	}
}
