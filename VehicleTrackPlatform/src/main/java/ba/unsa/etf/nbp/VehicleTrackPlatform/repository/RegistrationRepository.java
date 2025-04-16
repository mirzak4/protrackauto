package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Registration;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.VehicleInsuranceType;
import org.jspecify.annotations.NonNull;
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
public class RegistrationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class RegistrationRowMapper implements RowMapper<Registration> {
        @Override
        @NonNull
        public Registration mapRow(ResultSet rs, int rowNum) throws SQLException {
            Registration r = new Registration(
                    rs.getLong("ID"),
                    rs.getString("POLICY_NUMBER"),
                    VehicleInsuranceType.fromCode(rs.getInt("INSURANCE_TYPE")),
                    rs.getDate("INSURED_FROM").toLocalDate(),
                    rs.getDate("INSURED_UNTIL").toLocalDate(),
                    rs.getDouble("INSURANCE_COST"),
                    rs.getDouble("ADDITIONAL_COSTS"),
                    rs.getLong("VEHICLE_ID"),
                    rs.getLong("INSURANCE_COMPANY_ID")
            );

            r.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            r.setCreatedBy(rs.getString("CREATED_BY"));
            r.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            r.setModifiedBy(rs.getString("MODIFIED_BY"));
            return r;
        }
    }

    public List<Registration> findAll() {
        return jdbcTemplate.query("SELECT * FROM REGISTRATION", new RegistrationRowMapper());
    }

    public Optional<Registration> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM REGISTRATION WHERE ID = ?", new RegistrationRowMapper(), id)
                .stream().findFirst();
    }

    public Long create(Registration r) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO REGISTRATION (POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, " +
                    "INSURANCE_COST, ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setString(1, r.getPolicyNumber());
            ps.setInt(2, r.getInsuranceType().ordinal());
            ps.setDate(3, Date.valueOf(r.getInsuredFrom()));
            ps.setDate(4, Date.valueOf(r.getInsuredUntil()));
            ps.setDouble(5, r.getInsuranceCost());
            ps.setDouble(6, r.getAdditionalCosts());
            ps.setLong(7, r.getVehicleId());
            ps.setLong(8, r.getInsuranceCompanyId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public Registration update(Registration r) {
        jdbcTemplate.update("UPDATE REGISTRATION SET POLICY_NUMBER = ?, INSURANCE_TYPE = ?, INSURED_FROM = ?, " +
                        "INSURED_UNTIL = ?, INSURANCE_COST = ?, ADDITIONAL_COSTS = ?, VEHICLE_ID = ?, INSURANCE_COMPANY_ID = ? WHERE ID = ?",
                r.getPolicyNumber(), r.getInsuranceType().ordinal(), Date.valueOf(r.getInsuredFrom()),
                Date.valueOf(r.getInsuredUntil()), r.getInsuranceCost(), r.getAdditionalCosts(),
                r.getVehicleId(), r.getInsuranceCompanyId(), r.getId());
        return r;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM REGISTRATION WHERE ID = ?", id);
    }
}
