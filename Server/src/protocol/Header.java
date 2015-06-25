package protocol;

/*
 * This class defines a header for the message protocol
 */

import java.util.HashMap;
import java.util.Random;

public class Header {

	/*
	 * Header (String: Typ+SeqNr+Msg-ID+Msg-PacketNr+GrößePayloads), Typ: 6 chars, SeqNr: 10 chars, Msg-ID: 5 chars, Packet-Nr: 3 chars, GrößePayloads: 3 chars
		→ 27 Zeichen
	◦ Typ (Welche? Verbindung: (SYN, ACK, SYNACK, FIN, ERR), Msg: (DATA, DACK,
		RESEND, ERR))
	◦ Größe des Payloads
	◦ Seq-Nr
	◦ (Zeit)
	◦ Msg-PacketNr (Wert X = Startwert, wird runtergezählt bis 0)
	◦ Msg-ID
	◦ Später: Schriftart, Farbe, usw....
	 */

	String type;
	int seqnr, msgPacketNr, msgId;

	//Default Constructor
	public Header(){
		
	}
	
	public Header(String type, int seqNr, int msgPacketNr, int msgId){
		if(type.compareTo("SYN") == 0){
			setMsgId(0);
			setMsgPacketNr(0);
			setSeqNr(generateNewSeqNr());
			setType("SYN");
		}else if(type.compareTo("SYNACK") == 0){
			setMsgId(0);
			setMsgPacketNr(0);
			seqNr += 1;
			setSeqNr(seqNr);
			setType("SYNACK");
		}else if(type.compareTo("ACK") == 0){
			setMsgId(0);
			setMsgPacketNr(0);
			seqNr += 1;
			setSeqNr(seqNr);
			setType("ACK");
		}else{
			//ERROR: Undefined Type
		}
	}

	//Generates a new SeqNr for SYN
	static int generateNewSeqNr(){


		Random rand = new Random();
		int startSeqNr = rand.nextInt((100000 - 1000) +1) + 1000;

		return startSeqNr;
	}

	public void getValuesFromString(String s){

		HashMap<Integer,String> headerValues = new HashMap<Integer,String>();
		int counter = 0;
		StringBuffer buffer = new StringBuffer();	
		
		for(int i=0; i < s.length(); i++){

			if(s.charAt(i) == '|'){
				headerValues.put(counter, buffer.toString());
				buffer = new StringBuffer();
				counter++;
				if(counter >= 4){
					this.assignValuesToAttributes(headerValues);
				}
			}else{
				buffer.append(s.charAt(i));
			}
		}

	}

	private void assignValuesToAttributes(HashMap<Integer,String> hmap){

		if(hmap.size() != 4){
			System.out.println("Error: Header is corrupted");
			return;
		}

		for(int i=0; i < hmap.size(); i++){
			String strValue = hmap.get(i);
			if(i== 0){
				this.type = strValue;
			}else if(i == 1){
				this.seqnr = Integer.valueOf(strValue);
			}else if(i == 2){
				this.msgPacketNr = Integer.valueOf(strValue);
			}else if(i == 3){
				this.msgId = Integer.valueOf(strValue);
			}
		}
	}



	//Creates a string with all Header attributes
	public String toString(){

		String header;
		StringBuffer buffer = new StringBuffer();

		buffer.append(this.type + "|");
		buffer.append(this.seqnr + "|");
		buffer.append(this.msgPacketNr + "|");
		buffer.append(this.msgId + "|");

		header = buffer.toString();
		//Sout for testing purposes
		//		System.out.print(header);

		return header;
	}
	
	public int size(){
		return toString().length();
	}

	//Getter & Setter 	
	void setType(String type){
		this.type = type;
	}

	public String getType(){

		return this.type;
	}

	void setSeqNr(int seqNr){
		this.seqnr = seqNr;
	}

	public int getSeqNr(){
		return this.seqnr;
	}

	void setMsgPacketNr(int packetNr){
		this.msgPacketNr = packetNr;
	}

	public int getMsgPacketNr(){
		return this.msgPacketNr;
	}

	void setMsgId(int msgId){
		this.msgId = msgId;
	}

	public int getMsgId(){
		return this.msgId;
	}
}
