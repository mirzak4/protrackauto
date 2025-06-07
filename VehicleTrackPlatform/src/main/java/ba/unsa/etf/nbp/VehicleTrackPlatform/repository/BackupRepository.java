package ba.unsa.etf.nbp.VehicleTrackPlatform.repository;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class BackupRepository {

    private static final Logger LOG = Logger.getLogger(BackupRepository.class.getName());
    private final DataSource ds;

    public BackupRepository(DataSource ds) { this.ds = ds; }

    public void exportTableToCsv(String table, Path file) throws SQLException, IOException {
        try (Connection c = ds.getConnection();
             Statement  st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
             ResultSet   rs = st.executeQuery("SELECT * FROM " + table);
             CSVWriter   writer = new CSVWriter(Files.newBufferedWriter(file))) {

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
        }
        LOG.info(() -> "Table " + table + " → " + file.getFileName());
    }

    public String[] listTables() throws SQLException {
        try (Connection c = ds.getConnection();
             ResultSet rs = c.getMetaData()
                     .getTables(null, c.getSchema(), "%", new String[] { "TABLE" })) {

            List<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("TABLE_NAME"));
            }
            return list.toArray(new String[0]);
        }
    }
}
