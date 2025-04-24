package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.TravelRequestStatus;

import java.time.LocalDate;

public class TravelRequestDTO extends AuditableEntity {
    private Long id;
    private String approvedBy;
    private LocalDate approvalDate;
    private String departureLocation;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private TravelRequestStatus requestStatus;
    private Long vehicleId;
    private Long driverId;

    public TravelRequestDTO() {
    }

    public TravelRequestDTO(
            Long id,
            String approvedBy,
            LocalDate approvalDate,
            String departureLocation,
            String destination,
            LocalDate startDate,
            LocalDate endDate,
            TravelRequestStatus requestStatus,
            Long vehicleId,
            Long driverId) {
        this.id = id;
        this.approvedBy = approvedBy;
        this.approvalDate = approvalDate;
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requestStatus = requestStatus;
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
        return requestStatus;
    }

    public void setRequestStatus(TravelRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
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
