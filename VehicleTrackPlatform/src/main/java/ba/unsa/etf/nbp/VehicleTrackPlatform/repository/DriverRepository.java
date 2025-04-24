//package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;
//
//import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
//import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.DriverStatus;
//import io.micrometer.common.lang.NonNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class DriverRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public DriverRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    private static final class DriverRowMapper implements RowMapper<Driver> {
//        @Override
//        @NonNull
//        public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
//            var driver = new Driver(
//                    rs.getLong("ID"),
//                    rs.getString("FIRST_NAME"),
//                    rs.getString("LAST_NAME"),
//                    rs.getDate("BIRTH_DATE").toLocalDate(),
//                    rs.getString("LICENSE_NUMBER"),
//                    rs.getDate("LICENSE_EXPIRY").toLocalDate(),
//                    rs.getString("ADDRESS"),
//                    rs.getString("PHONE_NUMBER"),
//                    rs.getString("EMAIL"),
//                    rs.getDate("EMPLOYMENT_DATE").toLocalDate()
//            );
//
//            driver.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
//            driver.setCreatedBy(rs.getString("CREATED_BY"));
//            driver.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
//            driver.setModifiedBy(rs.getString("MODIFIED_BY"));
//
//            return driver;
//        }
//    }
//
//    public List<Driver> findAll() {
//        return jdbcTemplate.query("SELECT * FROM DRIVER", new DriverRowMapper());
//    }
//
//    public Optional<Driver> findById(Long id) {
//        return jdbcTemplate.query("SELECT * FROM DRIVER WHERE ID = ?",
//                new DriverRowMapper(), id).stream().findFirst();
//    }
//
//    public Long create(Driver driver) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(connection -> {
//            String sql = "INSERT INTO DRIVER (FIRST_NAME, LAST_NAME, BIRTH_DATE, LICENSE_NUMBER, LICENSE_EXPIRY, " +
//                    "ADDRESS, PHONE_NUMBER, EMAIL, EMPLOYMENT_DATE) " +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
//            ps.setString(1, driver.getFirstName());
//            ps.setString(2, driver.getLastName());
//            ps.setDate(3, java.sql.Date.valueOf(driver.getBirthDate()));
//            ps.setString(4, driver.getLicenseNumber());
//            ps.setDate(5, java.sql.Date.valueOf(driver.getLicenseExpiry()));
//            ps.setString(6, driver.getAddress());
//            ps.setString(7, driver.getPhoneNumber());
//            ps.setString(8, driver.getEmail());
//            ps.setDate(9, java.sql.Date.valueOf(driver.getEmploymentDate()));
//            return ps;
//        }, keyHolder);
//
//        return keyHolder.getKey().longValue();
//    }
//
//    public Driver update(Driver driver) {
//        jdbcTemplate.update(
//                "UPDATE DRIVER SET FIRST_NAME = ?, LAST_NAME = ?, BIRTH_DATE = ?, LICENSE_NUMBER = ?, LICENSE_EXPIRY = ?, " +
//                        "ADDRESS = ?, PHONE_NUMBER = ?, EMAIL = ?, EMPLOYMENT_DATE = ? WHERE ID = ?",
//                driver.getFirstName(),
//                driver.getLastName(),
//                driver.getBirthDate(),
//                driver.getLicenseNumber(),
//                driver.getLicenseExpiry(),
//                driver.getAddress(),
//                driver.getPhoneNumber(),
//                driver.getEmail(),
//                driver.getEmploymentDate(),
//                driver.getId());
//
//        return driver;
//    }
//
//    public void deleteById(Long id) {
//        jdbcTemplate.update("DELETE FROM DRIVER WHERE ID = ?", id);
//    }
//}
