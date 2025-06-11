package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import com.opencsv.CSVWriter;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class BackupRepository {

    private static final Logger LOG = Logger.getLogger(BackupRepository.class.getName());
    private final JdbcTemplate jdbc;

    public BackupRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void exportTableToCsv(String table, Path file) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(file);
             CSVWriter writer  = new CSVWriter(bw)) {

            jdbc.query("SELECT * FROM " + table, (ResultSetExtractor<Void>) rs -> {
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();

                String[] header = new String[cols];
                for (int i = 1; i <= cols; i++) header[i - 1] = md.getColumnName(i);
                writer.writeNext(header);

                while (rs.next()) {
                    String[] row = new String[cols];
                    for (int i = 1; i <= cols; i++)
                        row[i - 1] = Objects.toString(rs.getObject(i), "");
                    writer.writeNext(row, false);
                }
                return null;
            });
        }
        LOG.info(() -> "Table " + table + " → " + file.getFileName());
    }


    public String[] listTables() {
        return jdbc.execute((ConnectionCallback<String[]>) con -> {
            try (ResultSet rs = con.getMetaData()
                    .getTables(null, con.getSchema(), "%", new String[]{"TABLE"})) {
                List<String> list = new ArrayList<>();
                while (rs.next()) list.add(rs.getString("TABLE_NAME"));
                return list.toArray(new String[0]);
            }
        });
    }
}
