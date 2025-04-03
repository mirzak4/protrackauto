package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

public enum DriverStatus {
    ACTIVE("Active"),
    AVAILABLE("Available"),
    ASSIGNED("Assigned to route"),
    ON_BREAK("On break"),
    ON_LEAVE("On leave"),
    ON_VACATION("On vacation"),
    SICK_LEAVE("Sick leave"),
    IN_TRAINING("In training"),
    SUSPENDED("Suspended"),
    TERMINATED("Terminated"),
    INACTIVE("Inactive");

    private final String displayName;

    DriverStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
