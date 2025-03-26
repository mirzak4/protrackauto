package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class TrafficFine {
    private Long id;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String description;
    private String type;
    private String paymentStatus;
    private String location;
    private Long vehicleId;
    private Long driverId;

    public TrafficFine() {
    }

    public TrafficFine(Long id, LocalDate issueDate, LocalDate dueDate, String description, String type, String paymentStatus, String location, Long vehicleId, Long driverId) {
        this.id = id;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.description = description;
        this.type = type;
        this.paymentStatus = paymentStatus;
        this.location = location;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
