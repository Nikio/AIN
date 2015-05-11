/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;



/**
 *
 * @author Niklas
 */
public class UPDConnection {
    
    DatagramSocket client, server;
    
    //Konstruktoren für Client und Server
    
    //send und receive gekapselt
    public void send(String input, DatagramSocket socket) throws IOException{
        byte[] byteContent = new byte[24];
        byteContent = input.getBytes();
        int byteContentLength = byteContent.length; 
        DatagramPacket packet = new DatagramPacket(byteContent, byteContentLength);
        try{
            socket.send(packet);
        }catch(IOException e){
            System.out.println("Error: Packet could not be sended");
        }
    }
    
    public String receive(DatagramSocket socket){
        
        byte[] receivedData = new byte[24];
        DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
        try{
            socket.receive(packet);
        }catch(IOException e){
            System.out.println("Couldn't receive the packet");
        }
        
        String s = receivedData.toString();
        
        return s;
    }
    
}
