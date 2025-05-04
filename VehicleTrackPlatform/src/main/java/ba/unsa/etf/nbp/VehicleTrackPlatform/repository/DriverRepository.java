package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
public class DriverRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String driverQuerySql = """
                            SELECT
                d.ID,
                d.USER_ID,
                d.LICENSE_NUMBER,
                d.LICENSE_EXPIRY,
                d.EMPLOYMENT_DATE,
                d.CREATED_AT,
                d.CREATED_BY,
                d.MODIFIED_AT,
                d.MODIFIED_BY,
                u.FIRST_NAME,
                u.LAST_NAME,
                u.EMAIL,
                u.USERNAME,
                u.PHONE_NUMBER,
                u.BIRTH_DATE
                   FROM DRIVER d INNER JOIN NBP.NBP_USER u ON d.USER_ID=u.ID
            """;

    @Autowired
    public DriverRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class DriverRowMapper implements RowMapper<Driver> {
        @Override
        @NonNull
        public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
            var driverUser = new User(
                    rs.getLong("USER_ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("EMAIL"),
                    null,
                    null,
                    rs.getString("PHONE_NUMBER"),
                    null,
                    null
            );
            var driver = new Driver(
                    rs.getLong("ID"),
                    driverUser,
                    rs.getString("LICENSE_NUMBER"),
                    rs.getTimestamp("LICENSE_EXPIRY").toInstant()
            );

            var employmentDate = rs.getTimestamp("EMPLOYMENT_DATE");
            if (employmentDate != null) {
                driver.setEmploymentDate(employmentDate.toInstant());
            }

            driver.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            driver.setCreatedBy(rs.getString("CREATED_BY"));
            driver.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            driver.setModifiedBy(rs.getString("MODIFIED_BY"));

            return driver;
        }
    }

    public List<Driver> findAll() {
        // language=SQL
        // noinspection SqlResolve
        return jdbcTemplate.query(driverQuerySql, new DriverRowMapper());
    }

    public Optional<Driver> findById(Long id) {
        return jdbcTemplate.query(driverQuerySql + "WHERE d.ID = ?",
                new DriverRowMapper(), id).stream().findFirst();
    }

    @Transactional
    public Long create(Driver driver) {
        var userId = insertUser(driver.getUser());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY, EMPLOYMENT_DATE)" +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setLong(1, userId);
            ps.setString(2, driver.getLicenseNumber());
            ps.setTimestamp(3, Timestamp.from(driver.getLicenseExpiry()));
            ps.setTimestamp(4, Timestamp.from(driver.getEmploymentDate()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Transactional
    public Driver update(Driver driver) {
        updateUser(driver.getUser());

        jdbcTemplate.update(
                "UPDATE DRIVER SET LICENSE_NUMBER = ?, LICENSE_EXPIRY = ?, " +
                        "EMPLOYMENT_DATE = ?" +
                        "WHERE ID = ?",
                driver.getLicenseNumber(),
                Timestamp.from(driver.getLicenseExpiry()),
                Timestamp.from(driver.getEmploymentDate()),
                driver.getId()
        );

        return driver;
    }

    private void updateUser(User user) {
        jdbcTemplate.update(
                "UPDATE NBP.NBP_USER SET FIRST_NAME = ?, LAST_NAME = ?, " +
                        "EMAIL = ?, PASSWORD = ?, USERNAME = ?, PHONE_NUMBER = ?, BIRTH_DATE = ? " +
                        "WHERE ID = ?",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                user.getPhoneNumber(),
                Timestamp.from(user.getBirthDate()),
                user.getId()
        );
    }

    private Long insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String userSql = "INSERT INTO NBP.NBP_USER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(userSql, new String[] {"ID"});
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPhoneNumber());
            ps.setTimestamp(7, Timestamp.from(user.getBirthDate()));
            ps.setLong(8, user.getRoleId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteByUserId(Long userId) {
        jdbcTemplate.update("DELETE FROM NBP.NBP_USER WHERE ID = ?", userId);
    }
}
