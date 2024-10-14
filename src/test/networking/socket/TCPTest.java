package networking.socket;

import main.networking.socket.TCPClient;
import main.networking.socket.TCPServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class TCPTest {
    private TCPClient tcpClient;
    private static int port;

    @BeforeClass
    public static void start() throws IOException, InterruptedException {
        //get an available port
        ServerSocket serverSocket= new ServerSocket(0);
        port = serverSocket.getLocalPort();
        serverSocket.close();

        Executors.newSingleThreadExecutor().submit(()->new TCPServer().start(port));
        Thread.sleep(2000);
    }

    @Before
    public void init(){
        tcpClient = new TCPClient();
        tcpClient.startConnection("127.0.0.1",port);
    }

    @Test
    public void startConnectionFromClientCall(){
        String response = tcpClient.sendMessage("hello server");
        assertEquals(response,"hello client");
    }

    @After
    public void endConnection(){
        tcpClient.stopConnection();
    }
}
