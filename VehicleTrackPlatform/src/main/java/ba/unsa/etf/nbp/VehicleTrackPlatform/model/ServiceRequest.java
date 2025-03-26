package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class ServiceRequest {
    private Long id;
    private String orderNumber;
    private String requestedService;
    private Double estimatedCost;
    private LocalDate issueDate;
    private String status;
    private LocalDate requestDate;
    private String requestedBy;
    private LocalDate cancellationDate;
    private String cancelledBy;
    private Long registrationId;
    private Long servicerId;

    public ServiceRequest() {
    }

    public ServiceRequest(Long id, String orderNumber, String requestedService, Double estimatedCost, LocalDate issueDate, String status, LocalDate requestDate, String requestedBy, LocalDate cancellationDate, String cancelledBy, Long registrationId, Long servicerId) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.requestedService = requestedService;
        this.estimatedCost = estimatedCost;
        this.issueDate = issueDate;
        this.status = status;
        this.requestDate = requestDate;
        this.requestedBy = requestedBy;
        this.cancellationDate = cancellationDate;
        this.cancelledBy = cancelledBy;
        this.registrationId = registrationId;
        this.servicerId = servicerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRequestedService() {
        return requestedService;
    }

    public void setRequestedService(String requestedService) {
        this.requestedService = requestedService;
    }

    public Double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDate getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(LocalDate cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getCancelledBy() {
        return cancelledBy;
    }

    public void setCancelledBy(String cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public Long getServicerId() {
        return servicerId;
    }

    public void setServicerId(Long servicerId) {
        this.servicerId = servicerId;
    }
}
