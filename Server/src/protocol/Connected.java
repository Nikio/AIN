package protocol;

import actor.Actor;

public class Connected implements Protocolstate {

	/*
	 * Statedefinition
	 * 
	 * - Connected
	 * 		- Send Txtmsg
	 * 			- MsgWasAcknowledged
	 *  			- Wait for incoming Msg
	 *  			- Send a Msg
	 * 			- MsgHasBeenSended
	 *  			- Wait until DACK is send or timer runs out
	 * 		- Close Connection
	 */
	
	public void printCurrentState(){
		System.out.println("CurrentState: Connected");
	}
	
	public Header createHeader(){
		
		Header header = new Header("DATA",0,0,0);
		
		return header;
	}
	
	public void handleEvent(String event, Actor c){
		if(event.contains("FIN")){
			c.setState(new NotConnected());
			System.out.println(event + "\tSetToState: NotConnected");
		}else{
			
			System.out.println("Active Connection");
		}
	}
}
