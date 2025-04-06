package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DriverRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class DriverRowMapper implements RowMapper<Driver> {
        @Override
        public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
            Driver driver = new Driver();
            driver.setId(rs.getLong("ID"));
            driver.setFirstName(rs.getString("FIRST_NAME"));
            driver.setLastName(rs.getString("LAST_NAME"));
            driver.setBirthDate(rs.getDate("BIRTH_DATE").toLocalDate());
            driver.setLicenseNumber(rs.getString("LICENSE_NUMBER"));
            driver.setLicenseExpiry(rs.getDate("LICENSE_EXPIRY").toLocalDate());
            driver.setAddress(rs.getString("ADDRESS"));
            driver.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            driver.setEmail(rs.getString("EMAIL"));
            driver.setEmploymentDate(rs.getDate("EMPLOYMENT_DATE").toLocalDate());
            driver.setDriverStatus(DriverStatus.valueOf(rs.getString("DRIVER_STATUS")));
            return driver;
        }
    }

    public List<Driver> findAll() {
        return jdbcTemplate.query("SELECT * FROM DRIVER", new DriverRowMapper());
    }

    public Optional<Driver> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM DRIVER WHERE ID = ?",
                new DriverRowMapper(), id).stream().findFirst();
    }

    public Driver save(Driver driver) {
        if (driver.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO DRIVER (FIRST_NAME, LAST_NAME, BIRTH_DATE, LICENSE_NUMBER, " +
                            "LICENSE_EXPIRY, ADDRESS, PHONE_NUMBER, EMAIL, EMPLOYMENT_DATE, DRIVER_STATUS) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID",
                    Long.class,
                    driver.getFirstName(), driver.getLastName(), driver.getBirthDate(),
                    driver.getLicenseNumber(), driver.getLicenseExpiry(), driver.getAddress(),
                    driver.getPhoneNumber(), driver.getEmail(), driver.getEmploymentDate(),
                    driver.getDriverStatus().name());
            driver.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE DRIVER SET FIRST_NAME = ?, LAST_NAME = ?, BIRTH_DATE = ?, " +
                            "LICENSE_NUMBER = ?, LICENSE_EXPIRY = ?, ADDRESS = ?, PHONE_NUMBER = ?, " +
                            "EMAIL = ?, EMPLOYMENT_DATE = ?, DRIVER_STATUS = ? WHERE ID = ?",
                    driver.getFirstName(), driver.getLastName(), driver.getBirthDate(),
                    driver.getLicenseNumber(), driver.getLicenseExpiry(), driver.getAddress(),
                    driver.getPhoneNumber(), driver.getEmail(), driver.getEmploymentDate(),
                    driver.getDriverStatus().name(), driver.getId());
        }
        return driver;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM DRIVER WHERE ID = ?", id);
    }
}
