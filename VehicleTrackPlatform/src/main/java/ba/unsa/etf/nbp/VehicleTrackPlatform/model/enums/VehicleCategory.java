package ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums;

import java.util.Arrays;

public enum VehicleCategory {
    TRAVEL_CAR(1, "Travel Car"),
    TRUCK(2, "Truck"),
    MOTORCYCLE(3, "Motorcycle"),
    BUS(4, "Bus"),
    VAN(5, "Van"),
    SUV(6, "SUV"),
    PICKUP(7, "Pickup"),
    TRACTOR(8, "Tractor"),
    SCOOTER(9, "Scooter"),
    BICYCLE(10, "Bicycle");

    private final int code;
    private final String displayName;

    VehicleCategory(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public int getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VehicleCategory parse(int category) {
        return Arrays.stream(values())
                .filter(v -> v.code == category)
                .findFirst()
                .orElseThrow();
    }
}

