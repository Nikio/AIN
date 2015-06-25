package protocol;

import actor.Actor;

public class DSend implements Protocolstate {

	@Override
	public void handleEvent(String event, Actor c){
		try{
			String rcvd = c.rcvMsg();
			if(rcvd.contains("DACK")){
				System.out.println(rcvd);
				c.setState(new Connected());
			}
		}catch(Exception e){
			System.out.println("Waiting for Server-DACK");
		}

	}

	@Override
	public void printCurrentState() {
		System.out.println("CurrentState: DSend");
	}

	@Override
	public Header createHeader(String event) {
		// TODO Auto-generated method stub
		return null;
	}

}
