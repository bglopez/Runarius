import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_IgnoreList implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();

            // TODO: put member on ignore list
            Logger.debug("SV_IgnoreList not yet implemented");

        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}