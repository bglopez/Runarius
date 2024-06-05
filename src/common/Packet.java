import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Packet {
    private ByteBuffer buffer;
    public short packetLength;
    public short opcode;
    public ByteBuffer data;
    private boolean isFinalized = false;
    private byte[] packetBytes;

    public Packet(InputStream inputStream) throws IOException {
        int numBytesAvailable = inputStream.available();
        if (numBytesAvailable < 4) {
            throw new BufferUnderflowException();
        }
        buffer = ByteBuffer.allocate(numBytesAvailable);
        packetLength = buffer.getShort();
        opcode = buffer.getShort();
        data = buffer.slice();
    }

    // Input
    public String getString() throws BufferUnderflowException {
        if (data.remaining() < 1) {
            throw new BufferUnderflowException();
        }

        // Read the string length (assuming it's encoded as a short)
        short stringLength = data.getShort();

        if (data.remaining() < stringLength) {
            throw new BufferUnderflowException();
        }

        // Allocate a byte array to hold the string data
        byte[] stringBytes = new byte[stringLength];

        // Read the string bytes from the data buffer
        data.get(stringBytes);

        // Decode the string bytes based on your encoding
        return new String(stringBytes, StandardCharsets.US_ASCII);
    }

    // Output

    public void putString(String str) {
        byte[] strBytes = str.getBytes();
        // Write the string length
        data.putShort((short)strBytes.length);
        // Write the string content
        data.put(strBytes);
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void finalize() {
        if (data != null && !isFinalized) {
            // Allocate a byte array for packed data based on remaining bytes in data buffer
            byte[] rawData = data.array();
            short dataLength = (short)(rawData.length);
            short packedLen = (short)(dataLength + 4);
            packetBytes = new byte[packedLen];

            // write lenght
            packetBytes[0] = (byte)(packedLen >> 8 & 0xff);
            packetBytes[1] = (byte)(packedLen & 0xff);

            // write opcode
            packetBytes[2] = (byte)(opcode >> 8 & 0xff);
            packetBytes[3] = (byte)(opcode & 0xff);

            // write data
            for (int i = 0; i < dataLength; i++) {
              packetBytes[i+4] = rawData[i];
            }

            isFinalized = true;
        }
    }

    public void Send(Socket socket) throws IOException {
        try (OutputStream outputStream = socket.getOutputStream()) {
            if (!isFinalized) {
                finalize();
            }
            outputStream.write(packetBytes);
            outputStream.flush();
        }
    }
}
