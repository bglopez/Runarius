import java.util.HashMap;
import java.util.Map;

public class ClientSidePacketHandlers {
    private static final Map<Opcodes.Server, IPacketHandler> packetHandlers = new HashMap<>();

    static {
        packetHandlers.put(Opcodes.Server.SV_MESSAGE, new SV_MessageHandler()::handle);
    }

    public static IPacketHandler getHandlerByOpcode(short opcode) {
        return packetHandlers.get(Opcodes.Server.valueOf(opcode));
    }
}
