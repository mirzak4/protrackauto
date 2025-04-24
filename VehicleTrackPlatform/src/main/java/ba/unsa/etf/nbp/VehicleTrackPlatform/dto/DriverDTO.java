package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.DriverStatus;

import java.time.LocalDate;

public class DriverDTO extends AuditableEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String licenseNumber;
    private LocalDate licenseExpiry;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate employmentDate;

    public DriverDTO() {
    }

    public DriverDTO(
            Long id,
            String firstName,
            String lastName,
            LocalDate birthDate,
            String licenseNumber,
            LocalDate licenseExpiry,
            String address,
            String phoneNumber,
            String email,
            LocalDate employmentDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.licenseNumber = licenseNumber;
        this.licenseExpiry = licenseExpiry;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employmentDate = employmentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

}
