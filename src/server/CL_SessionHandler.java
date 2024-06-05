import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class CL_SessionHandler implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {
            OutputStream outStream = socket.getOutputStream();
            
            String username = data.getString();
            long sessionId = genSessionId(username);
            
            Buffer out = new Buffer();
            out.putLong(sessionId);
            
            outStream.write(out.toArray());
            outStream.flush();
        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }

    private long genSessionId(String data) {
        return System.currentTimeMillis();
    }
}
