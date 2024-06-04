package client;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.net.Socket;

public class PacketOut {
    protected int packetMaxLength;

    public short dataLength;
    public short packetID;
    public ByteBuffer data;
    private byte[] packetBytes;
    private boolean isFinalized;
    

    public PacketOut(short packedID) {
        super();
        packetMaxLength = 5000;
        this.packetID = packedID;
        dataLength = 0;
        isFinalized = false;
    }

    public void putString(String str) {
        byte[] strBytes = str.getBytes();
        data.put(strBytes);
    }

    public boolean isFinalized() {
        return isFinalized;
    }

    public void finalize() {
        if (data != null && !isFinalized) {
            // Allocate a byte array for packed data based on remaining bytes in data buffer
            byte[] rawData = data.array();
            dataLength = (short)(rawData.length);
            short packedLen = (short)(dataLength + 4);
            packetBytes = new byte[packedLen];

            // write lenght
            packetBytes[0] = (byte)(packedLen >> 8 & 0xff);
            packetBytes[1] = (byte)(packedLen & 0xff);

            // write packed id
            packetBytes[2] = (byte)(packetID >> 8 & 0xff);
            packetBytes[3] = (byte)(packetID & 0xff);

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
