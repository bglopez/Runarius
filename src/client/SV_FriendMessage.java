import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_FriendMessage implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();

            // TODO: show message from friend
            Logger.debug("SV_FriendMessage not yet implemented");

        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}