package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.Instant;
import java.time.LocalDate;

public class Driver {
    private Long id;
    private Long userId;
    private String licenseNumber;
    private LocalDate licenseExpiry;
    private LocalDate employmentDate;

    private User user;

    public Driver(
            Long id,
            User user,
            String licenseNumber,
            LocalDate licenseExpiry,
            LocalDate employmentDate) {
        this.id = id;

        this.user = user;
        this.userId = user.getId();

        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
        this.employmentDate = employmentDate;
    }

    public Driver(
            Long id,
            Long userId,
            String licenseNumber,
            LocalDate licenseExpiry,
            LocalDate employmentDate) {
        this.id = id;
        this.userId = userId;
        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
        this.employmentDate = employmentDate;
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

    public LocalDate getLicenseExpiry() {
        return licenseExpiry;
    }

    public void setLicenseExpiry(LocalDate licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }


    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
