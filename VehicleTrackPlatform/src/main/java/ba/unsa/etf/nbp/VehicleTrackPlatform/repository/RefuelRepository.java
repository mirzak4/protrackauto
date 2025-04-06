package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Refuel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class RefuelRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RefuelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class RefuelRowMapper implements RowMapper<Refuel> {
        @Override
        public Refuel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Refuel refuel = new Refuel();
            refuel.setId(rs.getLong("ID"));
            refuel.setFiscalReceiptNumber(rs.getString("FISCAL_RECEIPT_NUMBER"));
            refuel.setRefuelDate(rs.getDate("REFUEL_DATE").toLocalDate());
            refuel.setQuantity(rs.getDouble("QUANTITY"));
            refuel.setPricePerLiter(rs.getDouble("PRICE_PER_LITER"));
            refuel.setMileage(rs.getDouble("MILEAGE"));
            refuel.setFuelTypeId(rs.getLong("FUEL_TYPE_ID"));
            refuel.setStationId(rs.getLong("STATION_ID"));
            refuel.setVehicleId(rs.getLong("VEHICLE_ID"));
            return refuel;
        }
    }

    public List<Refuel> findAll() {
        return jdbcTemplate.query("SELECT * FROM REFUEL", new RefuelRowMapper());
    }

    public Optional<Refuel> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM REFUEL WHERE ID = ?",
                new RefuelRowMapper(), id).stream().findFirst();
    }

    public Refuel save(Refuel refuel) {
        if (refuel.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, " +
                            "PRICE_PER_LITER, MILEAGE, FUEL_TYPE_ID, STATION_ID, VEHICLE_ID) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID",
                    Long.class,
                    refuel.getFiscalReceiptNumber(),
                    refuel.getRefuelDate(),
                    refuel.getQuantity(),
                    refuel.getPricePerLiter(),
                    refuel.getMileage(),
                    refuel.getFuelTypeId(),
                    refuel.getStationId(),
                    refuel.getVehicleId());
            refuel.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE REFUEL SET FISCAL_RECEIPT_NUMBER = ?, REFUEL_DATE = ?, " +
                            "QUANTITY = ?, PRICE_PER_LITER = ?, MILEAGE = ?, FUEL_TYPE_ID = ?, " +
                            "STATION_ID = ?, VEHICLE_ID = ? WHERE ID = ?",
                    refuel.getFiscalReceiptNumber(),
                    refuel.getRefuelDate(),
                    refuel.getQuantity(),
                    refuel.getPricePerLiter(),
                    refuel.getMileage(),
                    refuel.getFuelTypeId(),
                    refuel.getStationId(),
                    refuel.getVehicleId(),
                    refuel.getId());
        }
        return refuel;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM REFUEL WHERE ID = ?", id);
    }

    public List<Refuel> findByVehicleId(Long vehicleId) {
        return jdbcTemplate.query(
                "SELECT * FROM REFUEL WHERE VEHICLE_ID = ? ORDER BY REFUEL_DATE DESC",
                new RefuelRowMapper(), vehicleId);
    }

    public List<Refuel> findByStationId(Long stationId) {
        return jdbcTemplate.query(
                "SELECT * FROM REFUEL WHERE STATION_ID = ? ORDER BY REFUEL_DATE DESC",
                new RefuelRowMapper(), stationId);
    }
}
