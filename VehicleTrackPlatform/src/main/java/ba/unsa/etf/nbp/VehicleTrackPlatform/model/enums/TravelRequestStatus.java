package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TravelRequestStatus {
    PENDING(1, "Pending"),
    APPROVED(2, "Approved"),
    REJECTED(3, "Rejected"),
    IN_PROGRESS(4, "In Progress"),
    COMPLETED(5, "Completed"),
    CANCELLED(6, "Cancelled");

    private final int code;
    private final String displayName;

    TravelRequestStatus(int code, String displayName) {
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

    public static TravelRequestStatus fromCode(int code) {
        for (TravelRequestStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid RequestStatus code: " + code);
    }
}