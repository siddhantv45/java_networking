package main.networking.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public void startConnection(String ip, int port){
        try{
            clientSocket= new Socket(ip,port);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (Exception e){
            //exception handling
        }
    }

    public String sendMessage(String msg){
        try {
            out.println(msg);
            return in.readLine();
        }
        catch (Exception e){
            return null;
        }
    }

    public void stopConnection(){
        try{
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            //exception handling
        }
    }
}
