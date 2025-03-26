package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class TravelRequest {
    private Long id;
    private String approvedBy;
    private LocalDate approvalDate;
    private String startPoint;
    private String endPoint;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Long vehicleId;
    private Long driverId;

    public TravelRequest() {
    }

    public TravelRequest(Long id, String approvedBy, LocalDate approvalDate, String startPoint, String endPoint, LocalDate startDate, LocalDate endDate, String status, Long vehicleId, Long driverId) {
        this.id = id;
        this.approvedBy = approvedBy;
        this.approvalDate = approvalDate;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
