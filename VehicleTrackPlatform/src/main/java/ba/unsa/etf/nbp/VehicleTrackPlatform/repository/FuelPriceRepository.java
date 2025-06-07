package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.FuelPrice;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class FuelPriceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FuelPriceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class FuelPriceRowMapper implements RowMapper<FuelPrice> {
        @Override
        @NonNull
        public FuelPrice mapRow(ResultSet rs, int rowNum) throws SQLException {
            FuelPrice fuelPrice = new FuelPrice(
                    rs.getLong("ID"),
                    rs.getDouble("PRICE"),
                    rs.getDate("ISSUE_DATE").toLocalDate(),
                    rs.getLong("FUEL_ID"),
                    rs.getLong("GAS_STATION_ID")
            );

            fuelPrice.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            fuelPrice.setCreatedBy(rs.getString("CREATED_BY"));
            fuelPrice.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            fuelPrice.setModifiedBy(rs.getString("MODIFIED_BY"));

            return fuelPrice;
        }
    }

    public List<FuelPrice> findAll() {
        return jdbcTemplate.query("SELECT * FROM FUEL_PRICE", new FuelPriceRowMapper());
    }

    public Optional<FuelPrice> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM FUEL_PRICE WHERE ID = ?", new FuelPriceRowMapper(), id)
                .stream().findFirst();
    }

    public List<FuelPrice> findByCompanyId(Long companyId) {
        return jdbcTemplate.query(
            "SELECT * FROM FUEL_PRICE WHERE GAS_STATION_ID = ? ORDER BY ISSUE_DATE DESC",
            new FuelPriceRowMapper(),
            companyId
        );
    }

    public Long create(FuelPrice fuelPrice) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO FUEL_PRICE (PRICE, ISSUE_DATE, FUEL_ID, GAS_STATION_ID) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setDouble(1, fuelPrice.getPrice());
            ps.setDate(2, Date.valueOf(fuelPrice.getIssueDate()));
            ps.setLong(3, fuelPrice.getFuelId());
            ps.setLong(4, fuelPrice.getGasStationId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public FuelPrice update(FuelPrice fuelPrice) {
        jdbcTemplate.update(
                "UPDATE FUEL_PRICE SET PRICE = ?, ISSUE_DATE = ?, FUEL_ID = ?, GAS_STATION_ID = ? WHERE ID = ?",
                fuelPrice.getPrice(),
                fuelPrice.getIssueDate(),
                fuelPrice.getFuelId(),
                fuelPrice.getGasStationId(),
                fuelPrice.getId()
        );
        return fuelPrice;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM FUEL_PRICE WHERE ID = ?", id);
    }
}
