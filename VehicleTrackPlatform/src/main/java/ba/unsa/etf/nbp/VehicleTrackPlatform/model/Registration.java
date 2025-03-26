package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class Registration {
    private Long id;
    private String policyNumber;
    private String insuranceType;
    private LocalDate insuredFrom;
    private LocalDate insuredUntil;
    private Double insuranceCost;
    private Double additionalCosts;
    private String notes;
    private Long vehicleId;
    private Long insuranceCompanyId;

    public Registration() {
    }

    public Registration(Long id, String policyNumber, String insuranceType, LocalDate insuredFrom, LocalDate insuredUntil, Double insuranceCost, Double additionalCosts, String notes, Long vehicleId, Long insuranceCompanyId) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.insuranceType = insuranceType;
        this.insuredFrom = insuredFrom;
        this.insuredUntil = insuredUntil;
        this.insuranceCost = insuranceCost;
        this.additionalCosts = additionalCosts;
        this.notes = notes;
        this.vehicleId = vehicleId;
        this.insuranceCompanyId = insuranceCompanyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public LocalDate getInsuredFrom() {
        return insuredFrom;
    }

    public void setInsuredFrom(LocalDate insuredFrom) {
        this.insuredFrom = insuredFrom;
    }

    public LocalDate getInsuredUntil() {
        return insuredUntil;
    }

    public void setInsuredUntil(LocalDate insuredUntil) {
        this.insuredUntil = insuredUntil;
    }

    public Double getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(Double insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public Double getAdditionalCosts() {
        return additionalCosts;
    }

    public void setAdditionalCosts(Double additionalCosts) {
        this.additionalCosts = additionalCosts;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getInsuranceCompanyId() {
        return insuranceCompanyId;
    }

    public void setInsuranceCompanyId(Long insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }
}
