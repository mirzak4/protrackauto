package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.common.authentication.NbpPasswordEncoder;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Role;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.User;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Repository
public class UserRepository {
    private final String tmpPassword = "NBP04.tmp_pw";
    private final JdbcTemplate jdbcTemplate;
    private final NbpPasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate, NbpPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    private static final class UserRowMapper implements RowMapper<UserDetails> {
        @Override
        @NonNull
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    new Role(rs.getLong("ROLE_ID"), rs.getString("NAME").substring(6)),
                    rs.getString("ACTIVE").equals("1")
            );
        }
    }

    public Optional<UserDetails> findByEmail(String email) {
        return jdbcTemplate.query("SELECT u.ID, u.FIRST_NAME, u.LAST_NAME, u.EMAIL, u.PASSWORD, u.ROLE_ID, r.NAME, ai.ACTIVE FROM NBP.NBP_USER u JOIN NBP.NBP_ROLE r ON u.ROLE_ID = r.ID JOIN ACCOUNT_INFO ai ON ai.USER_ID = u.ID WHERE EMAIL=?", new UserRepository.UserRowMapper(), email).stream().findFirst();
    }

    public void updateUser(User user) {
        jdbcTemplate.update(
                "UPDATE NBP.NBP_USER SET FIRST_NAME = ?, LAST_NAME = ?, " +
                        "EMAIL = ?, PASSWORD = ?, USERNAME = ?, PHONE_NUMBER = ?, BIRTH_DATE = ? " +
                        "WHERE ID = ?",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                this.passwordEncoder.encode(user.getPassword()),
                user.getUsername(),
                user.getPhoneNumber(),
                Timestamp.from(user.getBirthDate()),
                user.getId()
        );
    }

    public Long insertUser(User user) {
        KeyHolder createdUserKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String userSql = "INSERT INTO NBP.NBP_USER (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(userSql, new String[] {"ID"});
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, this.passwordEncoder.encode(tmpPassword));
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPhoneNumber());
            ps.setTimestamp(7, Timestamp.from(user.getBirthDate()));
            ps.setLong(8, user.getRoleId());
            return ps;
        }, createdUserKeyHolder);

        var userId = createdUserKeyHolder.getKey().longValue();

        jdbcTemplate.update(connection -> {
            String accountInfoSql = "INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID)" +
                    "VALUES (?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(accountInfoSql, new String[] {"ID"});
            ps.setString(1, "0");
            ps.setString(2, user.getLastVerificationCode());
            ps.setLong(3, userId);
            return ps;
        });

        return createdUserKeyHolder.getKey().longValue();
    }

    public void updateVerificationCode(Long userId, String verificationCode) {
        jdbcTemplate.update(
                "UPDATE ACCOUNT_INFO SET LAST_VERIFICATION_CODE = ?" +
                        "WHERE USER_ID = ?",
                verificationCode,
                userId
        );
    }

    public boolean isVerificationCodeValid(Long userId, String verificationCode) {
        String sql = """
            SELECT
            CASE 
                WHEN EXISTS (SELECT 1 FROM ACCOUNT_INFO ac WHERE USER_ID = ? AND LAST_VERIFICATION_CODE = ?) THEN 1 
                ELSE 0 
            END 
            FROM DUAL
        """;

        boolean exists = jdbcTemplate.queryForObject(sql, new Object[]{userId, verificationCode}, Boolean.class);
        return exists;
    }

    @Transactional
    public void changePasswordAndActivateUser(Long userId, String password) {
        jdbcTemplate.update(
                "UPDATE ACCOUNT_INFO SET ACTIVE = '1'" +
                        "WHERE USER_ID = ?",
                userId
        );

        jdbcTemplate.update(
                "UPDATE NBP.NBP_USER SET PASSWORD = ?" +
                        "WHERE ID = ?",
                this.passwordEncoder.encode(password),
                userId
        );
    }
}
