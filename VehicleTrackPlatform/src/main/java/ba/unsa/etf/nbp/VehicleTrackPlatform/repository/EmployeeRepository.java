package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Company;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Driver;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Employee;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;
import io.micrometer.common.lang.NonNull;
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
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    private final String employeeQuerySql = """
                SELECT
                    e.ID,
                    e.COMPANY_ID,
                    e.USER_ID,
                    e.CREATED_AT,
                    e.CREATED_BY,
                    e.MODIFIED_AT,
                    e.MODIFIED_BY,
                    u.FIRST_NAME,
                    u.LAST_NAME,
                    u.EMAIL,
                    u.USERNAME,
                    u.PHONE_NUMBER,
                    u.BIRTH_DATE,
                    ai.ACTIVE,
                    ai.LAST_VERIFICATION_CODE
               FROM EMPLOYEE e
               INNER JOIN NBP.NBP_USER u ON e.USER_ID=u.ID
               INNER JOIN ACCOUNT_INFO ai ON ai.USER_ID=u.ID
            """;

    public EmployeeRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        @NonNull
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            var employeeUser = new User(
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
            employeeUser.setActive(rs.getString("ACTIVE").equals("1"));
            employeeUser.setLastVerificationCode(rs.getString("LAST_VERIFICATION_CODE"));

            var company = new Company();
            company.setId(rs.getLong("COMPANY_ID"));

            var employee = new Employee(
                    rs.getLong("ID"),
                    company,
                    employeeUser
            );

            employee.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            employee.setCreatedBy(rs.getString("CREATED_BY"));
            employee.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            employee.setModifiedBy(rs.getString("MODIFIED_BY"));

            return employee;
        }
    }

    public List<Employee> findAll() {
        // language=SQL
        // noinspection SqlResolve
        return jdbcTemplate.query(employeeQuerySql, new EmployeeRowMapper());
    }

    public Optional<Employee> findById(Long id) {
        return jdbcTemplate.query(employeeQuerySql + "WHERE e.ID = ?",
                new EmployeeRowMapper(), id).stream().findFirst();
    }

    @Transactional
    public Long create(Employee employee) {
        var userId = this.userRepository.insertUser(employee.getUser());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setLong(1, employee.getCompanyId());
            ps.setLong(2, userId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Transactional
    public Employee update(Employee employee) {
        this.userRepository.updateUser(employee.getUser());

        jdbcTemplate.update(
                "UPDATE EMPLOYEE SET COMPANY_ID = ?, USER_ID = ? WHERE ID = ?",
                employee.getCompanyId(),
                employee.getUserId(),
                employee.getId()
        );

        return employee;
    }

    public void deleteByUserId(Long userId) {
        jdbcTemplate.update("DELETE FROM NBP.NBP_USER WHERE ID = ?", userId);
    }

}
