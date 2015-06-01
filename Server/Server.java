package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Server {
	public static void main(String args[]) throws Exception {
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		DatagramSocket socket = new DatagramSocket(50505);

		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			String data = new String(receivePacket.getData());

			System.out.println("FROM CLIENT: " + data);

			InetAddress ip = receivePacket.getAddress();
			int port = receivePacket.getPort();
			sendData = data.toUpperCase().getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
			socket.send(sendPacket);
		}
	}
}