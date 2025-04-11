package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class Refuel extends AuditableEntity {
    private Long id;
    private String fiscalReceiptNumber;
    private LocalDate refuelDate;
    private Double quantity;
    private Double pricePerLiter;
    private Double mileage;
    private Long fuelTypeId;
    private Long stationId;
    private Long vehicleId;

    public Refuel() {
    }

    public Refuel(Long id, String fiscalReceiptNumber, LocalDate refuelDate, Double quantity, Double pricePerLiter, Double mileage, Long fuelTypeId, Long stationId, Long vehicleId) {
        this.id = id;
        this.fiscalReceiptNumber = fiscalReceiptNumber;
        this.refuelDate = refuelDate;
        this.quantity = quantity;
        this.pricePerLiter = pricePerLiter;
        this.mileage = mileage;
        this.fuelTypeId = fuelTypeId;
        this.stationId = stationId;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiscalReceiptNumber() {
        return fiscalReceiptNumber;
    }

    public void setFiscalReceiptNumber(String fiscalReceiptNumber) {
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

    public Double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Long getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(Long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
