package main.networking.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port){
        try{
            serverSocket = new ServerSocket(port);
            clientSocket= serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String running= in.readLine();
            if("hello server".equals(running)){
                out.println("hello client");
            }else{
                out.println("unrecognized greeting");
            }
        }catch (Exception e){
            //exception handling
        }
    }

    public void stop(){
        try{
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }
        catch (Exception e){
            //exception handling
        }
    }

    public static void main(String ...args){
        TCPServer tcpServer= new TCPServer();
        tcpServer.start(6666);
    }
}
