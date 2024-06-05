import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_LogoutDeny implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();

            // TODO: prevent client from logging out
            Logger.debug("SV_LogoutDeny not yet implemented");

        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}