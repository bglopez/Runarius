package server;

import java.io.*;
import java.net.*;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import common.Opcodes;
import common.Packet;

public class Server {
    private static final int PORT = 43594;
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) {
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
        } 
        finally {
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

