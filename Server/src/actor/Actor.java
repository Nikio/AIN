package actor;

import java.net.DatagramPacket;

import protocol.Protocolstate;

public class Actor {

	protected Protocolstate state;
	
	public void sendMsg(String header) throws Exception{}
	public String rcvMsg() throws Exception{return null;}
	public void setState(Protocolstate s){}
}
