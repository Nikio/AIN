package protocol;

import actor.Actor;

/*
 * This interface provides the necessary methods which have to be implemented by all states
 */

public interface Protocolstate {

	
	 public void handleEvent(String event, Actor c);
	 public void printCurrentState();
	 public Header createHeader(String event);
	 
	/*
	 * What are the possible States?
	 * 	- Connected
	 * 		- Send Txtmsg 
	 * 			- MsgWasAcknowledged
	 *  			- Wait for incoming Msg
	 *  			- Send a Msg
	 * 			- MsgHasBeenSended 
	 *  			- Wait until DACK is send or timer runs out
	 * 		- Close Connection (FIN-Handshake)
	 * 			--> FINSent
	 * 			--> FINRcvd
	 * 			--> NotConnected
	 * 	- NotConnected
	 * 		- Establish Connection (Handshake) 
	 * 			--> SYNSent
	 * 			--> SYNRcvd
	 */
	
	
}
