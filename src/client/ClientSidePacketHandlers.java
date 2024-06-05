import java.util.HashMap;
import java.util.Map;

public class ClientSidePacketHandlers {
    private static final Map<Opcodes.Server, IPacketHandler> packetHandlers = new HashMap<>();

    static {
        packetHandlers.put(Opcodes.Server.SV_MESSAGE, new SV_MessageHandler()::handle);
        packetHandlers.put(Opcodes.Server.SV_CLOSE_CONNECTION, new SV_CloseConnectionHandler()::handle);
        packetHandlers.put(Opcodes.Server.SV_LOGOUT_DENY, new SV_LogoutDeny()::handle);
        packetHandlers.put(Opcodes.Server.SV_FRIEND_LIST, new SV_FriendList()::handle);
        packetHandlers.put(Opcodes.Server.SV_FRIEND_STATUS_CHANGE, new SV_FriendStatusChange()::handle);
        packetHandlers.put(Opcodes.Server.SV_IGNORE_LIST, new SV_IgnoreList()::handle);
        packetHandlers.put(Opcodes.Server.SV_PRIVACY_SETTINGS, new SV_PrivacySettings()::handle);
        packetHandlers.put(Opcodes.Server.SV_FRIEND_MESSAGE, new SV_FriendMessage()::handle);
    }

    public static IPacketHandler getHandlerByOpcode(short opcode) {
        return packetHandlers.get(Opcodes.Server.valueOf(opcode));
    }
}
