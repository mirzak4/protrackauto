package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompanyType {

    INSURANCE_COMPANY(1, "Insurance Company"),
    SERVICE_PROVIDER(2, "Service Provider"),
    GAS_STATION(3, "Gas Station");
    private final int code;
    private final String displayName;

    CompanyType(int code, String displayName) {
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

    public static CompanyType fromCode(int code) {
        for (CompanyType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CompanyType code: " + code);
    }
}
