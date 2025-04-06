package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

public enum VehicleBodyType {
    SEDAN(1, "Sedan"),
    HATCHBACK(2, "Hatchback"),
    SUV(3, "SUV"),
    COUPE(4, "Coupe"),
    CONVERTIBLE(5, "Convertible"),
    WAGON(6, "Wagon"),
    PICKUP(7, "Pickup Truck"),
    VAN(8, "Van"),
    MINIVAN(9, "Minivan"),
    ROADSTER(10, "Roadster"),
    JEEP(11, "Jeep"),
    LIMOUSINE(12, "Limousine"),
    TRUCK(13, "Truck"),
    BUS(14, "Bus"),
    MOTORCYCLE(15, "Motorcycle");

    private final int code;
    private final String displayName;

    VehicleBodyType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VehicleBodyType fromCode(int code) {
        for (VehicleBodyType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid VehicleBodyType code: " + code);
    }
}

