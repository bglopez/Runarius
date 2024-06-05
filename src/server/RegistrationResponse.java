public enum RegistrationResponse {
    SUCCESS(2, "Registration successful", "Welcome!"),
    USERNAME_TAKEN(3, "Username already taken.", "Please choose another username"),
    USERNAME_IN_USE(4, "That username is already in use.", "Wait 60 seconds then retry"),
    CLIENT_UPDATED(5, "The client has been updated.", "Please reload this page"),
    IP_ADDRESS_IN_USE(6, "You may only use 1 character at once.", "Your ip-address is already in use"),
    LOGIN_ATTEMPTS_EXCEEDED(7, "Login attempts exceeded!", "Please try again in 5 minutes"),
    TEMP_DISABLED_ACCOUNT(11, "Account has been temporarily disabled", "for cheating or abuse"),
    PERM_DISABLED_ACCOUNT(12, "Account has been permanently disabled", "for cheating or abuse"),
    SERVER_FULL(14, "Sorry! The server is currently full.", "Please try again later"),
    MEMBERS_ACCOUNT_REQUIRED(15, "You need a members account", "to login to this server"),
    SWITCH_TO_MEMBERS_SERVER(16, "Please login to a members server", "to access member-only features"),
    UNKNOWN(-1, "Error unable to create user.", "Unrecognised response code");

    private final int code;
    private final String message;
    private final String subMessage;

    RegistrationResponse(int code, String message, String subMessage) {
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

    public static RegistrationResponse fromCode(int code) {
        for (RegistrationResponse response : RegistrationResponse.values()) {
            if (response.code == code) {
                return response;
            }
        }
        return UNKNOWN;
    }
}