public enum LoginResponse {
    SUCCESS(0, "Login successful", "Welcome!"),
    RECONNECT_SUCCESS(1, "Login successful", "Welcome back!"),
    MODERATOR_SUCCESS(25, "Moderator login successful", "Welcome, Moderator!"),
    INVALID_CREDENTIALS(3, "Invalid username or password.", "Try again, or create a new account"),
    ALREADY_LOGGED_IN(4, "That username is already logged in.", "Wait 60 seconds then retry"),
    CLIENT_UPDATED(5, "The client has been updated.", "Please reload this page"),
    SINGLE_CHARACTER(6, "You may only use 1 character at once.", "Your ip-address is already in use"),
    LOGIN_ATTEMPTS_EXCEEDED(7, "Login attempts exceeded!", "Please try again in 5 minutes"),
    SERVER_REJECTED_SESSION(8, "Error unable to login.", "Server rejected session"),
    LOGIN_SERVER_REJECTED_SESSION(9, "Error unable to login.", "Loginserver rejected session"),
    USERNAME_IN_USE(10, "That username is already in use.", "Wait 60 seconds then retry"),
    TEMP_DISABLED_ACCOUNT(11, "Account has been temporarily disabled", "for cheating or abuse"),
    PERM_DISABLED_ACCOUNT(12, "Account has been permanently disabled", "for cheating or abuse"),
    WORLD_FULL(14, "Sorry! This world is currently full.", "Please try a different world"),
    MEMBERS_ACCOUNT_REQUIRED(15, "You need a members account", "to login to this world"),
    NO_REPLY_FROM_LOGINSERVER(16, "Error - no reply from loginserver.", "Please try again"),
    FAILED_TO_DECODE_PROFILE(17, "Error - failed to decode profile.", "Contact customer support"),
    ACCOUNT_SUSPECTED_STOLEN(18, "Account suspected stolen.", "Press 'recover a locked account' on front page."),
    LOGINSERVER_MISMATCH(20, "Error - loginserver mismatch", "Please try a different world"),
    NOT_RS_CLASSIC_ACCOUNT(21, "Unable to login.", "That is not an RS-Classic account"),
    PASSWORD_SUSPECTED_STOLEN(22, "Password suspected stolen.", "Press 'change your password' on front page."),
    SERVER_TIMEOUT(-1, "Error unable to login.", "Server timed out"),
    UNKNOWN(-2, "Error unable to login.", "Unrecognised response code");

    private final int code;
    private final String message;
    private final String subMessage;

    LoginResponse(int code, String message, String subMessage) {
        this.code = code;
        this.message = message;
        this.subMessage = subMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public static LoginResponse fromCode(int code) {
        for (LoginResponse response : LoginResponse.values()) {
            if (response.code == code) {
                return response;
            }
        }
        return UNKNOWN;
    }
}
