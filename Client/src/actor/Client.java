package actor;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import protocol.Connected;
import protocol.DSend;
import protocol.Header;
import protocol.NotConnected;
import protocol.Protocolstate;


public class Client extends Actor{
	
	DatagramSocket socket; 
	
	public Client() throws Exception{
		//state is an attribute of the superclass Actor 
		//Actor is used to access the protocol functionality without specifying the user class
		this.state = new NotConnected();
		this.socket = new DatagramSocket();
	}
	
	//Method required to change the state of the client
	public void setState(Protocolstate s){
		this.state = s;
	}
	
	//Sends data to a server (currently localhost on port 50505),
	public void sendMsg(String header) throws Exception{
		byte[] sendData = new byte[1024];
		sendData = header.getBytes();
		
		InetAddress ip = InetAddress.getByName("localhost");
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 50505);
		socket.send(sendPacket);

	}
	
	//Reveives data send by the server, 
	//requires that the client has send a packet to the server first
	public String rcvMsg() throws Exception{
		
		byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		this.socket.receive(receivePacket);
		
		//Contains the received data in a string which is parsed later on by the header
		String data = new String(receivePacket.getData());
		System.out.println("FROM SERVER: " + data);
		
		return data;
	}
	
	//Establishes the connection with the server through a handshake process
	public Header establishConnection() throws Exception{
		//This variable is used to guide the Client through the Handshake 
		String strValue = "";
		 
		 while(true){
			this.state.printCurrentState();
			//handleEvent() analyses the rcvd heade type which is used to trigger the next action
			this.state.handleEvent(strValue, this);
			
			//Assures that the client listens to Server messages after each step 
			//until the handshake is completed
			if(this.state.getClass() != Connected.class){
				strValue = this.rcvMsg();
			}else{
				System.out.println("Connection established, Handshake completed");
				
				//This header is generated to pass on the SeqNr created int the handshake
				//Might be used for other information later on
				Header header = new Header();
				header.getValuesFromString(strValue);
				return header;
			}
		 }
	}
	
	public static void main(String args[]) throws Exception{
		//Erstelle Clientinstanzen
		Client c = new Client();
		
		//The state of a new client instance is set to "NotConnected"
		//Therefore we need to establish the Connection 
		Header header = c.establishConnection();
		
		//If the connection has been established we can send data
		if(c.state.getClass() == Connected.class){
			 //Trigger could be used here
			 //The header created in the "Connected" state has the type "DATA"
			 header = c.state.createHeader(header.toString());
			 c.sendMsg(header.toString() + "Hello World!");
			 c.setState(new DSend());
			 c.state.handleEvent("", c);
		}	
	}
}

