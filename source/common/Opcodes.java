package common;


import java.util.HashMap;
import java.util.Map;

public class Opcodes {
    public enum Client {
        CL_TRADE_CONFIRM_ACCEPT((short)104),
        CL_APPEARANCE((short)235),
        CL_BANK_CLOSE((short)212),
        CL_BANK_DEPOSIT((short)23),
        CL_BANK_WITHDRAW((short)22),
        CL_CAST_GROUND((short)158),
        CL_CAST_INVITEM((short)4),
        CL_CAST_NPC((short)50),
        CL_CAST_OBJECT((short)99),
        CL_CAST_PLAYER((short)229),
        CL_CAST_SELF((short)137),
        CL_CHAT((short)216),
        CL_CHOOSE_OPTION((short)116),
        CL_CLOSE_CONNECTION((short)31),
        CL_COMBAT_STYLE((short)29),
        CL_COMMAND((short)38),
        CL_DUEL_ACCEPT((short)176),
        CL_DUEL_CONFIRM_ACCEPT((short)77),
        CL_DUEL_DECLINE((short)197),
        CL_DUEL_ITEM_UPDATE((short)33),
        CL_DUEL_SETTINGS((short)8),
        CL_FRIEND_ADD((short)195),
        CL_FRIEND_REMOVE((short)167),
        CL_CAST_GROUNDITEM((short)249),
        CL_GROUNDITEM_TAKE((short)247),
        CL_IGNORE_ADD((short)132),
        CL_IGNORE_REMOVE((short)241),
        CL_INV_CMD((short)90),
        CL_INV_DROP((short)246),
        CL_INV_UNEQUIP((short)170),
        CL_INV_WEAR((short)169),
        CL_KNOWN_PLAYERS((short)163),
        CL_LOGIN((short)0),
        CL_LOGOUT((short)102),
        CL_NPC_ATTACK((short)190),
        CL_NPC_CMD((short)202),
        CL_NPC_TALK((short)153),
        CL_OBJECT_CMD1((short)136),
        CL_OBJECT_CMD2((short)79),
        CL_PACKET_EXCEPTION((short)3),
        CL_PING((short)67),
        CL_PLAYER_ATTACK((short)171),
        CL_PLAYER_DUEL((short)103),
        CL_PLAYER_FOLLOW((short)165),
        CL_PLAYER_TRADE((short)142),
        CL_PM((short)218),
        CL_PRAYER_OFF((short)254),
        CL_PRAYER_ON((short)60),
        CL_REPORT_ABUSE((short)206),
        CL_SESSION((short)32),
        CL_SETTINGS_GAME((short)111),
        CL_SETTINGS_PRIVACY((short)64),
        CL_SHOP_BUY((short)236),
        CL_SHOP_CLOSE((short)166),
        CL_SHOP_SELL((short)221),
        CL_SLEEP_WORD((short)45),
        CL_TRADE_ACCEPT((short)55),
        CL_TRADE_DECLINE((short)230),
        CL_TRADE_ITEM_UPDATE((short)46),
        CL_USEWITH_GROUNDITEM((short)53),
        CL_USEWITH_INVITEM((short)91),
        CL_USEWITH_NPC((short)135),
        CL_USEWITH_OBJECT((short)115),
        CL_USEWITH_PLAYER((short)113),
        CL_USEWITH_WALLOBJECT((short)161),
        CL_WALK((short)187),
        CL_WALK_ACTION((short)16),
        CL_WALL_OBJECT_COMMAND1((short)14),
        CL_WALL_OBJECT_COMMAND2((short)127),
        CL_CAST_WALLOBJECT((short)180),
        UNKNOWN((short)-1);

        public final short value;

        private Client(short value) {
            this.value = value;
        }

        private static final Map<Short, Client> map = new HashMap<>();

        static {
            for (Client command : Client.values()) {
                map.put(command.value, command);
            }
        }

        public static Client valueOf(short value) {
            return map.getOrDefault(value, UNKNOWN);
        }
    }

    public enum Server {
        SV_APPEARANCE((short)59),
        SV_BANK_CLOSE((short)203),
        SV_BANK_OPEN((short)42),
        SV_BANK_UPDATE((short)249),
        SV_CLOSE_CONNECTION((short)4),
        SV_DUEL_ACCEPTED((short)210),
        SV_DUEL_CLOSE((short)225),
        SV_DUEL_CONFIRM_OPEN((short)172),
        SV_DUEL_OPEN((short)176),
        SV_DUEL_OPPONENT_ACCEPTED((short)253),
        SV_DUEL_SETTINGS((short)30),
        SV_DUEL_UPDATE((short)6),
        SV_FRIEND_LIST((short)71),
        SV_FRIEND_MESSAGE((short)120),
        SV_FRIEND_STATUS_CHANGE((short)149),
        SV_GAME_SETTINGS((short)240),
        SV_IGNORE_LIST((short)109),
        SV_INVENTORY_ITEMS((short)53),
        SV_INVENTORY_ITEM_REMOVE((short)123),
        SV_INVENTORY_ITEM_UPDATE((short)90),
        SV_LOGOUT_DENY((short)183),
        SV_MESSAGE((short)131),
        SV_OPTION_LIST((short)245),
        SV_OPTION_LIST_CLOSE((short)252),
        SV_PLAYER_DIED((short)83),
        SV_PLAYER_QUEST_LIST((short)5),
        SV_PLAYER_STAT_EQUIPMENT_BONUS((short)153),
        SV_PLAYER_STAT_EXPERIENCE_UPDATE((short)33),
        SV_PLAYER_STAT_FATIGUE((short)114),
        SV_PLAYER_STAT_FATIGUE_ASLEEP((short)244),
        SV_PLAYER_STAT_LIST((short)156),
        SV_PLAYER_STAT_UPDATE((short)159),
        SV_PRAYER_STATUS((short)206),
        SV_PRIVACY_SETTINGS((short)51),
        SV_REGION_ENTITY_UPDATE((short)211),
        SV_REGION_GROUND_ITEMS((short)99),
        SV_REGION_NPCS((short)79),
        SV_REGION_NPC_UPDATE((short)104),
        SV_REGION_OBJECTS((short)48),
        SV_REGION_PLAYERS((short)191),
        SV_REGION_PLAYER_UPDATE((short)234),
        SV_REGION_WALL_OBJECTS((short)91),
        SV_SERVER_MESSAGE((short)89),
        SV_SERVER_MESSAGE_ONTOP((short)222),
        SV_SHOP_CLOSE((short)137),
        SV_SHOP_OPEN((short)101),
        SV_SLEEP_CLOSE((short)84),
        SV_SLEEP_INCORRECT((short)194),
        SV_SLEEP_OPEN((short)117),
        SV_SOUND((short)204),
        SV_SYSTEM_UPDATE((short)52),
        SV_TELEPORT_BUBBLE((short)36),
        SV_TRADE_CLOSE((short)128),
        SV_TRADE_CONFIRM_OPEN((short)20),
        SV_TRADE_ITEMS((short)97),
        SV_TRADE_OPEN((short)92),
        SV_TRADE_RECIPIENT_STATUS((short)162),
        SV_TRADE_STATUS((short)15),
        SV_WELCOME((short)182),
        SV_WORLD_INFO((short)25),
        UNKNOWN((short)-1);

        public final short value;

        private Server(short value) {
            this.value = value;
        }

        private static final Map<Short, Server> map = new HashMap<>();

        static {
            for (Server command : Server.values()) {
                map.put(command.value, command);
            }
        }

        public static Server valueOf(short value) {
            return map.getOrDefault(value, UNKNOWN);
        }
    }
}