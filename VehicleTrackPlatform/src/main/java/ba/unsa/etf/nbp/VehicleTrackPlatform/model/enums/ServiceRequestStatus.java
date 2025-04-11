package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ServiceRequestStatus {
    REQUESTED(1, "Requested"),
    SCHEDULED(2, "Scheduled"),
    IN_PROGRESS(3, "In Progress"),
    AWAITING_PARTS(4, "Awaiting Parts"),
    COMPLETED(5, "Completed"),
    CANCELED(6, "Canceled");

    private final int code;
    private final String displayName;

    ServiceRequestStatus(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    @JsonValue
    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ServiceRequestStatus fromCode(int code) {
        for (ServiceRequestStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ServiceRequestStatus code: " + code);
    }
}
