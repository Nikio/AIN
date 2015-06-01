package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Client {
	public static void main(String args[]) throws Exception {
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		sendData = input.readLine().getBytes();

		DatagramSocket socket = new DatagramSocket();
		InetAddress ip = InetAddress.getByName("localhost");
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, 50505);
		socket.send(sendPacket);

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		socket.receive(receivePacket);

		String data = new String(receivePacket.getData());
		System.out.println("FROM SERVER: " + data);
		socket.close();
	}
}