package actor;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import protocol.Listen;
import protocol.Protocolstate;

public class Server extends Actor{
	
	//private Protocolstate state;
	DatagramSocket socket;
	InetAddress client = null;
	int clientport;
	
	public Server() throws Exception{
		this.state = new Listen();
		this.socket = new DatagramSocket(50505);
	}
	
	public void setState(Protocolstate s){
		this.state = s;
	}
	
	public String rcvMsg() throws Exception{
		    byte[] receiveData = new byte[1024];
		
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			
			if(this.client == null){
				this.client = receivePacket.getAddress();
				this.clientport = receivePacket.getPort();
			}
			
			String data = new String(receivePacket.getData());
			System.out.println("FROM CLIENT: " + data);
			
			return data;
	}
	
	public void sendMsg(String header, DatagramPacket p) throws Exception{
		byte[] sendData = new byte[1024];
		sendData = header.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.client, this.clientport);
		socket.send(sendPacket);
	}
	
	
    public static void main(String args[]) throws Exception {
		Server s = new Server();
		
		DatagramPacket p = null;
		for(int i= 0; i < 4; i++){
			s.state.printCurrentState();
			String rcvd = s.rcvMsg();
			System.out.println(rcvd);
			if(rcvd.contains("DATA")){ 
				break;
			}
		    s.state.printCurrentState();
			String event = s.state.createHeader().toString();
			s.state.handleEvent(rcvd, s);
			s.sendMsg(event, p);		
			
		}
		
		System.out.println("Handshake completed");
		s.sendMsg("FIN", p);
	}
}

