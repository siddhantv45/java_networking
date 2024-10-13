package main.networking.UDP;

import java.net.*;

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;
    private byte[] buf;

    public EchoClient() {
        try{
            socket= new DatagramSocket();
            address= InetAddress.getByName("localhost");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public String sendEcho(String msg){
        DatagramPacket packet = null;
        try{
            buf= msg.getBytes();
            packet= new DatagramPacket(buf,buf.length,address,4445);
            socket.send(packet);
            packet= new DatagramPacket(buf,buf.length);
            socket.receive(packet);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new String(packet.getData(),0, packet.getLength());
    }

    public void close(){
        socket.close();
    }
}
