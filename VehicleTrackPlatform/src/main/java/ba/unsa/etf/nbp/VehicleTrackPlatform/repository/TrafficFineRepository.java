package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TrafficFine;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.PaymentStatus;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ViolationType;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TrafficFineRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TrafficFineRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class TrafficFineRowMapper implements RowMapper<TrafficFine> {
        @Override
        @NonNull
        public TrafficFine mapRow(ResultSet rs, int rowNum) throws SQLException {
            TrafficFine fine = new TrafficFine(
                    rs.getLong("ID"),
                    rs.getDate("ISSUE_DATE").toLocalDate(),
                    rs.getDate("PAYMENT_DUE_DATE").toLocalDate(),
                    rs.getString("VIOLATION_DESCRIPTION"),
                    ViolationType.valueOf(rs.getString("VIOLATION_TYPE")),
                    rs.getString("LOCATION"),
                    PaymentStatus.valueOf(rs.getString("PAYMENT_STATUS")),
                    rs.getDouble("AMOUNT"),
                    rs.getLong("VEHICLE_ID"),
                    rs.getLong("DRIVER_ID")
            );

            fine.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            fine.setCreatedBy(rs.getString("CREATED_BY"));
            fine.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            fine.setModifiedBy(rs.getString("MODIFIED_BY"));

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

    public Long create(TrafficFine fine) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO TRAFFIC_FINE (ISSUE_DATE, PAYMENT_DUE_DATE, VIOLATION_DESCRIPTION, VIOLATION_TYPE, LOCATION, PAYMENT_STATUS, AMOUNT, VEHICLE_ID, DRIVER_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setDate(1, Date.valueOf(fine.getIssueDate()));
            ps.setDate(2, Date.valueOf(fine.getPaymentDueDate()));
            ps.setString(3, fine.getViolationDescription());
            ps.setString(4, fine.getViolationType().name());
            ps.setString(5, fine.getLocation());
            ps.setString(6, fine.getPaymentStatus().name());
            ps.setDouble(7, fine.getAmount());
            ps.setLong(8, fine.getVehicleId());
            ps.setLong(9, fine.getDriverId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public TrafficFine update(TrafficFine fine) {
        jdbcTemplate.update(
                "UPDATE TRAFFIC_FINE SET ISSUE_DATE = ?, PAYMENT_DUE_DATE = ?, VIOLATION_DESCRIPTION = ?, VIOLATION_TYPE = ?, LOCATION = ?, PAYMENT_STATUS = ?, AMOUNT = ?, VEHICLE_ID = ?, DRIVER_ID = ? " +
                        "WHERE ID = ?",
                Date.valueOf(fine.getIssueDate()),
                Date.valueOf(fine.getPaymentDueDate()),
                fine.getViolationDescription(),
                fine.getViolationType().name(),
                fine.getLocation(),
                fine.getPaymentStatus().name(),
                fine.getAmount(),
                fine.getVehicleId(),
                fine.getDriverId(),
                fine.getId()
        );

        return fine;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM TRAFFIC_FINE WHERE ID = ?", id);
    }
}
