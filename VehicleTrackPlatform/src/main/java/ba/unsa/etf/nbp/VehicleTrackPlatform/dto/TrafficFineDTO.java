package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.PaymentStatus;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ViolationType;

import java.time.LocalDate;

public class TrafficFineDTO extends AuditableEntity {
    private Long id;
    private LocalDate issueDate;
    private LocalDate paymentDueDate;
    private String violationDescription;
    private ViolationType violationType;
    private String location;
    private PaymentStatus paymentStatus;
    private Double amount;
    private Long vehicleId;
    private Long driverId;

    public TrafficFineDTO() {
    }

    public TrafficFineDTO(
            Long id,
            LocalDate issueDate,
            LocalDate paymentDueDate,
            String violationDescription,
            ViolationType violationType,
            String location,
            PaymentStatus paymentStatus,
            Double amount,
            Long vehicleId,
            Long driverId) {
        this.id = id;
        this.issueDate = issueDate;
        this.paymentDueDate = paymentDueDate;
        this.violationDescription = violationDescription;
        this.violationType = violationType;
        this.location = location;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
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

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }

    public ViolationType getViolationType() {
        return violationType;
    }

    public void setViolationType(ViolationType violationType) {
        this.violationType = violationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
