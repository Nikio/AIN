package protocol;

import actor.Actor;

public class Listen implements Protocolstate {

	public void printCurrentState(){
		System.out.println("CurrentState: Listen");
	}

	public Header createHeader(String event){

		//Header header = new Header("SYNACK", 0, 0, 0);
		Header header = new Header();
		header.getValuesFromString(event);
		header.type = "SYNACK";
		header.seqnr++;

		return header;
	}


	public void handleEvent(String event, Actor c) {
		Header header = createHeader(event);
		c.setState(new SYNRcvd());
		try{
			c.sendMsg(header.toString());
		}catch(Exception e){

		};
		System.out.println(event + "\t SetToState: SYNRcvd" + "\t" + header.toString());
	}

}
