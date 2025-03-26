package ba.unsa.etf.nbp.VehicleTrackPlatform.model;

import java.time.LocalDate;

public class FuelPump {
    private Long id;
    private LocalDate refuelingDate;
    private Integer quantity;
    private Integer pricePerLiter;
    private Integer totalCost;
    private Integer mileageAtRefueling;
    private Long gasStationId;
    private Long vehicleId;
    private Long fuelId;

    public FuelPump() {
    }

    public FuelPump(Long id, LocalDate refuelingDate, Integer quantity, Integer pricePerLiter, Integer totalCost, Integer mileageAtRefueling, Long gasStationId, Long vehicleId, Long fuelId) {
        this.id = id;
        this.refuelingDate = refuelingDate;
        this.quantity = quantity;
        this.pricePerLiter = pricePerLiter;
        this.totalCost = totalCost;
        this.mileageAtRefueling = mileageAtRefueling;
        this.gasStationId = gasStationId;
        this.vehicleId = vehicleId;
        this.fuelId = fuelId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRefuelingDate() {
        return refuelingDate;
    }

    public void setRefuelingDate(LocalDate refuelingDate) {
        this.refuelingDate = refuelingDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(Integer pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getMileageAtRefueling() {
        return mileageAtRefueling;
    }

    public void setMileageAtRefueling(Integer mileageAtRefueling) {
        this.mileageAtRefueling = mileageAtRefueling;
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

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }
}
