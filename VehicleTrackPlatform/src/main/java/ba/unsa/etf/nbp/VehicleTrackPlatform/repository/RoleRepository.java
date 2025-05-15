package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@SuppressWarnings("SqlResolve")
@Repository
public class RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Role findRoleByName(String roleName) {
        String sql = "SELECT ID, NAME FROM NBP.NBP_ROLE WHERE NAME = ?";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new Role(rs.getLong("ID"), rs.getString("NAME")),
                "NBP04." + roleName
        );
    }
}
