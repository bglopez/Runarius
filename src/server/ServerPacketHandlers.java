import java.util.HashMap;
import java.util.Map;

public class ServerPacketHandlers {
    private static final Map<Opcodes.Client, IPacketHandler> packetHandlers = new HashMap<>();

    static {
        packetHandlers.put(Opcodes.Client.CL_SESSION, new CL_SessionHandler()::handle);
        packetHandlers.put(Opcodes.Client.CL_LOGIN, new CL_LoginHandler()::handle);
        packetHandlers.put(Opcodes.Client.CL_REGISTER_ACCOUNT, new CL_RegisterHandler()::handle);
        packetHandlers.put(Opcodes.Client.CL_PING, new CL_PingHandler()::handle);
    }

    public static IPacketHandler getHandlerByOpcode(short opcode) {
        return packetHandlers.get(Opcodes.Client.valueOf(opcode));
    }
}
