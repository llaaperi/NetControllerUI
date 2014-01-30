package fi.laaperi.netcontroller.services;

import java.util.List;

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.Sensor;

public interface ControllerService {

	public abstract List<Sensor> getSensors();

	public abstract float getValue(long sensorId);
	
	public abstract List<Relay> getRelays();

	public abstract boolean getState(long relayId);
	
	public abstract void setState(long relayID, boolean state);
}