package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class Driver {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String driverLicenseNumber;
    private LocalDate driverLicenseExpiry;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate hireDate;
    private String status;
    private String notes;

    public Driver() {
    }

    public Driver(Long id, String firstName, String lastName, LocalDate dateOfBirth, String driverLicenseNumber, LocalDate driverLicenseExpiry, String address, String phoneNumber, String email, LocalDate hireDate, String status, String notes) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverLicenseExpiry = driverLicenseExpiry;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.hireDate = hireDate;
        this.status = status;
        this.notes = notes;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public LocalDate getDriverLicenseExpiry() {
        return driverLicenseExpiry;
    }

    public void setDriverLicenseExpiry(LocalDate driverLicenseExpiry) {
        this.driverLicenseExpiry = driverLicenseExpiry;
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
