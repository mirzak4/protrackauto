package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Vehicle;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.VehicleBodyType;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.VehicleCategory;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
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
        @NonNull
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            var vehicle = new Vehicle(
                    rs.getLong("ID"),
                    rs.getString("LICENSE_PLATE"),
                    rs.getDate("FIRST_REGISTRATION_DATE").toLocalDate(),
                    rs.getString("FIRST_REGISTRATION_PLACE"),
                    rs.getString("FIRST_LICENSE_PLATE"),
                    rs.getDate("REGISTRATION_ISSUE_DATE").toLocalDate(),
                    rs.getString("REGISTRATION_ISSUE_PLACE"),
                    rs.getLong("FUEL_ID"),
                    VehicleCategory.parse(rs.getInt("VEHICLE_CATEGORY")),
                    VehicleBodyType.fromCode(rs.getInt("VEHICLE_BODY_TYPE")),
                    rs.getString("COLOR"),
                    rs.getString("VEHICLE_BRAND_TYPE"),
                    rs.getString("REGISTRATION_NUMBER"),
                    rs.getString("COMMERCIAL_DESCRIPTION"),
                    rs.getString("CHASSIS_NUMBER"),
                    rs.getInt("PRODUCTION_YEAR"),
                    rs.getDouble("MAX_WEIGHT"),
                    rs.getDouble("PAYLOAD"),
                    rs.getDouble("VEHICLE_WEIGHT"),
                    rs.getDouble("POWER_WEIGHT_RATIO"),
                    rs.getInt("SEAT_COUNT"),
                    rs.getDouble("ENGINE_DISPLACEMENT"),
                    rs.getDouble("MAX_POWER"),
                    rs.getString("ECO_CHARACTERISTICS"),
                    rs.getString("CATALYST"),
                    rs.getString("ENGINE_NUMBER")
            );

            vehicle.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            vehicle.setCreatedBy(rs.getString("CREATED_BY"));
            vehicle.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            vehicle.setModifiedBy(rs.getString("MODIFIED_BY"));

            return vehicle;
        }
    }

    public List<Vehicle> findAll() {
        return jdbcTemplate.query("SELECT * FROM VEHICLE", new VehicleRowMapper());
    }

    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM VEHICLE", Long.class);
    }

    public Optional<Vehicle> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM VEHICLE WHERE ID = ?",
                new VehicleRowMapper(), id).stream().findFirst();
    }

    public Long create(Vehicle v) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = """
            INSERT INTO VEHICLE (
                LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE, FIRST_LICENSE_PLATE, 
                REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE, FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR,
                VEHICLE_BRAND_TYPE, REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER, PRODUCTION_YEAR, 
                MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT, POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, 
                MAX_POWER, ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, v.getLicensePlate());
            ps.setDate(2, Date.valueOf(v.getFirstRegistrationDate()));
            ps.setString(3, v.getFirstRegistrationPlace());
            ps.setString(4, v.getFirstLicensePlate());
            ps.setDate(5, Date.valueOf(v.getRegistrationIssueDate()));
            ps.setString(6, v.getRegistrationIssuePlace());
            ps.setLong(7, v.getFuelId());
            ps.setInt(8, v.getVehicleCategory().getCode());
            ps.setInt(9, v.getVehicleBodyType().getCode());
            ps.setString(10, v.getColor());
            ps.setString(11, v.getVehicleBrandType());
            ps.setString(12, v.getRegistrationNumber());
            ps.setString(13, v.getCommercialDescription());
            ps.setString(14, v.getChassisNumber());
            ps.setInt(15, v.getProductionYear());
            ps.setDouble(16, v.getMaxWeight());
            ps.setDouble(17, v.getPayload());
            ps.setDouble(18, v.getVehicleWeight());
            ps.setDouble(19, v.getPowerWeightRatio());
            ps.setInt(20, v.getSeatCount());
            ps.setDouble(21, v.getEngineDisplacement());
            ps.setDouble(22, v.getMaxPower());
            ps.setString(23, v.getEcoCharacteristics());
            ps.setString(24, v.getCatalyst());
            ps.setString(25, v.getEngineNumber());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }


    public Vehicle update(Vehicle v) {
        jdbcTemplate.update("""
            UPDATE VEHICLE SET LICENSE_PLATE=?, FIRST_REGISTRATION_DATE=?, FIRST_REGISTRATION_PLACE=?, FIRST_LICENSE_PLATE=?,
            REGISTRATION_ISSUE_DATE=?, REGISTRATION_ISSUE_PLACE=?, FUEL_ID=?, VEHICLE_CATEGORY=?, VEHICLE_BODY_TYPE=?, COLOR=?,
            VEHICLE_BRAND_TYPE=?, REGISTRATION_NUMBER=?, COMMERCIAL_DESCRIPTION=?, CHASSIS_NUMBER=?, PRODUCTION_YEAR=?, MAX_WEIGHT=?, PAYLOAD=?,
            VEHICLE_WEIGHT=?, POWER_WEIGHT_RATIO=?, SEAT_COUNT=?, ENGINE_DISPLACEMENT=?, MAX_POWER=?, ECO_CHARACTERISTICS=?,
            CATALYST=?, ENGINE_NUMBER=? WHERE ID=?""",
                v.getLicensePlate(), Date.valueOf(v.getFirstRegistrationDate()), v.getFirstRegistrationPlace(), v.getFirstLicensePlate(),
                Date.valueOf(v.getRegistrationIssueDate()), v.getRegistrationIssuePlace(), v.getFuelId(),
                v.getVehicleCategory().getCode(), v.getVehicleBodyType().getCode(), v.getColor(),
                v.getVehicleBrandType(), v.getRegistrationNumber(), v.getCommercialDescription(), v.getChassisNumber(), v.getProductionYear(),
                v.getMaxWeight(), v.getPayload(), v.getVehicleWeight(), v.getPowerWeightRatio(), v.getSeatCount(),
                v.getEngineDisplacement(), v.getMaxPower(), v.getEcoCharacteristics(), v.getCatalyst(), v.getEngineNumber(), v.getId());
        return v;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM VEHICLE WHERE ID = ?", id);
    }
}
