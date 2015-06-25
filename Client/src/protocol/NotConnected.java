package protocol;

import actor.Actor;

public class NotConnected implements Protocolstate {

	/*
	 * Statedefinition
	 * 
	 * - NotConnected
	 * 		- Establish Connection (Handshake) 
	 * 			- Client(SYN) --> Server  (Substate: NoConnection)
	 * 			- Server(SYNACK) --> Client (Substate: SynReceived)
	 * 			- Client(ACK) --> Server (Substate: Established --> State: Connected)
	 */
	
	public void printCurrentState(){
		System.out.println("CurrentState: NotConnected");
	}
	
	public Header createHeader(String event){
		
		Header header = new Header("SYN", 0, 0, 0);
		
		return header;
	}
	
	public void handleEvent(String event, Actor c) {
		 Header header = createHeader(event);
		 c.setState(new SYNSent());
		 try{
			 c.sendMsg(header.toString());
		 }catch(Exception e){
			 
		 };
		 System.out.println(event + "\t SetToState: SYNSent" + "\t" + header.toString());
	}
	
	//Current State:
	/*
	 * The protocol can perform the handshake, depending on the type created by the header
	 * - sending msgs
	 */
	
}
