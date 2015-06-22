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
	
	public Header createHeader(){
		Header header = new Header("SYN", 0, 0, 0);
		
		return header;
	}
	
	public void handleEvent(String event, Actor c) {
		 Header header = createHeader();
		 c.setState(new SYNSent());
		 System.out.println(event + "\t SetToState: SYSSend" + "\t" + header.toString());
	}
	
	//Current State:
	/*
	 * The protocol can perform the handshake, depending on the type created by the header
	 * - sending msgs
	 */
	
}
