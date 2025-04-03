package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

public enum PaymentStatus {

    UNPAID("Unpaid"),
    PAID("Paid"),
    PARTIAL("Partial"),
    CANCELLED("Cancelled"),
    PENDING("Pending");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
