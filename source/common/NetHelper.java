package common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class NetHelper {
    public static Socket createSocket(String ip, int port) throws IOException {
        Socket socket;

        socket = new Socket(InetAddress.getByName(ip), port);
        socket.setSoTimeout(30000);
        socket.setTcpNoDelay(true);
        return socket;
    }
}
