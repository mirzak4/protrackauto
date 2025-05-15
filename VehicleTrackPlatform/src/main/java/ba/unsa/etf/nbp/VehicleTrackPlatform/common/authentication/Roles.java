package ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication;

import org.springframework.stereotype.Component;

@Component("roles")
public class Roles {
    public static final String ADMIN = "ADMIN";
    public static final String CLAIMS_ADJUSTER = "CLAIMS_ADJUSTER";
    public static final String FIELD_TECHNICIAN = "FIELD_TECHNICIAN";
    public static final String STATION_MANAGER = "STATION_MANAGER";
    public static final String DRIVER = "DRIVER";

}
