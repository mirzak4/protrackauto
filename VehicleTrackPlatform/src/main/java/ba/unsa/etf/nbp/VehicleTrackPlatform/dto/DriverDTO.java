package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;

import java.time.Instant;

public class DriverDTO extends AuditableEntity {
    private Long id;
    private Long userId;
    private String licenseNumber;
    private Instant licenseExpiry;
    private Instant employmentDate;

    private UserDTO user;

    public DriverDTO(){}

    public DriverDTO(
            Long id,
            UserDTO user,
            String licenseNumber,
            Instant licenseExpiry) {
        this.id = id;

        this.user = user;
        this.userId = user.getId();

        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
    }

    public DriverDTO(
            Long id,
            Long userId,
            String licenseNumber,
            Instant licenseExpiry) {
        this.id = id;
        this.userId = userId;
        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Instant getLicenseExpiry() {
        return licenseExpiry;
    }

    public void setLicenseExpiry(Instant licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }


    public Instant getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Instant employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
