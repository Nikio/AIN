package protocol;

import actor.Actor;

public class SYNSent implements Protocolstate {
	
	public void printCurrentState(){
		System.out.println("CurrentState: SYNSent");
	}

	public Header createHeader(String event){
		
		//Header header = new Header("ACK", 1, 0, 0);
		Header header = new Header();
		header.getValuesFromString(event);
		header.type = "ACK";
		header.seqnr++;
		
		return header;
	}
	
	
	public void handleEvent(String event, Actor c) {
		if(event.contains("SYNACK")){
			Header header = createHeader(event);
			c.setState(new Connected());
			try{
			   c.sendMsg(header.toString());
			}catch(Exception ex){
				 
			};
			//SYNRcvd for testing purpose, 
			//"SYNSent" can only be reached by a client, next state with server is "Connected"
			System.out.println(event + "\t SetToState: Connected" + "\t" + header.toString());
		}else{
			System.out.println("Waiting for Server");
		}
		 
	}

}
