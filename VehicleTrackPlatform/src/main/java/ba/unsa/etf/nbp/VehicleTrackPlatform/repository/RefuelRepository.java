package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Refuel;
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
public class RefuelRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RefuelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class RefuelRowMapper implements RowMapper<Refuel> {
        @Override
        @NonNull
        public Refuel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Refuel refuel = new Refuel(
                    rs.getLong("ID"),
                    rs.getString("FISCAL_RECEIPT_NUMBER"),
                    rs.getDate("REFUEL_DATE").toLocalDate(),
                    rs.getDouble("QUANTITY"),
                    rs.getDouble("TOTAL_CHARGE_AMOUNT"),
                    rs.getLong("GAS_STATION_ID"),
                    rs.getLong("VEHICLE_ID")
            );

            refuel.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            refuel.setCreatedBy(rs.getString("CREATED_BY"));
            refuel.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            refuel.setModifiedBy(rs.getString("MODIFIED_BY"));

            return refuel;
        }
    }

    public List<Refuel> findAll() {
        return jdbcTemplate.query("SELECT * FROM REFUEL", new RefuelRowMapper());
    }

    public Optional<Refuel> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM REFUEL WHERE ID = ?", new RefuelRowMapper(), id)
                .stream().findFirst();
    }

    public Long create(Refuel refuel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, refuel.getFiscalReceiptNumber());
            ps.setDate(2, Date.valueOf(refuel.getRefuelDate()));
            ps.setDouble(3, refuel.getQuantity());
            ps.setDouble(4, refuel.getTotalChargeAmount());
            ps.setLong(5, refuel.getGasStationId());
            ps.setLong(6, refuel.getVehicleId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Refuel update(Refuel refuel) {
        jdbcTemplate.update(
                "UPDATE REFUEL SET FISCAL_RECEIPT_NUMBER = ?, REFUEL_DATE = ?, QUANTITY = ?, TOTAL_CHARGE_AMOUNT = ?, GAS_STATION_ID = ?, VEHICLE_ID = ? WHERE ID = ?",
                refuel.getFiscalReceiptNumber(),
                Date.valueOf(refuel.getRefuelDate()),
                refuel.getQuantity(),
                refuel.getTotalChargeAmount(),
                refuel.getGasStationId(),
                refuel.getVehicleId(),
                refuel.getId()
        );
        return refuel;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM REFUEL WHERE ID = ?", id);
    }
}
