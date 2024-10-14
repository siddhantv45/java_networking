package main.networking.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port){
        try {
            serverSocket= new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String running;
            while ((running=in.readLine())!=null){
                if(".".equals(running)){
                    out.println("good bye");
                    break;
                }
                out.println(running);
            }
        } catch (IOException e) {
            //exception handling
        }
    }

    public void stop(){
        try{
            serverSocket.close();
            clientSocket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            //exception handling
        }
    }

    public static void main(String ...args){
        EchoServer echoServer = new EchoServer();
        echoServer.start(4444);
    }
}
