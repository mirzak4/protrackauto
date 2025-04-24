package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Fuel;
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
public class FuelRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FuelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class FuelRowMapper implements RowMapper<Fuel> {
        @Override
        @NonNull
        public Fuel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Fuel fuel = new Fuel(
                    rs.getLong("ID"),
                    rs.getString("NAME")
            );
            fuel.setCreatedAt(rs.getTimestamp("CREATED_AT").toInstant());
            fuel.setCreatedBy(rs.getString("CREATED_BY"));
            fuel.setModifiedAt(rs.getTimestamp("MODIFIED_AT").toInstant());
            fuel.setModifiedBy(rs.getString("MODIFIED_BY"));
            return fuel;
        }
    }

    public List<Fuel> findAll() {
        return jdbcTemplate.query("SELECT * FROM FUEL", new FuelRowMapper());
    }

    public Optional<Fuel> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM FUEL WHERE ID = ?", new FuelRowMapper(), id).stream().findFirst();
    }

    public Long create(Fuel fuel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO FUEL (NAME) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, fuel.getName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Fuel update(Fuel fuel) {
        jdbcTemplate.update(
                "UPDATE FUEL SET NAME = ? WHERE ID = ?",
                fuel.getName(),
                fuel.getId()
        );
        return fuel;
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM FUEL WHERE ID = ?", id);
    }
}
