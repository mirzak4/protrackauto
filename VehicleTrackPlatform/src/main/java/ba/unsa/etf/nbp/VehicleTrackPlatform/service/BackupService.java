package ba.unsa.etf.nbp.VehicleTrackPlatform.service;

import ba.unsa.etf.nbp.VehicleTrackPlatform.repository.BackupRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Service
public class BackupService {

    private final BackupRepository repo;
    private final Path backupDir;

    public BackupService(BackupRepository repo,
                         @Value("${backup.dir}") String dir) {
        this.repo = repo;
        this.backupDir = Path.of(dir);
        try { Files.createDirectories(backupDir); } catch (Exception ignored) {}
    }

    @Scheduled(cron = "${backup.schedule}")
    public void runScheduled() throws Exception {
        doFullCsvBackup();
    }

    public void doFullCsvBackup() throws Exception {
        String today = LocalDate.now().toString();
        Path dayDir  = backupDir.resolve(today);
        Files.createDirectories(dayDir);

        for (String tbl : repo.listTables()) {
            Path csvFile = dayDir.resolve(tbl + ".csv");
            repo.exportTableToCsv(tbl, csvFile);
        }
    }
}

