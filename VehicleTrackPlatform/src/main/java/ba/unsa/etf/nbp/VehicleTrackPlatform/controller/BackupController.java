package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.service.BackupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/backup")
public class BackupController {

    private final BackupService service;

    public BackupController(BackupService service) { this.service = service; }

    @GetMapping("/now")
    public ResponseEntity<Map<String, Object>> trigger() {
        try {
            service.doFullCsvBackup();
            return ResponseEntity.ok(
                    Map.of("success", true,
                            "message", "Backup finished OK."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(
                    Map.of("success", false,
                            "message", "Backup failed: " + ex.getMessage()));
        }
    }
}

