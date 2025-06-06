package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import ba.unsa.etf.nbp.VehicleTrackPlatform.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Repository
public class DocumentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class DocumentRowMapper implements RowMapper<Document> {
        @Override
        public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Document(
                rs.getLong("ID"),
                rs.getString("FILE_NAME"),
                rs.getString("FILE_TYPE"),
                rs.getBytes("CONTENT"),
                rs.getTimestamp("CREATED_AT").toInstant()
            );
        }
    }

    public Optional<Document> findById(Long id) {
        return jdbcTemplate.query(
            "SELECT * FROM DOCUMENT WHERE ID = ?",
            new DocumentRowMapper(),
            id
        ).stream().findFirst();
    }

    public Long create(Document document) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            String sql = "INSERT INTO DOCUMENT (FILE_NAME, FILE_TYPE, CONTENT, CREATED_AT) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
            ps.setString(1, document.getFileName());
            ps.setString(2, document.getFileType());
            ps.setBytes(3, document.getContent());
            ps.setTimestamp(4, Timestamp.from(document.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM DOCUMENT WHERE ID = ?", id);
    }
} 