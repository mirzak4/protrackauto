package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

// To be deleted possibly
public enum DriverStatus {
    ACTIVE(1, "Active"),
    AVAILABLE(2, "Available"),
    ASSIGNED(3, "Assigned to route"),
    ON_BREAK(4, "On break"),
    ON_LEAVE(5, "On leave"),
    ON_VACATION(6, "On vacation"),
    SICK_LEAVE(7, "Sick leave"),
    IN_TRAINING(8, "In training"),
    SUSPENDED(9, "Suspended"),
    TERMINATED(10, "Terminated"),
    INACTIVE(11, "Inactive");

    private final int code;
    private final String displayName;

    DriverStatus(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DriverStatus fromCode(int code) {
        for (DriverStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid DriverStatus code: " + code);
    }
}
