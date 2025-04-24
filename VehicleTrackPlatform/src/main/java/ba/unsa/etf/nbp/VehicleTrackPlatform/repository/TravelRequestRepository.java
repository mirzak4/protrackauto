package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TravelRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.TravelRequestStatus;
import io.micrometer.common.lang.NonNull;
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
public class TravelRequestRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TravelRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class TravelRequestRowMapper implements RowMapper<TravelRequest> {
        @Override
        @NonNull
        public TravelRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            TravelRequest request = new TravelRequest(
                    rs.getLong("ID"),
                    rs.getString("APPROVED_BY"),
                    rs.getDate("APPROVAL_DATE") != null ? rs.getDate("APPROVAL_DATE").toLocalDate() : null,
                    rs.getString("DEPARTURE_LOCATION"),
                    rs.getString("DESTINATION"),
                    rs.getDate("START_DATE").toLocalDate(),
                    rs.getDate("END_DATE").toLocalDate(),
                    TravelRequestStatus.valueOf(rs.getString("REQUEST_STATUS")),
                    rs.getLong("VEHICLE_ID"),
                    rs.getLong("DRIVER_ID")
            );

            request.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            request.setCreatedBy(rs.getString("CREATED_BY"));
            request.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            request.setModifiedBy(rs.getString("MODIFIED_BY"));

            return request;
        }
    }

    public List<TravelRequest> findAll() {
        return jdbcTemplate.query("SELECT * FROM TRAVEL_REQUEST", new TravelRequestRowMapper());
    }

    public Optional<TravelRequest> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM TRAVEL_REQUEST WHERE ID = ?",
                new TravelRequestRowMapper(), id).stream().findFirst();
    }

    public Long create(TravelRequest request) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO TRAVEL_REQUEST (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, request.getApprovedBy());
            if (request.getApprovalDate() != null) {
                ps.setDate(2, java.sql.Date.valueOf(request.getApprovalDate()));
            } else {
                ps.setNull(2, java.sql.Types.DATE);
            }
            ps.setString(3, request.getDepartureLocation());
            ps.setString(4, request.getDestination());
            ps.setDate(5, java.sql.Date.valueOf(request.getStartDate()));
            ps.setDate(6, java.sql.Date.valueOf(request.getEndDate()));
            ps.setString(7, request.getRequestStatus().name());
            ps.setLong(8, request.getVehicleId());
            ps.setLong(9, request.getDriverId());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public TravelRequest update(TravelRequest request) {
        jdbcTemplate.update(
                "UPDATE TRAVEL_REQUEST SET APPROVED_BY = ?, APPROVAL_DATE = ?, DEPARTURE_LOCATION = ?, DESTINATION = ?, START_DATE = ?, END_DATE = ?, REQUEST_STATUS = ?, VEHICLE_ID = ?, DRIVER_ID = ? WHERE ID = ?",
                request.getApprovedBy(),
                request.getApprovalDate() != null ? java.sql.Date.valueOf(request.getApprovalDate()) : null,
                request.getDepartureLocation(),
                request.getDestination(),
                java.sql.Date.valueOf(request.getStartDate()),
                java.sql.Date.valueOf(request.getEndDate()),
                request.getRequestStatus().name(),
                request.getVehicleId(),
                request.getDriverId(),
                request.getId()
        );

        return request;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM TRAVEL_REQUEST WHERE ID = ?", id);
    }
}
