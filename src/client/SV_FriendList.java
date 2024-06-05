import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_FriendList implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();

            // TODO: sort friendlist
            Logger.debug("SV_FriendList not yet implemented");

        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}