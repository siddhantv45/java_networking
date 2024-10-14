package networking.socket;

import main.networking.socket.EchoClient;
import main.networking.socket.EchoServer;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class EchoTCPTest {
    private static int port;

    @BeforeClass
    public static void start() throws InterruptedException,IOException {

        ServerSocket s= new ServerSocket(0);
        port = s.getLocalPort();
        s.close();

        Executors.newSingleThreadExecutor()
                .submit(() -> new EchoServer().start(port));
        Thread.sleep(2000);
    }

    private EchoClient client= new EchoClient();

    @Before
    public void init(){
        client.startConnection("127.0.0.1",port);
    }

    @After
    public void tearDown(){
        client.endConnection();
    }

    @Test
    public void testClientServerEcho(){
        String s1= client.sendMessage("Hello");
        String s2=client.sendMessage("World");
        String s3=client.sendMessage(".");
        assertEquals(s1,"Hello");
        assertEquals(s2,"World");
        assertEquals(s3,"good bye");
    }
}
