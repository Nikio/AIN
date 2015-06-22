package protocol;

import actor.Actor;

public class SYNRcvd implements Protocolstate {
	
	public void printCurrentState(){
		System.out.println("CurrentState: SYNRcvd");
	}
	
	public Header createHeader(){
		Header header = new Header("ACK", 1, 0, 0);
		
		return header;
	}
	
	public void handleEvent(String event, Actor c){
		//Header header = createHeader();
		if(event.contains("ACK")){
			c.setState(new Connected());
			System.out.println(event + "\t SetToState: Connected" + "\t");
		}else{
			System.out.println("ACK not received");
		}
	}

}
