import java.net.Socket;

public class SV_MessageHandler implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        String message = data.getString();
        // TODO: show message in client
        Logger.debug("SV_MessageHandler not yet implemented");
    }
}
