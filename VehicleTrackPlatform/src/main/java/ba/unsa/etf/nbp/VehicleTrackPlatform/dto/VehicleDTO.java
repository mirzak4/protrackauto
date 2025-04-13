package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.AuditableEntity;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.VehicleBodyType;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.VehicleCategory;

import java.time.LocalDate;

public class VehicleDTO extends AuditableEntity {
    private Long id;
    private String licensePlate;
    private LocalDate firstRegistrationDate;
    private String firstRegistrationPlace;
    private String firstLicensePlate;
    private LocalDate registrationIssueDate;
    private String registrationIssuePlace;
    private Long fuelId;
    private VehicleCategory vehicleCategory;
    private VehicleBodyType vehicleBodyType;
    private String color;
    private String vehicleBrandType;
    private String registrationNumber;
    private String commercialDescription;
    private String chassisNumber;
    private Integer productionYear;
    private Double maxWeight;
    private Double payload;
    private Double vehicleWeight;
    private Double powerWeightRatio;
    private Integer seatCount;
    private Double engineDisplacement;
    private Double maxPower;
    private String ecoCharacteristics;
    private String catalyst;
    private String engineNumber;

    public VehicleDTO() {
    }

    public VehicleDTO(
            Long id,
            String licensePlate,
            LocalDate firstRegistrationDate,
            String firstRegistrationPlace,
            String firstLicensePlate,
            LocalDate registrationIssueDate,
            String registrationIssuePlace,
            Long fuelId,
            VehicleCategory vehicleCategory,
            VehicleBodyType vehicleBodyType,
            String color,
            String vehicleBrandType,
            String registrationNumber,
            String commercialDescription,
            String chassisNumber,
            Integer productionYear,
            Double maxWeight,
            Double payload,
            Double vehicleWeight,
            Double powerWeightRatio,
            Integer seatCount,
            Double engineDisplacement,
            Double maxPower,
            String ecoCharacteristics,
            String catalyst,
            String engineNumber) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.firstRegistrationDate = firstRegistrationDate;
        this.firstRegistrationPlace = firstRegistrationPlace;
        this.firstLicensePlate = firstLicensePlate;
        this.registrationIssueDate = registrationIssueDate;
        this.registrationIssuePlace = registrationIssuePlace;
        this.fuelId = fuelId;
        this.vehicleCategory = vehicleCategory;
        this.vehicleBodyType = vehicleBodyType;
        this.color = color;
        this.vehicleBrandType = vehicleBrandType;
        this.registrationNumber = registrationNumber;
        this.commercialDescription = commercialDescription;
        this.chassisNumber = chassisNumber;
        this.productionYear = productionYear;
        this.maxWeight = maxWeight;
        this.payload = payload;
        this.vehicleWeight = vehicleWeight;
        this.powerWeightRatio = powerWeightRatio;
        this.seatCount = seatCount;
        this.engineDisplacement = engineDisplacement;
        this.maxPower = maxPower;
        this.ecoCharacteristics = ecoCharacteristics;
        this.catalyst = catalyst;
        this.engineNumber = engineNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDate getFirstRegistrationDate() {
        return firstRegistrationDate;
    }

    public void setFirstRegistrationDate(LocalDate firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }

    public String getFirstRegistrationPlace() {
        return firstRegistrationPlace;
    }

    public void setFirstRegistrationPlace(String firstRegistrationPlace) {
        this.firstRegistrationPlace = firstRegistrationPlace;
    }

    public String getFirstLicensePlate() {
        return firstLicensePlate;
    }

    public void setFirstLicensePlate(String firstLicensePlate) {
        this.firstLicensePlate = firstLicensePlate;
    }

    public LocalDate getRegistrationIssueDate() {
        return registrationIssueDate;
    }

    public void setRegistrationIssueDate(LocalDate registrationIssueDate) {
        this.registrationIssueDate = registrationIssueDate;
    }

    public String getRegistrationIssuePlace() {
        return registrationIssuePlace;
    }

    public void setRegistrationIssuePlace(String registrationIssuePlace) {
        this.registrationIssuePlace = registrationIssuePlace;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public VehicleBodyType getVehicleBodyType() {
        return vehicleBodyType;
    }

    public void setVehicleBodyType(VehicleBodyType vehicleBodyType) {
        this.vehicleBodyType = vehicleBodyType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleBrandType() {
        return vehicleBrandType;
    }

    public void setVehicleBrandType(String vehicleBrandType) {
        this.vehicleBrandType = vehicleBrandType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCommercialDescription() {
        return commercialDescription;
    }

    public void setCommercialDescription(String commercialDescription) {
        this.commercialDescription = commercialDescription;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double getPayload() {
        return payload;
    }

    public void setPayload(Double payload) {
        this.payload = payload;
    }

    public Double getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(Double vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public Double getPowerWeightRatio() {
        return powerWeightRatio;
    }

    public void setPowerWeightRatio(Double powerWeightRatio) {
        this.powerWeightRatio = powerWeightRatio;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Double getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Double engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public Double getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Double maxPower) {
        this.maxPower = maxPower;
    }

    public String getEcoCharacteristics() {
        return ecoCharacteristics;
    }

    public void setEcoCharacteristics(String ecoCharacteristics) {
        this.ecoCharacteristics = ecoCharacteristics;
    }

    public String getCatalyst() {
        return catalyst;
    }

    public void setCatalyst(String catalyst) {
        this.catalyst = catalyst;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }
}
