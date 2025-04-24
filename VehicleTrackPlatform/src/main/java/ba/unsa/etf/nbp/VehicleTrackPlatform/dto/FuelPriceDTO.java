package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;

import java.time.LocalDate;

public class FuelPriceDTO extends AuditableEntity {
    private Long id;
    private Double price;
    private LocalDate issueDate;
    private Long fuelId;
    private Long gasStationId;

    public FuelPriceDTO() {
    }

    public FuelPriceDTO(Long id, Double price, LocalDate issueDate, Long fuelId, Long gasStationId) {
        this.id = id;
        this.price = price;
        this.issueDate = issueDate;
        this.fuelId = fuelId;
        this.gasStationId = gasStationId;
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

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public Long getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(Long gasStationId) {
        this.gasStationId = gasStationId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
