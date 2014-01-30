package fi.laaperi.netcontroller.services;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import fi.laaperi.netcontroller.repository.Relay;
import fi.laaperi.netcontroller.repository.Sensor;

public class MockController implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(MockController.class);
	
	private static final String ERROR = "Invalid command";
	
	private volatile boolean running = false;
	
	private List<Sensor> sensors;
	private List<Relay> relays;
	
	public MockController(){
		//Sensors
		this.sensors = new ArrayList<Sensor>();
		
		Sensor s1 = new Sensor(1, "Sensor 1", 5.4f);
		Sensor s2 = new Sensor(2, "Sensor 2", 2.4f);
		Sensor s3 = new Sensor(3, "Sensor 3", 14.4f);
		this.sensors.add(s1);
		this.sensors.add(s2);
		this.sensors.add(s3);
		
		//Relays
		this.relays = new ArrayList<Relay>();
		Relay r1 = new Relay(11, "Relay 1", true);
		Relay r2 = new Relay(12, "Relay 2", false);
		Relay r3 = new Relay(13, "Relay 3", true);
		Relay r4 = new Relay(14, "Relay 4", false);
		this.relays.add(r1);
		this.relays.add(r2);
		this.relays.add(r3);
		this.relays.add(r4);
	}
	
	@Override
	public void run() {
		logger.info("Starting mock controller on port " + UDPService.PORT);
		try {
			startServer();
		} catch (Exception e) {
			logger.error("Failed to start mock controller on port " + UDPService.PORT);
			//e.printStackTrace();
		}
		logger.info("Mock controller terminated");
	}
	
	
	/**
	 * Terminates mock controller
	 */
	public void stop(){
		logger.info("Terminating mock controller");
		running = false;
	}
	
	
	/**
	 * 
	 * @throws Exception
	 */
	public void startServer() throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(UDPService.PORT);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		running = true;
		while(running){
			
			//Wait for packets
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String msg = new String(receivePacket.getData()).trim();
			logger.debug("Received: " + msg);
			
			//Reply
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String response = produceResponse(msg);
			sendData = response.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			logger.debug("Responded: " + response);
			
			//Init buffers
			Arrays.fill(receiveData, (byte)0);
			Arrays.fill(sendData, (byte)0);
		}
		serverSocket.close();
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String produceResponse(String msg){
		
		if(msg.startsWith("sensor")){
			return produceSensorData(msg);
		}
		
		if(msg.startsWith("relay")){
			return produceRelayData(msg);
		}
		
		return ERROR;
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String produceSensorData(String msg){
		
		Gson gson = new Gson();
		
		if(msg.equals(ControllerServiceImpl.GET_SENSORS)){
			return gson.toJson(sensors);
		}
		
		if(msg.startsWith(ControllerServiceImpl.GET_SENSOR)){
			String[] parts = msg.split(":");
			long id = Long.parseLong(parts[parts.length-1]);
			for(Sensor sensor : sensors){
				if(sensor.getId() == id){
					return gson.toJson(sensor);
				}
			}
		}
		
		return ERROR;
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String produceRelayData(String msg){
		
		Gson gson = new Gson();
		
		if(msg.equals(ControllerServiceImpl.GET_RELAYS)){
			return gson.toJson(relays);
		}
		
		if(msg.startsWith(ControllerServiceImpl.GET_RELAY)){
			String[] parts = msg.split(":");
			long id = Long.parseLong(parts[parts.length-1]);
			for(Relay relay : relays){
				if(relay.getId() == id){
					return gson.toJson(relay);
				}
			}
		}
		
		if(msg.startsWith(ControllerServiceImpl.SET_RELAY)){
			String[] parts = msg.split(":");
			long id = Long.parseLong(parts[parts.length-2]);
			String bool = parts[parts.length-1];
			for(Relay relay : relays){
				if(relay.getId() == id){
					if(bool.equals("true"))
						relay.setState(true);
					else
						relay.setState(false);
					return gson.toJson(relay);
				}
			}
		}
		return ERROR;
	}

}
