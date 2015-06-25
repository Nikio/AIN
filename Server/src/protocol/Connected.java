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
	
	public Header createHeader(String event){
		
		//Header header = new Header("DATA",0,0,0);
		Header header = new Header();
		header.getValuesFromString(event);
		header.type = "DATA";
		header.seqnr++;
		
		return header;
	}
	
	public void handleEvent(String event, Actor c){
		if(event.contains("FIN")){
			c.setState(new NotConnected());
			System.out.println(event + "\tSetToState: NotConnected");
		}else if(event.contains("DATA")){
			
			//Code for testing purposes!!!
			Header header = new Header();
			header.getValuesFromString(event);
			header.type = "DACK";
			header.seqnr++;
			try{
				c.sendMsg(header.toString() + event.substring(header.size()));
			}catch(Exception e){
				
			}
		}else if(event.contains("SYN")){
			System.out.println("Active Connection");
		}else{
			
		}
	}
}
