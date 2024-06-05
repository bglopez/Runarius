import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_FriendStatusChange implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();

            // TODO: show messages <fiendname>  has logged in /  has logged out
            Logger.debug("SV_FriendStatusChange not yet implemented");

        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}