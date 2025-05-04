package ba.unsa.etf.nbp.VehicleTrackPlatform.mappings;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.DriverDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;

public class DriverMapping {
    public static DriverDTO convertToDTO(Driver driver) {
        DriverDTO dto = driver.getUser() != null ? new DriverDTO(
                driver.getId(),
                UserMapping.convertToDTO(driver.getUser()),
                driver.getLicenseNumber(),
                driver.getLicenseExpiry()
        ) : new DriverDTO(
                driver.getId(),
                driver.getUserId(),
                driver.getLicenseNumber(),
                driver.getLicenseExpiry()
        );

        if (driver.getEmploymentDate() != null) {
            dto.setEmploymentDate(driver.getEmploymentDate());
        }
        dto.setCreatedAt(driver.getCreatedAt());
        dto.setCreatedBy(driver.getCreatedBy());
        dto.setModifiedAt(driver.getModifiedAt());
        dto.setModifiedBy(driver.getModifiedBy());

        return dto;
    }

    public static Driver convertToEntity(DriverDTO dto) {
        Driver driver = dto.getUser() != null ? new Driver(
                dto.getId(),
                UserMapping.convertToEntity(dto.getUser()),
                dto.getLicenseNumber(),
                dto.getLicenseExpiry()
        ) : new Driver(
                dto.getId(),
                dto.getUserId(),
                dto.getLicenseNumber(),
                dto.getLicenseExpiry()
        );

        if (dto.getEmploymentDate() != null) {
            driver.setEmploymentDate(dto.getEmploymentDate());
        }
        driver.setCreatedAt(driver.getCreatedAt());
        driver.setCreatedBy(driver.getCreatedBy());
        driver.setModifiedAt(driver.getModifiedAt());
        driver.setModifiedBy(driver.getModifiedBy());

        return driver;
    }
}
