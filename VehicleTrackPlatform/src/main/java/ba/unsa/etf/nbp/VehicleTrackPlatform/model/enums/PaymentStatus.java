package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentStatus {

    UNPAID(1, "Unpaid"),
    PAID(2, "Paid"),
    PARTIAL(3, "Partial"),
    CANCELLED(4, "Cancelled"),
    PENDING(5, "Pending");

    private final int code;
    private final String displayName;

    PaymentStatus(int code, String displayName) {
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

    public static PaymentStatus fromCode(int code) {
        for (PaymentStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentStatus code: " + code);
    }
}
