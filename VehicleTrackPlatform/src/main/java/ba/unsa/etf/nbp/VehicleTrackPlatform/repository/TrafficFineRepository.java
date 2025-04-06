package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TrafficFine;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.PaymentStatus;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ViolationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class TrafficFineRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrafficFineRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class TrafficFineRowMapper implements RowMapper<TrafficFine> {
        @Override
        public TrafficFine mapRow(ResultSet rs, int rowNum) throws SQLException {
            TrafficFine fine = new TrafficFine();
            fine.setId(rs.getLong("ID"));
            fine.setIssueDate(rs.getDate("ISSUE_DATE").toLocalDate());
            fine.setPaymentDueDate(rs.getDate("PAYMENT_DUE_DATE").toLocalDate());
            fine.setViolationDescription(rs.getString("VIOLATION_DESCRIPTION"));
            fine.setViolationType(ViolationType.valueOf(rs.getString("VIOLATION_TYPE")));
            fine.setLocation(rs.getString("LOCATION"));
            fine.setPaymentStatus(PaymentStatus.valueOf(rs.getString("PAYMENT_STATUS")));
            fine.setAmount(rs.getDouble("AMOUNT"));
            fine.setVehicleId(rs.getLong("VEHICLE_ID"));
            fine.setDriverId(rs.getLong("DRIVER_ID"));
            return fine;
        }
    }

    public List<TrafficFine> findAll() {
        return jdbcTemplate.query("SELECT * FROM TRAFFIC_FINE", new TrafficFineRowMapper());
    }

    public Optional<TrafficFine> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM TRAFFIC_FINE WHERE ID = ?",
                new TrafficFineRowMapper(), id).stream().findFirst();
    }

    public TrafficFine save(TrafficFine fine) {
        if (fine.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO TRAFFIC_FINE (ISSUE_DATE, PAYMENT_DUE_DATE, VIOLATION_DESCRIPTION, " +
                            "VIOLATION_TYPE, LOCATION, PAYMENT_STATUS, AMOUNT, VEHICLE_ID, DRIVER_ID) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID",
                    Long.class,
                    fine.getIssueDate(),
                    fine.getPaymentDueDate(),
                    fine.getViolationDescription(),
                    fine.getViolationType().name(),
                    fine.getLocation(),
                    fine.getPaymentStatus().name(),
                    fine.getAmount(),
                    fine.getVehicleId(),
                    fine.getDriverId());
            fine.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE TRAFFIC_FINE SET ISSUE_DATE = ?, PAYMENT_DUE_DATE = ?, " +
                            "VIOLATION_DESCRIPTION = ?, VIOLATION_TYPE = ?, LOCATION = ?, " +
                            "PAYMENT_STATUS = ?, AMOUNT = ?, VEHICLE_ID = ?, DRIVER_ID = ? WHERE ID = ?",
                    fine.getIssueDate(),
                    fine.getPaymentDueDate(),
                    fine.getViolationDescription(),
                    fine.getViolationType().name(),
                    fine.getLocation(),
                    fine.getPaymentStatus().name(),
                    fine.getAmount(),
                    fine.getVehicleId(),
                    fine.getDriverId(),
                    fine.getId());
        }
        return fine;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM TRAFFIC_FINE WHERE ID = ?", id);
    }

    public List<TrafficFine> findByDriverId(Long driverId) {
        return jdbcTemplate.query(
                "SELECT * FROM TRAFFIC_FINE WHERE DRIVER_ID = ?",
                new TrafficFineRowMapper(), driverId);
    }

    public List<TrafficFine> findByVehicleId(Long vehicleId) {
        return jdbcTemplate.query(
                "SELECT * FROM TRAFFIC_FINE WHERE VEHICLE_ID = ?",
                new TrafficFineRowMapper(), vehicleId);
    }
}
