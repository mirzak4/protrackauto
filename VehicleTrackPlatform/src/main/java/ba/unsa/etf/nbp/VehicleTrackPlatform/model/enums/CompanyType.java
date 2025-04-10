package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

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
