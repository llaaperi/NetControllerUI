package fi.laaperi.netcontroller.services;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockController implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(MockController.class);
	
	private volatile boolean running = false;
	
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
			String msg = new String(receivePacket.getData());
			logger.info("Received: " + msg);
			
			//Reply
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String response = produceResponse(msg);
			sendData = response.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			logger.info("Responded: " + response);
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
		
		if(msg.startsWith("switch")){
			return produceSwitchData(msg);
		}
		
		return "reply";
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String produceSensorData(String msg){
		if(msg.equals("sensor0")){
			return "8.5";
		}
		if(msg.equals("sensor0")){
			return "8.5";
		}
		return "";
	}
	
	
	/**
	 * 
	 * @param msg
	 * @return
	 */
	private String produceSwitchData(String msg){
		return "";
	}

}
