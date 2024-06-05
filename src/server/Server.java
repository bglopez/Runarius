import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 43594;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
        int launchResult = launchFromTerminal(args);

        if (launchResult == 1) {
            return; // Launched successfully, exit current instance
        } else if (launchResult == -1) {
            System.err.println("Error launching terminal. Exiting.");
            return; // Error occurred, exit
        }

        // Proceed to run the server if not launched from terminal
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                threadPool.execute(new ClientHandler(socket));
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static int launchFromTerminal(String[] args) {
        boolean wasLaunched = false;
        boolean noConsole = false;

        for (String arg : args) {
            if (arg.equals("-waslaunched")) {
                wasLaunched = true;
            }
            if (arg.equals("-noconsole")) {
                noConsole = true;
            }
        }

        if (wasLaunched || noConsole) {
            return 0; // Already launched from terminal or no console needed
        }

        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder = new ProcessBuilder();

        try {
            if (os.contains("win")) {
                // Windows command to open a new command prompt and run the server
                processBuilder.command("cmd.exe", "/c", "start", "cmd.exe", "/k", "java -jar server.jar -waslaunched");
            } else if (os.contains("mac")) {
                // macOS command to open a new terminal and run the server
                processBuilder.command("osascript", "-e", "tell application \"Terminal\" to do script \"java -jar server.jar -waslaunched\"");
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux command to open a new terminal and run the server
                processBuilder.command("x-terminal-emulator", "-e", "java -jar server.jar -waslaunched");
            } else {
                System.out.println("Unsupported OS: " + os);
                return -1; // Unsupported OS
            }

            processBuilder.start();
            return 1; // Launched successfully
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Error occurred
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()) {
            while (true) {
                Packet packet = new Packet(inputStream);
                // Handle based on opcodes
                switch (Opcodes.Client.values()[packet.opcode]) {
                    case CL_SESSION:
                        String username = packet.getString();
                        System.out.println("Username: " + username);
                        long sessionId = genSessionId(packet.data);
                        byte[] sessionIdBuff = longToByteArray(sessionId);
                        outputStream.write(sessionIdBuff);
                        outputStream.flush();
                        break;
                    default:
                        System.out.println("Unknown opcode: " + packet.opcode);
                }
            }
        } catch (IOException ex) {
            System.out.println("Client disconnected or connection reset: " + ex.getMessage());
        } catch (BufferUnderflowException ex) {
            System.out.println("Got invalid packet: " + ex.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Failed to close socket: " + ex.getMessage());
            }
            System.out.println("Client disconnected");
        }
    }

    private long genSessionId(ByteBuffer data) {
        // Replace with your logic to generate a unique session ID
        // This is a simplified example, you might want to use a cryptographically secure random number generator
        return System.currentTimeMillis();
    }

    private static byte[] longToByteArray(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(value);
        return buffer.array();
    }
}
