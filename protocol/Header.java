package protocol;

/*
 * This class defines a header for the message protocol
 */

public class Header {

	/*
	 * Header (String: Typ+SeqNr+Msg-ID+Msg-PacketNr+GrößePayloads), Typ: 1 chars, SeqNr: 10 chars, Msg-ID: 5 chars, Packet-Nr: 3 chars, GrößePayloads: 3 chars → 22 Zeichen
	 * 
	 * Typ (Welche? Verbindung: (SYN, ACK, SYNACK, FIN, ERR), Msg: (DATA, DACK, RESEND, ERR))
	 * 
	 * Seq-Nr
	 * 
	 * (Zeit)
	 * 
	 * Msg-ID
	 * 
	 * Msg-PacketNr (Wert X = Startwert, wird runtergezählt bis 0)
	 * 
	 * Größe des Payloads
	 * 
	 * Später: Schriftart, Farbe, usw....
	 */

	int type, seqNr, msgID, msgPacketNr, payloadSize;

	// Default Constructor
	public Header() {

	}

	public Header(int type, int seqNr, int msgID, int msgPacketNr, int payloadSize) {
		setType(type);
		setSeqNr(seqNr);
		setMsgID(msgID);
		setMsgPacketNr(msgPacketNr);
		setPayloadSize(payloadSize);
	}

	public Header(String data) {
		setType(Integer.parseInt(data.substring(0, 1)));
		setSeqNr(Integer.parseInt(data.substring(1, 11)));
		setMsgID(Integer.parseInt(data.substring(11, 16)));
		setMsgPacketNr(Integer.parseInt(data.substring(16, 19)));
		setPayloadSize(Integer.parseInt(data.substring(19, 21)));
	}

	// Creates a string with all Header attributes
	public String toString() {
		String header = new String();
		StringBuffer buffer = new StringBuffer(header);

		buffer.append(this.type);
		buffer.append(this.seqNr);
		buffer.append(this.msgID);
		buffer.append(this.msgPacketNr);
		buffer.append(this.payloadSize);

		// Sout for testing purposes
		System.out.print(header);

		return header;
	}

	// Getter & Setter

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSeqNr() {
		return this.seqNr;
	}

	public void setSeqNr(int seqNr) {
		this.seqNr = seqNr;
	}

	public int getMsgID() {
		return this.msgID;
	}

	public void setMsgID(int msgID) {
		this.msgID = msgID;
	}

	public int getMsgPacketNr() {
		return this.msgPacketNr;
	}

	public void setMsgPacketNr(int packetNr) {
		this.msgPacketNr = packetNr;
	}

	public int getPayloadSize() {
		return this.payloadSize;
	}

	public void setPayloadSize(int msgPayload) {
		this.payloadSize = msgPayload;
	}
}