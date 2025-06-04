package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.WeeklyFuelPriceDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Company;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.CompanyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CompanyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class CompanyRowMapper implements RowMapper<Company> {
        @Override
        @NonNull
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            var company = new Company(
                    rs.getLong("ID"),
                    CompanyType.fromCode(rs.getInt("COMPANY_TYPE")),
                    rs.getString("NAME"),
                    rs.getString("ADDRESS"),
                    rs.getString("PHONE_NUMBER"),
                    rs.getString("EMAIL"),
                    rs.getString("CONTACT_PERSON")
            );

            company.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            company.setCreatedBy(rs.getString("CREATED_BY"));
            company.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            company.setModifiedBy(rs.getString("MODIFIED_BY"));

            return company;
        }
    }

    private static final class WeeklyFuelPriceRowMapper implements RowMapper<WeeklyFuelPriceDTO> {
        @Override
        @NonNull
        public WeeklyFuelPriceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new WeeklyFuelPriceDTO(
                rs.getLong("COMPANY_ID"),
                rs.getString("COMPANY_NAME"),
                rs.getLong("FUEL_ID"),
                rs.getString("FUEL_NAME"),
                rs.getString("YEAR"),
                rs.getString("WEEK"),
                rs.getDate("WEEK_START_DATE").toLocalDate(),
                rs.getDate("WEEK_END_DATE").toLocalDate(),
                rs.getDouble("AVG_PRICE")
            );
        }
    }

    public List<WeeklyFuelPriceDTO> getGasStationYearStatistic(Long companyId, String year) {
        String sql = """
            SELECT 
                COMPANY_ID,
                COMPANY_NAME,
                FUEL_ID,
                FUEL_NAME,
                YEAR,
                WEEK,
                WEEK_START_DATE,
                WEEK_END_DATE,
                AVG_PRICE
            FROM VW_WEEKLY_AVG_FUEL_PRICE
            WHERE COMPANY_ID = ? 
            AND YEAR = ?
            ORDER BY FUEL_ID, WEEK
            """;
        
        return jdbcTemplate.query(sql, new WeeklyFuelPriceRowMapper(), companyId, year);
    }

    public List<Company> findAll(CompanyType companyType) {
        return jdbcTemplate.query("SELECT * FROM COMPANY WHERE COMPANY_TYPE=?", new CompanyRowMapper(), companyType.getCode());
    }

    public Optional<Company> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM COMPANY WHERE ID = ?",
                new CompanyRowMapper(), id).stream().findFirst();
    }

    public Long create(Company company) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO COMPANY (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setInt(1, company.getCompanyType().ordinal());
            ps.setString(2, company.getName());
            ps.setString(3, company.getAddress());
            ps.setString(4, company.getPhoneNumber());
            ps.setString(5, company.getEmail());
            ps.setString(6, company.getContactPerson());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Company update(Company company) {
        jdbcTemplate.update(
                "UPDATE COMPANY SET NAME = ?, ADDRESS = ?, " +
                        "PHONE_NUMBER = ?, EMAIL = ?, CONTACT_PERSON = ? " +
                        "WHERE ID = ?",
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber(),
                company.getEmail(),
                company.getContactPerson(),
                company.getId());

        return company;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM COMPANY WHERE ID = ?", id);
    }
}
