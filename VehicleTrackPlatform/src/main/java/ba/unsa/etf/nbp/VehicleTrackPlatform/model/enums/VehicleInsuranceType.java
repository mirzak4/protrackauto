package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VehicleInsuranceType {

    LIABILITY(1, "Liability Insurance"),
    COLLISION(2, "Collision Insurance"),
    COMPREHENSIVE(3, "Comprehensive Insurance"),
    PERSONAL_INJURY_PROTECTION(4, "Personal Injury Protection (PIP)"),
    UNINSURED_MOTORIST(5, "Uninsured Motorist Coverage"),
    UNDERINSURED_MOTORIST(6, "Underinsured Motorist Coverage"),
    GAP(7, "GAP Insurance"),
    TOWING(8, "Towing and Labor Coverage"),
    RENTAL_REIMBURSEMENT(9, "Rental Reimbursement Coverage"),
    GLASS(10, "Glass Coverage"),
    ROADSIDE_ASSISTANCE(11, "Roadside Assistance"),
    OTHER(12, "Other");

    private final int code;
    private final String displayName;

    VehicleInsuranceType(int code, String displayName) {
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
