package protocol;

import actor.Actor;
import protocol.*;

public interface Protocolstate {

	
	 public void handleEvent(String event, Actor c);
	 public void printCurrentState();
	 public Header createHeader();
	 
	/*
	 * What are the possible States?
	 * 	- Connected
	 * 		- Send Txtmsg
	 * 			- MsgWasAcknowledged
	 *  			- Wait for incoming Msg
	 *  			- Send a Msg
	 * 			- MsgHasBeenSended
	 *  			- Wait until DACK is send or timer runs out
	 * 		- Close Connection
	 * 	- NotConnected
	 * 		- Establish Connection (Handshake) 
	 */
	
	
}
