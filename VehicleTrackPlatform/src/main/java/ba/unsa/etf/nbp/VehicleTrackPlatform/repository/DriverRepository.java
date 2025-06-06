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
    private final UserRepository userRepository;
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
                    u.BIRTH_DATE,
                    ai.ACTIVE,
                    ai.LAST_VERIFICATION_CODE
               FROM DRIVER d
               INNER JOIN NBP.NBP_USER u ON d.USER_ID=u.ID
               INNER JOIN ACCOUNT_INFO ai ON ai.USER_ID=u.ID
            """;

    @Autowired
    public DriverRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    private static final class DriverRowMapper implements RowMapper<Driver> {
        @Override
        @NonNull
        public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
            var birthDateSql = rs.getTimestamp("BIRTH_DATE");
            var driverUser = new User(
                    rs.getLong("USER_ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("EMAIL"),
                    null,
                    rs.getString("USERNAME"),
                    rs.getString("PHONE_NUMBER"),
                    birthDateSql != null ? birthDateSql.toInstant() : null,
                    null
            );
            driverUser.setActive(rs.getString("ACTIVE").equals("1"));
            driverUser.setLastVerificationCode(rs.getString("LAST_VERIFICATION_CODE"));

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
        var userId = this.userRepository.insertUser(driver.getUser());

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
        this.userRepository.updateUser(driver.getUser());

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

    public void deleteByUserId(Long userId) {
        jdbcTemplate.update("DELETE FROM DRIVER WHERE ID = ?", userId);
    }
}
