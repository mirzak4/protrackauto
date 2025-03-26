package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class Vehicle {
    private Long id;
    private LocalDate firstRegistrationDate;
    private String firstRegistrationPlace;
    private String firstRegistrationNumber;
    private LocalDate issueDate;
    private String issuePlace;
    private String registrationNumber;
    private String vehicleType;
    private String brand;
    private String model;
    private String commercialDescription;
    private String chassisNumber;
    private String bodyShape;
    private Integer productionYear;
    private Integer maxTechnicalMass;
    private Integer allowedLoad;
    private Integer vehicleWeight;
    private String typeApprovalNumber;
    private String powerRatio;
    private String color;
    private Integer seatCount;
    private Integer standingPlaceCount;
    private Integer lyingPlaceCount;
    private Integer engineDisplacement;
    private Integer maxPower;
    private String ecoCharacteristics;
    private String catalyst;
    private String engineNumber;
    private String engineType;
    private Long fuelId;

    public Vehicle() {
    }

    public Vehicle(Long id, LocalDate firstRegistrationDate, String firstRegistrationPlace, String firstRegistrationNumber, LocalDate issueDate, String issuePlace, String registrationNumber, String vehicleType, String brand, String model, String commercialDescription, String chassisNumber, String bodyShape, Integer productionYear, Integer maxTechnicalMass, Integer allowedLoad, Integer vehicleWeight, String typeApprovalNumber, String powerRatio, String color, Integer seatCount, Integer standingPlaceCount, Integer lyingPlaceCount, Integer engineDisplacement, Integer maxPower, String ecoCharacteristics, String catalyst, String engineNumber, String engineType, Long fuelId) {
        this.id = id;
        this.firstRegistrationDate = firstRegistrationDate;
        this.firstRegistrationPlace = firstRegistrationPlace;
        this.firstRegistrationNumber = firstRegistrationNumber;
        this.issueDate = issueDate;
        this.issuePlace = issuePlace;
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.commercialDescription = commercialDescription;
        this.chassisNumber = chassisNumber;
        this.bodyShape = bodyShape;
        this.productionYear = productionYear;
        this.maxTechnicalMass = maxTechnicalMass;
        this.allowedLoad = allowedLoad;
        this.vehicleWeight = vehicleWeight;
        this.typeApprovalNumber = typeApprovalNumber;
        this.powerRatio = powerRatio;
        this.color = color;
        this.seatCount = seatCount;
        this.standingPlaceCount = standingPlaceCount;
        this.lyingPlaceCount = lyingPlaceCount;
        this.engineDisplacement = engineDisplacement;
        this.maxPower = maxPower;
        this.ecoCharacteristics = ecoCharacteristics;
        this.catalyst = catalyst;
        this.engineNumber = engineNumber;
        this.engineType = engineType;
        this.fuelId = fuelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstRegistrationNumber() {
        return firstRegistrationNumber;
    }

    public void setFirstRegistrationNumber(String firstRegistrationNumber) {
        this.firstRegistrationNumber = firstRegistrationNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getBodyShape() {
        return bodyShape;
    }

    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getMaxTechnicalMass() {
        return maxTechnicalMass;
    }

    public void setMaxTechnicalMass(Integer maxTechnicalMass) {
        this.maxTechnicalMass = maxTechnicalMass;
    }

    public Integer getAllowedLoad() {
        return allowedLoad;
    }

    public void setAllowedLoad(Integer allowedLoad) {
        this.allowedLoad = allowedLoad;
    }

    public Integer getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(Integer vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public String getTypeApprovalNumber() {
        return typeApprovalNumber;
    }

    public void setTypeApprovalNumber(String typeApprovalNumber) {
        this.typeApprovalNumber = typeApprovalNumber;
    }

    public String getPowerRatio() {
        return powerRatio;
    }

    public void setPowerRatio(String powerRatio) {
        this.powerRatio = powerRatio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getStandingPlaceCount() {
        return standingPlaceCount;
    }

    public void setStandingPlaceCount(Integer standingPlaceCount) {
        this.standingPlaceCount = standingPlaceCount;
    }

    public Integer getLyingPlaceCount() {
        return lyingPlaceCount;
    }

    public void setLyingPlaceCount(Integer lyingPlaceCount) {
        this.lyingPlaceCount = lyingPlaceCount;
    }

    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
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

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }
}
