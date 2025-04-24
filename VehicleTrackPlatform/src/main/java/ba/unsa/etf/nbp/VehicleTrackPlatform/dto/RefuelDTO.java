package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;

import java.time.LocalDate;

public class RefuelDTO extends AuditableEntity {
    private Long id;
    private Integer fiscalReceiptNumber;
    private LocalDate refuelDate;
    private Double quantity;
    private Double totalChargeAmount;
    private Long gasStationId;
    private Long vehicleId;

    public RefuelDTO() {
    }

    public RefuelDTO(
            Long id,
            Integer fiscalReceiptNumber,
            LocalDate refuelDate,
            Double quantity,
            Double totalChargeAmount,
            Long gasStationId,
            Long vehicleId) {
        this.id = id;
        this.fiscalReceiptNumber = fiscalReceiptNumber;
        this.refuelDate = refuelDate;
        this.quantity = quantity;
        this.totalChargeAmount = totalChargeAmount;
        this.gasStationId = gasStationId;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFiscalReceiptNumber() {
        return fiscalReceiptNumber;
    }

    public void setFiscalReceiptNumber(Integer fiscalReceiptNumber) {
        this.fiscalReceiptNumber = fiscalReceiptNumber;
    }

    public LocalDate getRefuelDate() {
        return refuelDate;
    }

    public void setRefuelDate(LocalDate refuelDate) {
        this.refuelDate = refuelDate;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotalChargeAmount() {
        return totalChargeAmount;
    }

    public void setTotalChargeAmount(Double totalChargeAmount) {
        this.totalChargeAmount = totalChargeAmount;
    }

    public Long getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(Long gasStationId) {
        this.gasStationId = gasStationId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
