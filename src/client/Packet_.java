import java.io.IOException;

public class Packet_ {

    public static int anIntArray537[] = new int[256];
    public static int anIntArray541[] = new int[256];
    public int readTries;
    public int maxReadTries;
    public int packetStart;
    public byte packetData[];
    protected int length;
    protected int packetMaxLength;
    protected boolean socketException;
    protected String socketExceptionMessage;
    protected int delay;
    private int packetCursorPos;

    public Packet_() {
        packetCursorPos = 3;
        packetMaxLength = 5000;
        socketException = false;
        socketExceptionMessage = "";
    }

    public void closeStream() {
    }

    public void readBytes(int len, byte buff[])
            throws IOException {
        readStreamBytes(len, 0, buff);
    }

    public int readPacket(byte buff[]) {
        try {
            readTries++;
            if (maxReadTries > 0 && readTries > maxReadTries) {
                socketException = true;
                socketExceptionMessage = "time-out";
                maxReadTries += maxReadTries;
                return 0;
            }
            if (length == 0 && availableStream() >= 2) {
                length = readStream();
                if (length >= 160)
                    length = (length - 160) * 256 + readStream();
            }
            if (length > 0 && availableStream() >= length) {
                if (length >= 160) {
                    readBytes(length, buff);
                } else {
                    buff[length - 1] = (byte) readStream();
                    if (length > 1)
                        readBytes(length - 1, buff);
                }
                int i = length;
                length = 0;
                readTries = 0;
                return i;
            }
        } catch (IOException ioexception) {
            socketException = true;
            socketExceptionMessage = ioexception.getMessage();
        }
        return 0;
    }

    public int availableStream()
            throws IOException {
        return 0;
    }

    public void readStreamBytes(int i, int j, byte abyte0[])
            throws IOException {
    }

    public boolean hasPacket() {
        return packetStart > 0;
    }

    public void writePacket(int i)
            throws IOException {
        if (socketException) {
            packetStart = 0;
            packetCursorPos = 3;
            socketException = false;
            throw new IOException(socketExceptionMessage);
        }
        delay++;
        if (delay < i)
            return;
        if (packetStart > 0) {
            delay = 0;
            writeStreamBytes(packetData, 0, packetStart);
        }
        packetStart = 0;
        packetCursorPos = 3;
    }

    public void sendPacket() {
        int packetLen = packetCursorPos - packetStart - 2;
    
        // Always use two bytes for the packet length
        packetData[packetStart] = (byte) (packetLen >> 8 & 0xff);
        packetData[packetStart + 1] = (byte) (packetLen & 0xff);
    
        if (packetMaxLength <= 10000) { // This keeps count of how many times we send each opcode, and how much bandwidth each opcode uses per session
            int k = packetData[packetStart + 2] & 0xff;
            anIntArray537[k]++;
            anIntArray541[k] += packetCursorPos - packetStart;
        }
        packetStart = packetCursorPos;
    }

    public void putBytes(byte src[], int srcPos, int len) {
        System.arraycopy(src, srcPos, packetData, packetCursorPos, len);
        packetCursorPos += len;
    }

    public void putLong(long l) {
        putInt((int) (l >> 32));
        putInt((int) (l & -1L));
    }

    public void newPacket(int command) {
        if (packetStart > (packetMaxLength * 4) / 5)
            try {
                writePacket(0);
            } catch (IOException ioexception) {
                socketException = true;
                socketExceptionMessage = ioexception.getMessage();
            }
        if (packetData == null)
            packetData = new byte[packetMaxLength];

        packetData[packetStart + 2] = (byte) command;
        packetData[packetStart + 3] = 0;
        packetCursorPos = packetStart + 3;
    }

    public void writeStreamBytes(byte abyte0[], int i, int j)
            throws IOException {
    }

    public int readStream()
            throws IOException {
        return 0;
    }

    public long getLong()
            throws IOException {
        long l = getShort();
        long l1 = getShort();
        long l2 = getShort();
        long l3 = getShort();
        return (l << 48) + (l1 << 32) + (l2 << 16) + l3;
    }

    public void putShort(int i) {
        packetData[packetCursorPos++] = (byte) (i >> 8);
        packetData[packetCursorPos++] = (byte) i;
    }

    public void putInt(int i) {
        packetData[packetCursorPos++] = (byte) (i >> 24);
        packetData[packetCursorPos++] = (byte) (i >> 16);
        packetData[packetCursorPos++] = (byte) (i >> 8);
        packetData[packetCursorPos++] = (byte) i;
    }

    public int getShort()
            throws IOException {
        int i = getByte();
        int j = getByte();
        return i * 256 + j;
    }

    public void putString(String s) {
        //s.getBytes(0, s.length(), packetData, packetEnd);
        System.arraycopy(s.getBytes(), 0, packetData, packetCursorPos, s.length());
        packetCursorPos += s.length();
    }

    public void putByte(int i) {
        packetData[packetCursorPos++] = (byte) i;
    }

    public int getByte()
            throws IOException {
        return readStream();
    }

    public void sendAndFlushPacket()
            throws IOException {
        sendPacket();
        writePacket(0);
    }
}
