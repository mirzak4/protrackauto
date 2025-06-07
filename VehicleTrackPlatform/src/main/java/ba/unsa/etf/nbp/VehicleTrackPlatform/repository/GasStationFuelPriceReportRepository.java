package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.GasStationFuelPriceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class GasStationFuelPriceReportRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GasStationFuelPriceReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class GasStationFuelPriceReportRowMapper implements RowMapper<GasStationFuelPriceReport> {
        @Override
        public GasStationFuelPriceReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new GasStationFuelPriceReport(
                rs.getLong("ID"),
                rs.getInt("COMPANY_ID"),
                rs.getString("NAME"),
                rs.getInt("DOCUMENT_ID")
            );
        }
    }

    public Optional<GasStationFuelPriceReport> findById(Long id) {
        return jdbcTemplate.query(
            "SELECT * FROM GAS_STATION_FUEL_PRICE_REPORT WHERE ID = ?",
            new GasStationFuelPriceReportRowMapper(),
            id
        ).stream().findFirst();
    }

    public List<GasStationFuelPriceReport> findByCompanyId(Long companyId) {
        return jdbcTemplate.query(
            "SELECT * FROM GAS_STATION_FUEL_PRICE_REPORT WHERE COMPANY_ID = ? ORDER BY ID DESC",
            new GasStationFuelPriceReportRowMapper(),
            companyId
        );
    }

    public Long create(GasStationFuelPriceReport report) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO GAS_STATION_FUEL_PRICE_REPORT (COMPANY_ID, NAME, DOCUMENT_ID) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setInt(1, report.getCompanyId());
            ps.setString(2, report.getName());
            ps.setInt(3, report.getDocumentId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM GAS_STATION_FUEL_PRICE_REPORT WHERE ID = ?", id);
    }
} 