package ba.unsa.etf.nbp.VehicleTrackPlatform.dto;

import java.time.LocalDate;

public class WeeklyFuelPriceDTO {
    private Long companyId;
    private String companyName;
    private Long fuelId;
    private String fuelName;
    private String year;
    private String week;
    private LocalDate weekStartDate;
    private LocalDate weekEndDate;
    private Double avgPrice;

    public WeeklyFuelPriceDTO() {
    }

    public WeeklyFuelPriceDTO(Long companyId, String companyName, Long fuelId, String fuelName, String year, String week, LocalDate weekStartDate, LocalDate weekEndDate, Double avgPrice) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.fuelId = fuelId;
        this.fuelName = fuelName;
        this.year = year;
        this.week = week;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.avgPrice = avgPrice;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public LocalDate getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(LocalDate weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }
}