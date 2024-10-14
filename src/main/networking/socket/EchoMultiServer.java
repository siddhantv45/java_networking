package main.networking.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static jdk.jfr.internal.consumer.EventLog.stop;

public class EchoMultiServer {

    private ServerSocket serverSocket;

    public void start(int port){
        try{
            serverSocket = new ServerSocket(port);
            while (true){
                new EchoClientHandler(serverSocket.accept()).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            stop();
        }
    }
    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String running;
                while ((running = in.readLine()) != null) {
                    if (".".equals(running)) {
                        out.println("bye");
                        break;
                    }
                    out.println(running);
                }

                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        public static void main(String ...args){
            EchoMultiServer echoMultiServer = new EchoMultiServer();
            echoMultiServer.start(5555);
        }
}


