package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.TravelRequestStatus;

import java.time.LocalDate;

public class TravelRequest extends AuditableEntity {
    private Long id;
    private String approvedBy;
    private LocalDate approvalDate;
    private String departureLocation;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private TravelRequestStatus travelRequestStatus;
    private Long vehicleId;
    private Long driverId;

    public TravelRequest() {
    }

    public TravelRequest(Long id, String approvedBy, LocalDate approvalDate, String departureLocation, String destination, LocalDate startDate, LocalDate endDate, TravelRequestStatus travelRequestStatus, Long vehicleId, Long driverId) {
        this.id = id;
        this.approvedBy = approvedBy;
        this.approvalDate = approvalDate;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.travelRequestStatus = travelRequestStatus;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public TravelRequestStatus getRequestStatus() {
        return travelRequestStatus;
    }

    public void setRequestStatus(TravelRequestStatus travelRequestStatus) {
        this.travelRequestStatus = travelRequestStatus;
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
