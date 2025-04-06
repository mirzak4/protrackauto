package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

public enum ViolationType {

    SPEEDING(1, "Speeding"),
    RED_LIGHT(2, "Running red light"),
    PARKING(3, "Illegal parking"),
    SEATBELT(4, "No seatbelt"),
    DOCUMENTS(5, "Invalid documents"),
    OTHER(6, "Other");

    private final int code;
    private final String displayName;

    ViolationType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ViolationType fromCode(int code) {
        for (ViolationType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ViolationType code: " + code);
    }
}
