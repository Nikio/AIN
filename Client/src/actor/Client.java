package actor;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import protocol.NotConnected;
import protocol.Protocolstate;
import actor.Actor;


public class Client extends Actor{
	
	//private Protocolstate state;
	DatagramSocket socket; 
	
	public Client() throws Exception{
		this.state = new NotConnected();
		this.socket = new DatagramSocket();
	}
	
	public void setState(Protocolstate s){
		this.state = s;
	}
	
	public void sendMsg(String header, DatagramPacket p) throws Exception{
		byte[] sendData = new byte[1024];

//		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//		sendData = input.readLine().getBytes();
		sendData = header.getBytes();
		
		InetAddress ip = InetAddress.getByName("localhost");
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 50505);
		socket.send(sendPacket);

		
		//socket.close();
	}
	
	public DatagramPacket rcvMsg() throws Exception{
		
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		while(receivePacket.getLength() == 0){
			this.socket.receive(receivePacket);
		}
		
		String data = new String(receivePacket.getData());
		System.out.println("FROM SERVER: " + data);
		
		return receivePacket;
	}
	
	public static void main(String args[]) throws Exception{
		//Erstelle Clientinstanzen
		Client c = new Client();
		String event = c.state.createHeader().toString();
		DatagramPacket p = null;
		c.sendMsg(event, p);
		
		for(int i=0; i <10; i++){
			c.state.printCurrentState();
			p = c.rcvMsg();
			c.state.handleEvent(p.getData().toString(), c);
			event = c.state.createHeader().toString();
			c.sendMsg(event, p);
			
			if(event.contains("DATA")){
				break;
			}
		}	
		
	}
}

