package protocol;

/*
 * This class defines a header for the message protocol
 */

public class Header {

	/*
	 * Header (String: Typ+SeqNr+Msg-ID+Msg-PacketNr+GrößePayloads), Typ: 6 chars, SeqNr: 10 chars, Msg-ID: 5 chars, Packet-Nr: 3 chars, GrößePayloads: 3 chars → 27 Zeichen
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

	int type, seqnr, msgId, msgPacketNr, msgPayload;

	// Default Constructor
	public Header() {

	}

	// Creates a string with all Header attributes
	public String toString() {

		String header = new String();
		StringBuffer buffer = new StringBuffer(header);

		buffer.append(this.type);
		buffer.append(this.seqnr);
		buffer.append(this.msgId);
		buffer.append(this.msgPacketNr);
		buffer.append(this.msgPayload);

		// Sout for testing purposes
		System.out.print(header);

		return header;
	}

	// Getter & Setter

	public int getType() {
		return this.type;
	}

	void setType(int type) {
		this.type = type;
	}

	public int getSeqNr() {
		return this.seqnr;
	}

	void setSeqNr(int seqNr) {
		this.seqnr = seqNr;
	}

	public int getMsgId() {
		return this.msgId;
	}

	void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getMsgPacketNr() {
		return this.msgPacketNr;
	}

	void setMsgPacketNr(int packetNr) {
		this.msgPacketNr = packetNr;
	}

	public int getPayload() {
		return this.msgPayload;
	}

	void setPayload(int msgPayload) {
		this.msgPayload = msgPayload;
	}
}