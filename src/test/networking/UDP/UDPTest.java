package networking.UDP;

import main.networking.UDP.EchoClient;
import main.networking.UDP.EchoServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.SocketException;

import static org.junit.Assert.*;

public class UDPTest {
    private EchoClient echoClient;

    @Before
    public void setup() throws SocketException {
        new EchoServer().start();
        echoClient = new EchoClient();
    }

    @Test
    public void sendPacket(){
        String echo = echoClient.sendEcho("Hello Server");
        assertEquals("Hello Server", echo);
        echo = echoClient.sendEcho("Server is working");
        assertFalse(echo.equals("Hello"));
    }

    @After
    public void tearDown(){
        stopEchoServer();
        echoClient.close();
    }

    private void stopEchoServer() {
        echoClient.sendEcho("end");
    }
}
