import java.net.Socket;

@FunctionalInterface
public interface IPacketHandler {
    public void handle(Socket socket, Buffer data);
}
