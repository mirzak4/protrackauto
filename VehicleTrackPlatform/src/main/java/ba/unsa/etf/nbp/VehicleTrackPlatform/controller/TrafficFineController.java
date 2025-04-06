package ba.unsa.etf.nbp.VehicleTrackPlatform.controller;

import ba.unsa.etf.nbp.VehicleTrackPlatform.dto.TrafficFineDTO;
import ba.unsa.etf.nbp.VehicleTrackPlatform.service.TrafficFineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/traffic-fine")
public class TrafficFineController {
    private final TrafficFineService trafficFineService;

    public TrafficFineController(TrafficFineService trafficFineService) {
        this.trafficFineService = trafficFineService;
    }

    @GetMapping
    public List<TrafficFineDTO> getAllTrafficFines() {
        return trafficFineService.getAllTrafficFines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrafficFineDTO> getTrafficFineById(@PathVariable Long id) {
        return trafficFineService.getTrafficFineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TrafficFineDTO> createTrafficFine(@RequestBody TrafficFineDTO trafficFineDTO) {
        TrafficFineDTO savedFine = trafficFineService.saveTrafficFine(trafficFineDTO);
        return ResponseEntity.created(URI.create("/api/traffic-fines/" + savedFine.getId()))
                .body(savedFine);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrafficFineDTO> updateTrafficFine(@PathVariable Long id, @RequestBody TrafficFineDTO trafficFineDTO) {
        trafficFineDTO.setId(id);
        return ResponseEntity.ok(trafficFineService.saveTrafficFine(trafficFineDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrafficFine(@PathVariable Long id) {
        trafficFineService.deleteTrafficFine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/driver/{driverId}")
    public List<TrafficFineDTO> getFinesByDriver(@PathVariable Long driverId) {
        return trafficFineService.getFinesByDriver(driverId);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<TrafficFineDTO> getFinesByVehicle(@PathVariable Long vehicleId) {
        return trafficFineService.getFinesByVehicle(vehicleId);
    }
}
