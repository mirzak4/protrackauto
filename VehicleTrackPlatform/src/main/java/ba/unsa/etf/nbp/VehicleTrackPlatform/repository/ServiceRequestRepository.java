package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.ServiceRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ServiceRequestStatus;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.ServiceType;
import io.micrometer.common.lang.NonNull;
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
public class ServiceRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ServiceRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ServiceRequestRowMapper implements RowMapper<ServiceRequest> {
        @Override
        @NonNull
        public ServiceRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            var request = new ServiceRequest(
                    rs.getLong("ID"),
                    ServiceType.valueOf(rs.getString("SERVICE_TYPE")),
                    rs.getInt("FISCAL_RECEIPT_NUMBER"),
                    rs.getDouble("COST"),
                    ServiceRequestStatus.valueOf(rs.getString("STATUS")),
                    rs.getDate("REQUEST_DATE").toLocalDate(),
                    rs.getString("REQUESTED_BY"),
                    rs.getLong("VEHICLE_ID"),
                    rs.getLong("SERVICER_ID")
            );

            request.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            request.setCreatedBy(rs.getString("CREATED_BY"));
            request.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            request.setModifiedBy(rs.getString("MODIFIED_BY"));

            return request;
        }
    }

    public List<ServiceRequest> findAll() {
        return jdbcTemplate.query("SELECT * FROM SERVICE_REQUEST", new ServiceRequestRowMapper());
    }

    public Optional<ServiceRequest> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM SERVICE_REQUEST WHERE ID = ?",
                new ServiceRequestRowMapper(), id).stream().findFirst();
    }

    public Long create(ServiceRequest request) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO SERVICE_REQUEST (SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setString(1, request.getServiceType().name());
            ps.setInt(2, request.getFiscalReceiptNumber());
            ps.setDouble(3, request.getCost());
            ps.setString(4, request.getStatus().name());
            ps.setDate(5, Date.valueOf(request.getRequestDate()));
            ps.setString(6, request.getRequestedBy());
            ps.setLong(7, request.getVehicleId());
            ps.setLong(8, request.getServicerId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public ServiceRequest update(ServiceRequest request) {
        jdbcTemplate.update(
                "UPDATE SERVICE_REQUEST SET SERVICE_TYPE = ?, FISCAL_RECEIPT_NUMBER = ?, COST = ?, STATUS = ?, " +
                        "REQUEST_DATE = ?, REQUESTED_BY = ?, VEHICLE_ID = ?, SERVICER_ID = ? WHERE ID = ?",
                request.getServiceType().name(),
                request.getFiscalReceiptNumber(),
                request.getCost(),
                request.getStatus().name(),
                Date.valueOf(request.getRequestDate()),
                request.getRequestedBy(),
                request.getVehicleId(),
                request.getServicerId(),
                request.getId()
        );

        return request;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM SERVICE_REQUEST WHERE ID = ?", id);
    }
}
