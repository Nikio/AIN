package protocol;

import actor.Actor;

public class SYNSent implements Protocolstate {
	
	public void printCurrentState(){
		System.out.println("CurrentState: SYNSent");
	}

	public Header createHeader(){
		Header header = new Header("ACK", 1, 0, 0);
		
		return header;
	}
	
	
	public void handleEvent(String event, Actor c) {
		if(event.contains("SYNACK")){
		      Header header = createHeader();
		      c.setState(new Connected());
		     //SYNRcvd for testing purpose, 
		     //"SYNSent" can only be reached by a client, next state with server is "Connected"
		      System.out.println(event + "\t SetToState: Connected" + "\t" + header.toString());
		}else{
			System.out.println("Waiting for Server");
		}
		
	}

}
