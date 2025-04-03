package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.StationDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public List<StationDTO> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDTO> getStationById(@PathVariable Long id) {
        return stationService.getStationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StationDTO> createStation(@RequestBody StationDTO stationDTO) {
        StationDTO savedStation = stationService.saveStation(stationDTO);
        return ResponseEntity.created(URI.create("/api/stations/" + savedStation.getId()))
                .body(savedStation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDTO> updateStation(@PathVariable Long id, @RequestBody StationDTO stationDTO) {
        stationDTO.setId(id);
        return ResponseEntity.ok(stationService.saveStation(stationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }
}
