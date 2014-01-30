package fi.laaperi.netcontroller.services;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UDPService {
	
	private static final Logger logger = LoggerFactory.getLogger(UDPService.class);
	
	public static final int PORT = 9876;
	public static final String IP = "localhost";
	public static final int TIMEOUT = 1000;
	private static final boolean MOCK = true;
	
	private ExecutorService executorService = Executors.newCachedThreadPool();
	private MockController mockController;
	
	public UDPService(){
		if(MOCK){
			mockController = new MockController();
			executorService.execute(mockController);
		}
	}
	
	public void stopMockController(){
		mockController.stop();
	}
	
	public String sendMessage(String msg) {
		logger.info("Sending message: " + msg);
		
		String response = "";
		try {
			DatagramSocket socket = new DatagramSocket();
			socket.setSoTimeout(TIMEOUT);	//Set timeout
			InetAddress IPAddress = InetAddress.getByName(IP);
			
			byte[] sendData = new byte[1024];
			sendData = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PORT);
			socket.send(sendPacket);
			
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			
			response = new String(receivePacket.getData());
			
			logger.info("Message received: " + response);
			socket.close();
			
		} catch (SocketTimeoutException e) {
			logger.error("Controller ureachable");
		} catch (Exception e){
			e.printStackTrace();
		}
		return response.trim();
	}
	
}
