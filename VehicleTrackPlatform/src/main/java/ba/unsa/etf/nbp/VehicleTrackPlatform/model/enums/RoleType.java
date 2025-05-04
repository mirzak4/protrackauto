package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleType {
    CLAIMS_ADJUSTER(1, "CLAIMS_ADJUSTER"),
    FIELD_TECHNICIAN(2, "FIELD_TECHNICIAN"),
    STATION_MANAGER(3, "STATION_MANAGER"),
    DRIVER(4, "DRIVER");

    private final int code;
    private final String displayName;

    RoleType(int code, String displayName) {
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
}
