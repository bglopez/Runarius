import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_PrivacySettings implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();

            // TODO: set wich channels to block: chat/private/trade/duel
            Logger.debug("SV_PrivacySettings not yet implemented");

        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}