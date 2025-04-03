package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Station;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class StationRepository {
    private final JdbcTemplate jdbcTemplate;

    public StationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class StationRowMapper implements RowMapper<Station> {
        @Override
        public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
            Station station = new Station();
            station.setId(rs.getLong("ID"));
            station.setName(rs.getString("NAME"));
            station.setAddress(rs.getString("ADDRESS"));
            station.setCity(rs.getString("CITY"));
            station.setPostalCode(rs.getString("POSTAL_CODE"));
            station.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            station.setEmail(rs.getString("EMAIL"));
            return station;
        }
    }

    public List<Station> findAll() {
        return jdbcTemplate.query("SELECT * FROM STATION", new StationRowMapper());
    }

    public Optional<Station> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM STATION WHERE ID = ?",
                new StationRowMapper(), id).stream().findFirst();
    }

    public Station save(Station station) {
        if (station.getId() == null) {
            Long id = jdbcTemplate.queryForObject(
                    "INSERT INTO STATION (NAME, ADDRESS, CITY, POSTAL_CODE, PHONE_NUMBER, EMAIL) " +
                            "VALUES (?, ?, ?, ?, ?, ?) RETURNING ID",
                    Long.class,
                    station.getName(),
                    station.getAddress(),
                    station.getCity(),
                    station.getPostalCode(),
                    station.getPhoneNumber(),
                    station.getEmail());
            station.setId(id);
        } else {
            jdbcTemplate.update(
                    "UPDATE STATION SET NAME = ?, ADDRESS = ?, CITY = ?, " +
                            "POSTAL_CODE = ?, PHONE_NUMBER = ?, EMAIL = ? WHERE ID = ?",
                    station.getName(),
                    station.getAddress(),
                    station.getCity(),
                    station.getPostalCode(),
                    station.getPhoneNumber(),
                    station.getEmail(),
                    station.getId());
        }
        return station;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM STATION WHERE ID = ?", id);
    }
}
