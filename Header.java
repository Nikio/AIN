package protocol;

/*
 * This class defines a header for the message protocol
 */

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
	
//Creates a string with all Header attributes
	public String toString(){
		
		String header = new String();
		StringBuffer buffer = new StringBuffer(header);
		
		buffer.append(this.type);
		buffer.append(this.seqnr);
		buffer.append(this.msgPacketNr);
		buffer.append(this.msgId);
		
		//Sout for testing purposes
		System.out.print(header);
		
		return header;
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
