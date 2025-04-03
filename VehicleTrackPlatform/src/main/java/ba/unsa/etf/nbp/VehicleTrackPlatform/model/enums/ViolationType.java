package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

public enum ViolationType {

    SPEEDING("Speeding"),
    RED_LIGHT("Running red light"),
    PARKING("Illegal parking"),
    SEATBELT("No seatbelt"),
    DOCUMENTS("Invalid documents"),
    OTHER("Other");

    private final String description;

    ViolationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
