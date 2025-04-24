package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ServiceRequestStatus;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ServiceType;

import java.time.LocalDate;

public class ServiceRequestDTO extends AuditableEntity {
    private Long id;
    private ServiceType serviceType;
    private Integer fiscalReceiptNumber;
    private Double cost;
    private ServiceRequestStatus status;
    private LocalDate requestDate;
    private String requestedBy;
    private Long vehicleId;
    private Long servicerId;

    public ServiceRequestDTO() {
    }

    public ServiceRequestDTO(
            Long id,
            ServiceType serviceType,
            Integer fiscalReceiptNumber,
            Double cost,
            ServiceRequestStatus status,
            LocalDate requestDate,
            String requestedBy,
            Long vehicleId,
            Long servicerId) {
        this.id = id;
        this.serviceType = serviceType;
        this.fiscalReceiptNumber = fiscalReceiptNumber;
        this.cost = cost;
        this.status = status;
        this.requestDate = requestDate;
        this.requestedBy = requestedBy;
        this.vehicleId = vehicleId;
        this.servicerId = servicerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getFiscalReceiptNumber() {
        return fiscalReceiptNumber;
    }

    public void setFiscalReceiptNumber(Integer fiscalReceiptNumber) {
        this.fiscalReceiptNumber = fiscalReceiptNumber;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ServiceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceRequestStatus status) {
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getServicerId() {
        return servicerId;
    }

    public void setServicerId(Long servicerId) {
        this.servicerId = servicerId;
    }
}
