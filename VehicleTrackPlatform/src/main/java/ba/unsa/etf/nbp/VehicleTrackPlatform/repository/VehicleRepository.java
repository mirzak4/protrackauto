package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepository {
    private final JdbcTemplate jdbcTemplate;

    public VehicleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class VehicleRowMapper implements RowMapper<Vehicle> {
        @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(rs.getLong("ID"));
            vehicle.setLicensePlate(rs.getString("LICENSE_PLATE"));
            vehicle.setFirstRegistrationDate(rs.getDate("FIRST_REGISTRATION_DATE") != null ?
                    rs.getDate("FIRST_REGISTRATION_DATE").toLocalDate() : null);
            vehicle.setFirstRegistrationPlace(rs.getString("FIRST_REGISTRATION_PLACE"));
            vehicle.setFirstLicensePlate(rs.getString("FIRST_LICENSE_PLATE"));
            vehicle.setRegistrationIssueDate(rs.getDate("REGISTRATION_ISSUE_DATE") != null ?
                    rs.getDate("REGISTRATION_ISSUE_DATE").toLocalDate() : null);
            vehicle.setRegistrationIssuePlace(rs.getString("REGISTRATION_ISSUE_PLACE"));
            vehicle.setFuelId(rs.getLong("FUEL_ID"));
            vehicle.setVehicleCategory(rs.getInt("VEHICLE_CATEGORY"));
            vehicle.setVehicleBodyType(rs.getInt("VEHICLE_BODY_TYPE"));
            vehicle.setColor(rs.getString("COLOR"));
            vehicle.setVehicleBrandType(rs.getString("VEHICLE_BRAND_TYPE"));
            vehicle.setRegistrationNumber(rs.getString("REGISTRATION_NUMBER"));
            vehicle.setCommercialDescription(rs.getString("COMMERCIAL_DESCRIPTION"));
            vehicle.setChassisNumber(rs.getString("CHASSIS_NUMBER"));
            vehicle.setProductionYear(rs.getInt("PRODUCTION_YEAR"));
            vehicle.setMaxWeight(rs.getDouble("MAX_WEIGHT"));
            vehicle.setPayload(rs.getDouble("PAYLOAD"));
            vehicle.setVehicleWeight(rs.getDouble("VEHICLE_WEIGHT"));
            vehicle.setPowerWeightRatio(rs.getString("POWER_WEIGHT_RATIO"));
            vehicle.setSeatCount(rs.getInt("SEAT_COUNT"));
            vehicle.setEngineDisplacement(rs.getDouble("ENGINE_DISPLACEMENT"));
            vehicle.setMaxPower(rs.getDouble("MAX_POWER"));
            vehicle.setEcoCharacteristics(rs.getString("ECO_CHARACTERISTICS"));
            vehicle.setCatalyst(rs.getString("CATALYST"));
            vehicle.setEngineNumber(rs.getString("ENGINE_NUMBER"));
            return vehicle;
        }
    }

    public List<Vehicle> findAll() {
        return jdbcTemplate.query("SELECT * FROM VEHICLE", new VehicleRowMapper());
    }

    public Optional<Vehicle> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM VEHICLE WHERE ID = ?",
                new VehicleRowMapper(), id).stream().findFirst();
    }

    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO VEHICLE (" +
                            "LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE, " +
                            "FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE, " +
                            "FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE, " +
                            "REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER, PRODUCTION_YEAR, " +
                            "MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT, POWER_WEIGHT_RATIO, SEAT_COUNT, " +
                            "ENGINE_DISPLACEMENT, MAX_POWER, ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER" +
                            ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID",
                    Long.class,
                    // All parameters in order
                    vehicle.getLicensePlate(),
                    vehicle.getFirstRegistrationDate(),
                    vehicle.getFirstRegistrationPlace(),
                    vehicle.getFirstLicensePlate(),
                    vehicle.getRegistrationIssueDate(),
                    vehicle.getRegistrationIssuePlace(),
                    vehicle.getFuelId(),
                    vehicle.getVehicleCategory(),
                    vehicle.getVehicleBodyType(),
                    vehicle.getColor(),
                    vehicle.getVehicleBrandType(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getCommercialDescription(),
                    vehicle.getChassisNumber(),
                    vehicle.getProductionYear(),
                    vehicle.getMaxWeight(),
                    vehicle.getPayload(),
                    vehicle.getVehicleWeight(),
                    vehicle.getPowerWeightRatio(),
                    vehicle.getSeatCount(),
                    vehicle.getEngineDisplacement(),
                    vehicle.getMaxPower(),
                    vehicle.getEcoCharacteristics(),
                    vehicle.getCatalyst(),
                    vehicle.getEngineNumber());
            vehicle.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE VEHICLE SET " +
                            "LICENSE_PLATE = ?, FIRST_REGISTRATION_DATE = ?, FIRST_REGISTRATION_PLACE = ?, " +
                            "FIRST_LICENSE_PLATE = ?, REGISTRATION_ISSUE_DATE = ?, REGISTRATION_ISSUE_PLACE = ?, " +
                            "FUEL_ID = ?, VEHICLE_CATEGORY = ?, VEHICLE_BODY_TYPE = ?, COLOR = ?, VEHICLE_BRAND_TYPE = ?, " +
                            "REGISTRATION_NUMBER = ?, COMMERCIAL_DESCRIPTION = ?, CHASSIS_NUMBER = ?, PRODUCTION_YEAR = ?, " +
                            "MAX_WEIGHT = ?, PAYLOAD = ?, VEHICLE_WEIGHT = ?, POWER_WEIGHT_RATIO = ?, SEAT_COUNT = ?, " +
                            "ENGINE_DISPLACEMENT = ?, MAX_POWER = ?, ECO_CHARACTERISTICS = ?, CATALYST = ?, ENGINE_NUMBER = ? " +
                            "WHERE ID = ?",
                    // All parameters in order
                    vehicle.getLicensePlate(),
                    vehicle.getFirstRegistrationDate(),
                    vehicle.getFirstRegistrationPlace(),
                    vehicle.getFirstLicensePlate(),
                    vehicle.getRegistrationIssueDate(),
                    vehicle.getRegistrationIssuePlace(),
                    vehicle.getFuelId(),
                    vehicle.getVehicleCategory(),
                    vehicle.getVehicleBodyType(),
                    vehicle.getColor(),
                    vehicle.getVehicleBrandType(),
                    vehicle.getRegistrationNumber(),
                    vehicle.getCommercialDescription(),
                    vehicle.getChassisNumber(),
                    vehicle.getProductionYear(),
                    vehicle.getMaxWeight(),
                    vehicle.getPayload(),
                    vehicle.getVehicleWeight(),
                    vehicle.getPowerWeightRatio(),
                    vehicle.getSeatCount(),
                    vehicle.getEngineDisplacement(),
                    vehicle.getMaxPower(),
                    vehicle.getEcoCharacteristics(),
                    vehicle.getCatalyst(),
                    vehicle.getEngineNumber(),
                    vehicle.getId());
        }
        return vehicle;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM VEHICLE WHERE ID = ?", id);
    }
}
