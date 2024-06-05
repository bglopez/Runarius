import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SV_CloseConnectionHandler implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        try {

            OutputStream outStream = socket.getOutputStream();
            Buffer out = new Buffer();
            out.putShort(Opcodes.Client.CL_CLOSE_CONNECTION.value);
            outStream.write(out.toArrayWithLen());
            outStream.flush();
        } catch (IOException ex) {
            Logger.error(ex.getMessage());
        }
    }
}