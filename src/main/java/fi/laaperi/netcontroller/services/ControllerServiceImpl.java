package fi.laaperi.netcontroller.services;

import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControllerServiceImpl implements ControllerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerServiceImpl.class);
	
	@Autowired
	UDPService connection;
	
	@Override
	public String getSensors(){
		return "Sensor1";
	}
	
	@Override
	public float getTemperature(){
		
		String response = "";
		try {
			response = connection.sendMessage("test");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		float temp;
		try{
			temp = Float.parseFloat(response);
		} catch (NumberFormatException e){
			return 0;
		}
		return temp;
	}
	
}
