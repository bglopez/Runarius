import java.util.HashMap;
import java.util.Map;

public class PacketHandlers {
    private static final Map<Opcodes.Client, IPacketHandler> packetHandlers = new HashMap<>();

    static {
        packetHandlers.put(Opcodes.Client.CL_SESSION, new CL_SessionHandler()::handle);
        packetHandlers.put(Opcodes.Client.CL_LOGIN, new CL_LoginHandler()::handle);
    }

    public static IPacketHandler getHandlerByOpcode(short opcode) {
        return packetHandlers.get(Opcodes.Client.valueOf(opcode));
    }
}
