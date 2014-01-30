package fi.laaperi.netcontroller.services;

public class ControllerServiceImpl implements ControllerService {
	
	
	@Override
	public String getSensors(){
		return "Sensor1";
	}
	
	@Override
	public float getTemperature(){
		return 1.0f;
	}
	
}
