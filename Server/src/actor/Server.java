package actor;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import protocol.Connected;
import protocol.Header;
import protocol.Listen;
import protocol.Protocolstate;

public class Server extends Actor{

	DatagramSocket socket;
	InetAddress client = null;
	int clientport;

	public Server() throws Exception{
		//state is an attribute of the superclass Actor 
		//Actor is used to access the protocol functionality without specifying the user class
		this.state = new Listen();
		
		this.socket = new DatagramSocket(50505);
		this.socket.setSoTimeout(10000);

	}

	//Method required to change the state of the client
	public void setState(Protocolstate s){
		this.state = s;
	}

	//Reveives data send by the server,
	public String rcvMsg() throws Exception{
		byte[] receiveData = new byte[1024];

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		socket.receive(receivePacket);

		//This sets the client parameters which are needed to reply to a request from the client 
		if(this.client == null){
			this.client = receivePacket.getAddress();
			this.clientport = receivePacket.getPort();
		}

		//Contains the received data in a string which is parsed later on by the header
		String data = new String(receivePacket.getData());
		System.out.println("FROM CLIENT: " + data);

		return data;
	}

	//Sends data to a client, 
	//server has to receive a packet from the client to use this method
	public void sendMsg(String header) throws Exception{
		byte[] sendData = new byte[1024];
		sendData = header.getBytes();
		
		//Checks if the client is known
		if(this.client != null){
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.client, this.clientport);
			socket.send(sendPacket);
		}else{
			System.out.println("Destinationaddress is not defined");
		}
	}

	//Established a connection with a client
	public void establisheConnectionWithClient(String header) throws Exception{

		//Contains the data which has been sent by the client
		String rcvd = header;
		
		while(true){
			/* Creating a new header based on the current state
			 * Step 1: Creation of a new empty header
			 * Step 2: Copying the header data of the received packet to the new header
			 * Step 3: Using the new header to follow up with the next step in the handshake
			 */
			Header h = new Header();
			h.getValuesFromString(rcvd);
			this.state.handleEvent(h.toString(), this);
			
			//Checks if the server received the ACK from the client
			if(this.state.getClass() == Connected.class){
				break;
			}
			
			//Waiting for the next msg of the client if SYN was not successful
			try{
				rcvd = this.rcvMsg();
			}catch(SocketTimeoutException e){
				System.out.println("Waiting for Client");
			}
		}
		System.out.println("Handshake completed");
	}

	public String extractPayload(String rcvd){
		
		Header header = new Header();
		header.getValuesFromString(rcvd);
		
		String payload = rcvd.substring(header.size());
		
		return payload;
	}

	public static void main(String args[]) throws Exception {
		
		Server s = new Server();
		int counter = 0;
		s.state.printCurrentState();

		while(true){
			String rcvd = "";
			try{
				rcvd = s.rcvMsg();
				if(s.state.getClass() == Listen.class){
					counter = 0;
					s.establisheConnectionWithClient(rcvd);
				}else if(s.state.getClass() == Connected.class){
		
					System.out.println(s.extractPayload(rcvd));
					Header header = new Header();
					header.getValuesFromString(rcvd);
					s.state.handleEvent(header.toString(), s);

				}
			}catch(SocketTimeoutException e){
				counter++;
				System.out.println("Waiting for Clientrequest");
				if(counter >= 3){
					System.out.println("Serverprocess terminated");
					return;
				}
			}
		}
	}
}

