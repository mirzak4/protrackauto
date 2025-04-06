package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.TravelRequest;
import ba.unsa.etf.nbp.VehicleTrackPlatform.model.enums.TravelRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        public TravelRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
            TravelRequest request = new TravelRequest();
            request.setId(rs.getLong("ID"));
            request.setApprovedBy(rs.getString("APPROVED_BY"));
            request.setApprovalDate(rs.getDate("APPROVAL_DATE") != null ?
                    rs.getDate("APPROVAL_DATE").toLocalDate() : null);
            request.setDepartureLocation(rs.getString("DEPARTURE_LOCATION"));
            request.setDestination(rs.getString("DESTINATION"));
            request.setStartDate(rs.getDate("START_DATE").toLocalDate());
            request.setEndDate(rs.getDate("END_DATE").toLocalDate());
            request.setRequestStatus(TravelRequestStatus.valueOf(rs.getString("REQUEST_STATUS")));
            request.setVehicleId(rs.getLong("VEHICLE_ID"));
            request.setDriverId(rs.getLong("DRIVER_ID"));
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

    public TravelRequest save(TravelRequest request) {
        if (request.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO TRAVEL_REQUEST (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, " +
                            "DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID",
                    Long.class,
                    request.getApprovedBy(),
                    request.getApprovalDate(),
                    request.getDepartureLocation(),
                    request.getDestination(),
                    request.getStartDate(),
                    request.getEndDate(),
                    request.getRequestStatus().name(),
                    request.getVehicleId(),
                    request.getDriverId());
            request.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE TRAVEL_REQUEST SET APPROVED_BY = ?, APPROVAL_DATE = ?, " +
                            "DEPARTURE_LOCATION = ?, DESTINATION = ?, START_DATE = ?, END_DATE = ?, " +
                            "REQUEST_STATUS = ?, VEHICLE_ID = ?, DRIVER_ID = ? WHERE ID = ?",
                    request.getApprovedBy(),
                    request.getApprovalDate(),
                    request.getDepartureLocation(),
                    request.getDestination(),
                    request.getStartDate(),
                    request.getEndDate(),
                    request.getRequestStatus().name(),
                    request.getVehicleId(),
                    request.getDriverId(),
                    request.getId());
        }
        return request;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM TRAVEL_REQUEST WHERE ID = ?", id);
    }
}
