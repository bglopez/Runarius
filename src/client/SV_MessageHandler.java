import java.net.Socket;

public class SV_MessageHandler implements IPacketHandler {
    @Override
    public void handle(Socket socket, Buffer data) {
        String message = data.getString();
        showServerMessage(message);
    }

    private void showServerMessage(String message) {
        // TODO: show message in client
        System.out.println(message);
    }
}
