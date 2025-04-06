package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

public enum ServiceType {

    REGULAR_SERVICE(1, "Regular Service Check"),
    TECHNICAL_INSPECTION(2, "Technical Inspection for Registration");

    private final int code;
    private final String displayName;

    ServiceType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ServiceType fromCode(int code) {
        for (ServiceType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid ServiceType code: " + code);
    }
}
