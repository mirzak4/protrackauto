package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class FuelPrice extends AuditableEntity {
    private Long id;
    private LocalDate issueDate;
    private Long fuelId;

    public FuelPrice() {
    }

    public FuelPrice(Long id, LocalDate issueDate, Long fuelId) {
        this.id = id;
        this.issueDate = issueDate;
        this.fuelId = fuelId;
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
}
