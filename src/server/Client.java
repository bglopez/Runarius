import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

public class Client implements Runnable {
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()) {
            while (true) {
                // Check if at least 4 bytes are available to read the length and opcode
                if (inputStream.available() >= 4) {
                    byte[] lengthOpcodeBuffer = inputStream.readNBytes(4);
                    ByteBuffer headerBuffer = ByteBuffer.wrap(lengthOpcodeBuffer);
                    short length = headerBuffer.getShort();
                    short opcode = headerBuffer.getShort();

                    // Ensure that the full packet data is available
                    if (inputStream.available() >= length - 4) {
                        byte[] dataBuffer = inputStream.readNBytes(length - 2); // read length without length-bytes (2)
                        Buffer data = new Buffer(dataBuffer);

                        IPacketHandler handler = ServerPacketHandlers.getHandlerByOpcode(opcode);

                        if (handler != null) {
                            handler.handle(socket, data);
                        } else {
                            System.out.println("Unknown opcode: " + opcode);
                        }
                    } else {
                        Thread.sleep(50); // Wait for more data to arrive
                    }
                } else {
                    Thread.sleep(50); // Wait for more data to arrive
                }
            }
        } catch (IOException ex) {
            System.out.println("Client disconnected or connection reset: " + ex.getMessage());
        } catch (BufferUnderflowException ex) {
            System.out.println("Got invalid packet: " + ex.getMessage());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted: " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Failed to close socket: " + ex.getMessage());
            }
            System.out.println("Client disconnected");
        }
    }
}