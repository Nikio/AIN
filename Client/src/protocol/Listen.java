package protocol;

import actor.Actor;

public class Listen implements Protocolstate {

	public void printCurrentState(){
		System.out.println("CurrentState: Listen");
	}
	
	public Header createHeader(){
		Header header = new Header("SYNACK", 0, 0, 0);
		
		return header;
	}
	
	
	public void handleEvent(String event, Actor c) {
		 Header header = createHeader();
		 c.setState(new SYNRcvd());
		 System.out.println(event + "\t SetToState: SYNRcvd" + "\t" + header.toString());
		 
	}

}
